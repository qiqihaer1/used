package com.keduox.kafkastorm;

import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 将多个节点进行连接处理
 */
public class KafkaJob {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        BrokerHosts hosts = new ZkHosts("192.168.200.21:2181","/brokers");
        /**
         * hosts:用以获取Kafka broker和partition的信息,在zk上获取，此处填写zk的地址
         * topic:从哪个topic读取消息 zkRoot:进度信息记录于zookeeper的哪个路径下
         * id:进度记录的id，想要一个新的Spout读取之前的记录，应把它的id设为跟之前的一样
         */
        SpoutConfig spoutConfig = new SpoutConfig(hosts, "mytopic", "/bigdata/kafkastorm", "storm-example");
        spoutConfig.zkRoot = "/bigdata/kafkastorm";
        spoutConfig.zkPort = 2181;
        List<String> zkServersList = Arrays.asList("192.168.200.21");
        spoutConfig.zkServers = zkServersList;
        spoutConfig.id ="storm-example";
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
        topologyBuilder.setSpout("kafkaSpout",kafkaSpout);
        topologyBuilder.setBolt("SplitBolt", new SplitBolt()).shuffleGrouping("kafkaSpout");
        topologyBuilder.setBolt("CountBolt", new CountBolt()).shuffleGrouping("SplitBolt");
        topologyBuilder.setBolt("PrintBolt", new PrintBolt()).shuffleGrouping("CountBolt");
//         // 启动topology的配置信息
//         Config config = new Config();
//         // 定义集群分配多少个工作进程来执行这个topology
//         config.setNumWorkers(3);
        //本地分布
//          LocalCluster localCluster = new LocalCluster();
//          localCluster.submitTopology("salt", new HashMap(), topologyBuilder.createTopology());
        // 集群模式提交topology
         StormSubmitter.submitTopologyWithProgressBar("salt", new HashMap(), topologyBuilder.createTopology());


    }
}

package com.keduox.com.keduox.moretest;

import com.keduox.test.BoltTest;
import com.keduox.test.SpoutTest;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import java.util.HashMap;

/**
 * 将多个节点进行连接处理
 */
public class WordJob {
    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
       //id自己定义,IRichSpout spout就是指extends IRichSpout的子类BaseRichSpout的SpoutTest对象
        //两个包的类的名字SpoutTest相同,修改后不报错
        topologyBuilder.setSpout("spout",new BlankSpout());
        //同上，放入对象,同时设置streamGrouping为shuffleGrouping并关联
        topologyBuilder.setBolt("split",new SplitBolt()).shuffleGrouping("spout");
        topologyBuilder.setBolt("Count",new CountBolt()).shuffleGrouping("split");
        topologyBuilder.setBolt("Print",new PrintBolt()).shuffleGrouping("Count");
        //启动本地模式，可以在idea直接运行
        LocalCluster localCluster = new LocalCluster();
        //String var1, Map var2, StormTopology var3,
        localCluster.submitTopology("salt",new HashMap(),topologyBuilder.createTopology());
    }
}

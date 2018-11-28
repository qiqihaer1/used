package com.keduox.test;

import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import java.util.HashMap;

/**
 * 将多个节点进行连接处理
 */
public class JobTest {
    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
       //id自己定义,IRichSpout spout就是指extends IRichSpout的子类BaseRichSpout的SpoutTest对象
        topologyBuilder.setSpout("zijixie",new SpoutTest());
        //同上，放入对象,同时设置streamGrouping为shuffleGrouping并关联
        topologyBuilder.setBolt("kankan",new BoltTest()).shuffleGrouping("zijixie");
        //启动本地模式，可以在idea直接运行
        LocalCluster localCluster = new LocalCluster();
        //String var1, Map var2, StormTopology var3,
        localCluster.submitTopology("salt",new HashMap(),topologyBuilder.createTopology());
    }
}

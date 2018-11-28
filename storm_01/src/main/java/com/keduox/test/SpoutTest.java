package com.keduox.test;

import org.apache.storm.spout.ISpoutOutputCollector;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 将我们自己的数据，转换成tuple之后，发送到bolt当中
 */
public class SpoutTest extends BaseRichSpout{
    //发送的数据
    String[] datas=new String[]{"go","c++","python","java","php","scala",".net"};
    //设置全局变量，为了在nextTuple方法中使用
    private SpoutOutputCollector collector;
    /**
     * 初始化方法，如果有一些参数需要初始值的话，可以在该方法当中定义
     * 该方法在程序运行过程当中只执行一次
     * @param map
     * @param topologyContext
     * @param spoutOutputCollector
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        collector=spoutOutputCollector;
    }

    /**
     * 将数据转为tuple对象
     * 该方法会一直运行
     */
    @Override
    public void nextTuple() {
        Random random = new Random();
        String data=datas[random.nextInt(datas.length-1)];
//        List list = Arrays.asList(data,data);
//        collector.emit(list);
        //Values()代替了List
        collector.emit(new Values(data));
    }

    /**
     * 每一个tuple当中是有多个值，而且每一个值需要一个name，该方法就是定义tuple有哪些name
     * 也是决定一个tuple当中有多少个元素的方法
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //tuple的名字，数量要与emit()发送的数量一致
        outputFieldsDeclarer.declare(new Fields("test1"));
    }
}

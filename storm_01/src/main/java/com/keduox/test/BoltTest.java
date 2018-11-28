package com.keduox.test;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * bolt主要是处理spout或bolt传输过来的数据
 */
public class BoltTest extends BaseRichBolt {
    private OutputCollector collector;
    /**
     * 初始处理
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        outputCollector=outputCollector;
    }

    /**
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //通过字段名拿到tuple中的值
//        Object value1 = tuple.getValueByField("test1");
//        String s1 = value1.toString();
        //当值为string，可以用getStringByField()
        String s1 = tuple.getStringByField("test1");
        System.out.println("storm:"+s1);
    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("test1"));
    }
}

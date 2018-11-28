package com.keduox.com.keduox.moretest;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * bolt主要是处理spout或bolt传输过来的数据
 */
public class SplitBolt extends BaseRichBolt {
    private OutputCollector collector;
    /**
     * 初始处理
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector=outputCollector;
    }

    /**
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //通过字段名拿到bolt中的值
        String s1 = tuple.getStringByField("efsefas");
        //判断数据不为空
        if(s1!=null&&s1.trim().length()>0) {
            String[] dataArray =s1.split(" ");
            for (String s : dataArray) {
                //发送分离的数据
                collector.emit(new Values(s));
                System.out.println("分离后的storm:" + s);
            }
        }

    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("splitData"));
    }
}

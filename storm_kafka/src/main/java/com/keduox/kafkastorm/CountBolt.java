package com.keduox.kafkastorm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * bolt主要是处理spout或bolt传输过来的数据
 */
public class CountBolt extends BaseRichBolt {
    private OutputCollector collector;
    //采用临时存储的对象
    private Map<String,Long> result=new HashMap<String,Long>();
    /**
     * 初始处理
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector=outputCollector;
//        result=map;
    }

    /**
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //拿splitbolt的数据
        String str1 = tuple.getStringByField("splitData1");
        String str2 = tuple.getStringByField("splitData2");
        //先判断值是否存在，存在则获取value值并+1，不存在将value值由null变为0，存在则取value值
        Long aLong1 = result.get(str1)==null?0:result.get(str1);
        Long aLong2 = result.get(str2)==null?0:result.get(str2);
        //向map里新建一个key,值为1统计内容和出现次数的
        result.put(str1,aLong1+1);
        result.put(str2,aLong2+1);
        collector.emit(new Values(result));
    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("CountData"));
    }
}

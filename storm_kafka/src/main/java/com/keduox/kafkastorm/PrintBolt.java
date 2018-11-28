package com.keduox.kafkastorm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * bolt主要是处理spout或bolt传输过来的数据
 */
public class PrintBolt extends BaseRichBolt {
    /**
     * 初始处理
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    }

    /**
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //通过字段名拿到countbolt中的值,强制转型为Map<String,Long>
        Map<String,Long> map = (Map<String,Long>)tuple.getValueByField("CountData");
        //map.keySet()返回所有key的Set<>集合,将set<>集合放入redis
        //获取连接传入ip
        //如果不是默认6379端口，需要多加一个port参数
        Jedis jedis = new Jedis("localhost",6379);
        //循环放入set<>数据
            for (String key:map.keySet()){
                //放入key和value
                String value = String.valueOf(map.get(key));
                jedis.set(key,value);
                //设置过期时间为7天
                jedis.expire(key, 604800);
                System.out.println("key:"+key+"|value:"+value);
            }
        //关闭连接
        jedis.close();
    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}

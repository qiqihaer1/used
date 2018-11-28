package com.keduox.com.keduox.moretest;

        import org.apache.storm.task.OutputCollector;
        import org.apache.storm.task.TopologyContext;
        import org.apache.storm.topology.OutputFieldsDeclarer;
        import org.apache.storm.topology.base.BaseRichBolt;
        import org.apache.storm.tuple.Tuple;

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
        //map.keySet()返回所有key的Set<>集合
            for (String key:map.keySet()){
                System.out.println("key:"+key+"|value:"+map.get(key));
            }
    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}

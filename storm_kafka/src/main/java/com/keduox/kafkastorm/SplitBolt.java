package com.keduox.kafkastorm;

import com.keduox.util.AddressUtil;
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
    //发送者
    private OutputCollector collector;

   // 初始处理
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
        Object s = tuple.getValues() ;
        String s1=s.toString();
        System.out.println("我的kafka数据:"+s1);
        //判断数据不为空
        if(s1!=null&&s1.trim().length()>0) {
            String[] dataArray =s1.split("\t");
            //防脏写
            if (dataArray.length==3){
                    //发送分离的数据
                String address= "台湾";
                try {
                    address = AddressUtil.getAddressByIp(dataArray[1]);
                    String province=dataArray[2].trim().replaceAll("\\]", "").replaceAll("\\n", "");
                    collector.emit(new Values(address,province));
                    System.out.println("分离后的地址:" + address);
                    System.out.println("分离后的浏览器:" + province);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("输入格式错误!");
            }

        }

    }

    /**
     * 向bolt发送数据
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("splitData1","splitData2"));
    }
}

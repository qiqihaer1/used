package com.keduox;


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartition implements Partitioner {


    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        //如何将自定义分区绑定到指定生产者？？？
        //s=topic,o=key,bytes=value,ol=
        if(o!=null&&o.toString().length()==11){
            //135开头的手机号进入0分区
            //返回布尔值用startsWith
            if(o.toString().startsWith("135")){
                return 0;
            }else if(o.toString().startsWith("181")){
                return 1;
            }else if (o.toString().startsWith("130")){
                return 2;
            }else {
                return 3;
            }
        }
        System.out.println("数据错误");
        return 4;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

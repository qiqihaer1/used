package com.keduox;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

public class test {

    public static void main(String[] args) {
       //构造zkclient,2181端口可以省略不写
        //当有多个zk时
        String hosts="master0301,slave0301,slave0302";
        ZkClient zkClient = new ZkClient(hosts,10000,10000,new SerializableSerializer());
        //zk的节点的三种类型：普通类型，有 序类型，临时类型
        //临时节点：当前会话有效，会话结束节点自动消失
        zkClient.create("/javanode","halo", CreateMode.PERSISTENT);
        //关闭
        zkClient.close();
    }
}

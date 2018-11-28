package com.kd.controller;

import com.kd.entity.Hbase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ErrorNameController {

//        public static void main(String[] args) {
//        List list = null;
//        try {
//            list = ScanData1();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(list);
//    }

    public  List<Hbase>  ScanData1() throws IOException{
        List<Hbase> list=new ArrayList();
        Hbase hbase=null;
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301,slave0301,slave0302");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //获取表
        Table table = connection.getTable(TableName.valueOf("three_day_big_tx"));
        //scan的创建
        Scan scan = new Scan();
        //设置每次从HBASE拿取数据的条数，作为读取大量数据的一个优化
        scan.setCaching(2000);
        //获取多行数据
        ResultScanner scanner=table.getScanner(scan);
        //拿到第一行数据
        Result result = scanner.next();
        //拿到每行的具体数据
        while (result!=null){
            //按cell一个个遍历
            for(Cell cell:result.rawCells()){
                String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell)) ;//返回字段名
                String rowkey=Bytes.toString(CellUtil.cloneRow(cell)) ;//返回行键名
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(rowkey+"|"+qualifier+"|"+value+"|"+family);
                hbase=new Hbase(rowkey,qualifier,value,family);
                list.add(hbase);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
        return list;
    }

    public  List<Hbase>  ScanData2() throws IOException{
        List<Hbase> list=new ArrayList();
        Hbase hbase=null;
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301,slave0301,slave0302");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //获取表
        Table table = connection.getTable(TableName.valueOf("five_day_top"));
        //scan的创建
        Scan scan = new Scan();
        //设置每次从HBASE拿取数据的条数，作为读取大量数据的一个优化
        scan.setCaching(2000);
        //获取多行数据
        ResultScanner scanner=table.getScanner(scan);
        //拿到第一行数据
        Result result = scanner.next();
        //拿到每行的具体数据
        while (result!=null){
            //按cell一个个遍历
            for(Cell cell:result.rawCells()){
                String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell)) ;//返回字段名
                String rowkey=Bytes.toString(CellUtil.cloneRow(cell)) ;//返回行键名
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(rowkey+"|"+qualifier+"|"+value+"|"+family);
                hbase=new Hbase(rowkey,qualifier,value,family);
                list.add(hbase);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
        return list;
    }

    public  List ScanData3() throws IOException{
        List<Hbase> list=new ArrayList();
        Hbase hbase=null;
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301,slave0301,slave0302");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //获取表
        Table table = connection.getTable(TableName.valueOf("error_status_customer"));
        //scan的创建
        Scan scan = new Scan();
        //设置每次从HBASE拿取数据的条数，作为读取大量数据的一个优化
        scan.setCaching(2000);
        //获取多行数据
        ResultScanner scanner=table.getScanner(scan);
        //拿到第一行数据
        Result result = scanner.next();
        //拿到每行的具体数据
        while (result!=null){
            //按cell一个个遍历
            for(Cell cell:result.rawCells()){
                String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell)) ;//返回字段名
                String rowkey=Bytes.toString(CellUtil.cloneRow(cell)) ;//返回行键名
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(rowkey+"|"+qualifier+"|"+value+"|"+family);
                hbase=new Hbase(rowkey,qualifier,value,family);
                list.add(hbase);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
        return list;
    }


}

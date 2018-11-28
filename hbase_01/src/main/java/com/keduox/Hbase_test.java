package com.keduox;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Hbase_test {
    public static void main(String[] args) throws IOException {
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","salt");
        //创建连接
        Connection connection = ConnectionFactory.createConnection(config);
       // 使用一个封装插入数据的方法,插入表user
        PutListData(connection);
        GetData(connection);
//        ScanData(connection);
//        ScanFilterData(connection);
//        CreateTable(connection);
//        DeleteTable(connection);
//        PutHbaseData();
    }

    public static void PutListData(Connection connection) throws IOException {
        //获取表
        Table table = connection.getTable(TableName.valueOf("user"));
        //创建空集合，防止报错
        List<Put> list =Collections.emptyList();
        Put put =null;
        for (int i=1;i<20000;i++){
            if (list.isEmpty()){
                list = new ArrayList<Put>();
            }
            //put没有无参构造，构造行键
            put = new Put(Bytes.toBytes("rowkey01"+i));
            put.addColumn(Bytes.toBytes("name"),Bytes.toBytes("test"+i),Bytes.toBytes("wife"+i));
            list.add(put);
        }
        //将put传给table
        table.put(list);
        //关闭连接
        table.close();
        connection.close();
    }

    public static void GetData(Connection connection) throws IOException{
        //获取表
        Table table = connection.getTable(TableName.valueOf("user"));
        //拿一行的数据
        Get get = new Get(Bytes.toBytes("rowkey019999"));
        Result result=table.get(get);
        //按cell一个个遍历
        for (Cell cell:result.rawCells()){
//            byte[] bytes = CellUtil.cloneQualifier(cell);//返回字段名
           String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell)) ;//返回字段名
           String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
           String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
            System.out.println(qualifier+"|"+value+"|"+family);
        }
        //关闭连接
        table.close();
        connection.close();
    }

    public static void ScanData(Connection connection) throws IOException{
        //获取表
        Table table = connection.getTable(TableName.valueOf("user"));
        //scan的创建
        Scan scan = new Scan();
        //设置scan只取一段数据
        scan.setStartRow(Bytes.toBytes("rowkey019111"));
        scan.setStopRow(Bytes.toBytes("rowkey019999"));
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
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(qualifier+"|"+value+"|"+family);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
    }

    public static void ScanFilterData(Connection connection) throws IOException{
        //获取表
        Table table = connection.getTable(TableName.valueOf("user"));
        //scan的创建
        Scan scan = new Scan();
        //设置Filter,filter是抽象类，必须拿他的子类
        //filter只能读取行键中包含rowkey01999的行键
        Filter filter=new PrefixFilter(Bytes.toBytes("rowkey01199"));
        scan.setFilter(filter);
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
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(qualifier+"|"+value+"|"+family);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
    }

    public static void CreateTable(Connection connection) throws IOException{
        //创建表需要先得到管理权限，不用table用admin
        Admin admin = connection.getAdmin();
        //创建表的时候需要表名，列族名
        HTableDescriptor htd=new HTableDescriptor(TableName.valueOf("lakers"));
        //创建列族
        HColumnDescriptor family=new HColumnDescriptor("cf01");
        //列族加入表中
        htd.addFamily(family);
        //创建表
        admin.createTable(htd);

        //关闭连接
        admin.close();
        connection.close();
    }

    public static void DeleteTable(Connection connection) throws IOException{
        //创建表需要先得到管理权限，不用table用admin
        Admin admin = connection.getAdmin();
        //先判断表是否存在
        TableName tableName = TableName.valueOf("lakers");
        if (admin.tableExists(tableName)){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        //关闭连接
        admin.close();
        connection.close();
    }

    public static void PutHbaseData() throws IOException {
        //当前日期
        java.util.Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);

        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //使用一个封装插入数据的方法,插入表user
        //获取表
        Table table = connection.getTable(TableName.valueOf("three_day_big_tx"));
        //创建空集合，防止报错
        //put没有无参构造，构造行键
        String str1="gaga";
        int str2=2222;
        int str3=132114;
        int i=10;
        Put put = new Put(Bytes.toBytes("rowkey"+day+"00"+i));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"+i),Bytes.toBytes(str1));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("frequency"+i),Bytes.toBytes(str2+""));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("cost"+i),Bytes.toBytes(str3+""));
        //将put传给table
        table.put(put);
        //关闭连接
        table.close();
        connection.close();
    }
}

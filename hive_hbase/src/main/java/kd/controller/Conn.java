package kd.controller;

import kd.entity.ThreeDay;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conn {
    public void PutHbaseData(ThreeDay threeDay, int i) throws IOException {
        //当前日期
        Date date=new Date();
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
        String str1=threeDay.getName();
        int str2=threeDay.getFrequency();
        int str3=threeDay.getCost();
        Put put = new Put(Bytes.toBytes("rowkey"+day+"00"+i));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"+i),Bytes.toBytes(str1));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("frequency"+i),Bytes.toBytes(str2));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("cost"+i),Bytes.toBytes(str3));
        //将put传给table
        table.put(put);
        //关闭连接
        table.close();
        connection.close();
    }


    public List HiveGet() {

        List list=new ArrayList();
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            String url="jdbc:hive2://192.168.200.100:10000/default";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql="select * from three_day_big_tx where day="+day;
//            Statement st =conn.createStatement();
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i=1;
            //取出内容
            while (rs.next()){
                String str1=rs.getString(1);
                int str2=rs.getInt(2);
                int str3=rs.getInt(3);
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(str3);
                ThreeDay threeDay=new ThreeDay(str1,str2,str3);
                list.add(threeDay);
                //put没有无参构造，构造行键

                //将数据插入hbase的表中
//                PutHbaseData(threeDay,i);
                i++;
                System.out.println(i);
            }
            System.out.println(list);
            //关闭连接
            ps.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

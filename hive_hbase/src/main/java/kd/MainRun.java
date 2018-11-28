package kd;


import kd.entity.ThreeDay;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainRun {

    public static void main(String[] args) {
        //将hive表three_day_big_tx放入hbase表
        HiveGet1();
        //将hive表five_day_top放入hbase表
        HiveGet2();
        //将hive表error_status_customer放入hbase表
        HiveGet3();
    }


    public static List HiveGet1() {
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
            //取出内容
            int i=1;
            while (rs.next()){
                String str1=rs.getString(1);
                int str2=rs.getInt(2);
                int str3=rs.getInt(3);
                ThreeDay threeDay=new ThreeDay(str1,str2,str3);
                System.out.println(str1);
                list.add(threeDay);
                //将数据插入hbase的表中
                PutHbaseData1(threeDay,i);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void PutHbaseData1(ThreeDay threeDay,int i) throws IOException {
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        //随机数取盐
        Random random=new Random();
        int ran=random.nextInt(10);

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
        Put put = new Put(Bytes.toBytes("rowkey"+ran+day+"00"+i));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"),Bytes.toBytes(str1));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("frequency"),Bytes.toBytes(str2+""));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("cost"),Bytes.toBytes(str3+""));
        //将put传给table
        table.put(put);
        //关闭连接
        table.close();
        connection.close();
    }

    public static void HiveGet2() {
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            String url="jdbc:hive2://192.168.200.100:10000/default";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql="select * from five_day_top where day="+day;

            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //取出内容
            int i=1;
            while (rs.next()){
                String str1=rs.getString(1);
                //将数据插入hbase的表中
                PutHbaseData2(str1,i);
                i++;
            }
            //关闭连接
            ps.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void PutHbaseData2(String str,int i) throws IOException {
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        //随机数取盐
        Random random=new Random();
        int ran=random.nextInt(10);

        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //使用一个封装插入数据的方法,插入表user
        //获取表
        Table table = connection.getTable(TableName.valueOf("five_day_top"));
        //创建空集合，防止报错
        //put没有无参构造，构造行键
        Put put = new Put(Bytes.toBytes("rowkey"+ran+day+"00"+i));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"),Bytes.toBytes(str));
        //将put传给table
        table.put(put);
        //关闭连接
        table.close();
        connection.close();
    }

    public static void HiveGet3() {
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            String url="jdbc:hive2://192.168.200.100:10000/default";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql="select * from error_status_customer where day="+day;

            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //取出内容
            int i=1;
            while (rs.next()){
                String str1=rs.getString(1);
                //将数据插入hbase的表中
                PutHbaseData3(str1,i);
                i++;
                System.out.println(str1);
            }
            //关闭连接
            ps.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {


        }
    }

    public static void PutHbaseData3(String str,int i) throws IOException {
        //当前日期
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);
        //随机数取盐
        Random random=new Random();
        int ran=random.nextInt(10);

        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //使用一个封装插入数据的方法,插入表user
        //获取表
        Table table = connection.getTable(TableName.valueOf("error_status_customer"));
        //创建空集合，防止报错
        //put没有无参构造，构造行键
        int result=i%5;
        System.out.println(result);
        Put put = new Put(Bytes.toBytes("rowkey"+ran+day+"00"+i));
        put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"),Bytes.toBytes(str));
        //将put传给table
        table.put(put);
        //关闭连接
        table.close();
        connection.close();
    }

    public static void ScanData() throws IOException{
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301");
        //创建连接
        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(config);
        //获取表
        Table table = connection.getTable(TableName.valueOf("error_status_customer"));
        //scan的创建
        Scan scan = new Scan();
        //设置Filter,filter是抽象类，必须拿他的子类
        //以zs35开头的字符串
        RegexStringComparator comp = new RegexStringComparator("zs");
        //filter只能读取行键中包含rowkey01999的行键
//        Filter filter=new PrefixFilter(Bytes.toBytes("rowkey01199"));

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
                String rowkey=Bytes.toString(CellUtil.cloneRow(cell)) ;//返回字段名
                String value=Bytes.toString(CellUtil.cloneValue(cell)) ;///返回值
                String family=Bytes.toString(CellUtil.cloneFamily(cell)) ;///返回列族名
                System.out.println(rowkey+"|"+qualifier+"|"+value+"|"+family);
            }
            //拿到下行数据
            result=scanner.next();
        }
        //关闭连接
        table.close();
        connection.close();
    }
}

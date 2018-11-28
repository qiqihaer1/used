package com.keduox;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class filtertest {
    public static void main(String[] args) throws IOException {
        Configuration config= HBaseConfiguration.create();
        //zookeeper
        config.set("hbase.zookeeper.property.clientPort","2181");
        //zookeeper的地址，写主机名
        config.set("hbase.zookeeper.quorum","master0301,slave0301,slave0302");
        //创建连接
        Connection connection = ConnectionFactory.createConnection(config);
        //获取表
        Table table = connection.getTable(TableName.valueOf("smart"));
        //scan的创建
        Scan scan = new Scan();
        //以zs35开头的字符串
        RegexStringComparator comp = new RegexStringComparator("zs");
        //filter只能读取行键中包含rowkey01999的行键
//        Filter filter=new PrefixFilter(Bytes.toBytes("rowkey01199"));
        //默认情况下，对于没有过滤条件的字段，会将匹配上!!!!!!!!!!!!!!!!!!!!
        FilterList filterList=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes("f01"), Bytes.toBytes("age"), CompareFilter.CompareOp.GREATER,Bytes.toBytes("17"));
        SingleColumnValueFilter filter2= new SingleColumnValueFilter(Bytes.toBytes("f01"), Bytes.toBytes("name"), CompareFilter.CompareOp.EQUAL,comp);
        filterList.addFilter(filter1);
        filterList.addFilter(filter2);
        //设置该参数，当字段如age在表的每一行都有的情况下可以不设置
        filter1.setFilterIfMissing(true);
        scan.setFilter(filterList);
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
}

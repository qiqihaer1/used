package com.keduox.job;

import com.keduox.map.LocationMap;
import com.keduox.util.JDBCUtil;
import com.keduox.reduce.LocationReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class LocationJob {

    public static void main(String[] args) {

        //创建Job
        FileSystem fs = null;
        try {
            Configuration configuration = new Configuration();
            //更改路径
            configuration.set("fs.defaultFS", "hdfs://192.168.200.100:9000");
            Job job = Job.getInstance(configuration);
            job.setJarByClass(LocationJob.class);
            job.setJobName("location_job");
            //设置关联的东西
            job.setMapperClass(LocationMap.class);
            job.setReducerClass(LocationReduce.class);
            //设置输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);
            //告诉job从hdfs的哪里拿数据
            FileInputFormat.addInputPath(job, new Path("/message.txt"));
            //判断输出目录是否存在
            fs = FileSystem.get(configuration);
            Path outputPath = new Path("/spark_out_location");
            if (fs.exists(outputPath)) {
                fs.delete(outputPath, true);
            }
            //将结果输出到hdfs
            FileOutputFormat.setOutputPath(job, new Path("/spark_out_location/"));
            //启动
            boolean flag = job.waitForCompletion(true);
            if (flag) {
                System.out.println("程序执行成功!");

                //先清空MYSQL表数据
                JDBCUtil jdbcUtil=JDBCUtil.getInstance();
                Connection conn1 = null;
                Statement st = null;
                int row1=0;
                String sqlflush = "DELETE FROM t_location;";
                try {
                    //注册，建立连接
                    conn1=jdbcUtil.getConn();
                    st = conn1.createStatement();

                    row1=st.executeUpdate(sqlflush);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    jdbcUtil.close(st, conn1);
                }
                //获取数据
                //读取某个文件
            FSDataInputStream fdis = fs.open(new Path("/spark_out_location/part-r-00000"));
                BufferedReader br=new BufferedReader(new InputStreamReader(fdis));
                String line=null;
                while((line=br.readLine())!=null){
                    //向MYSQL插入数据
                    Connection conn = null;
                    PreparedStatement ps = null;

                    int row = 0;
                    String sql = "INSERT INTO `t_location` VALUES(NULL ,?);";

                    try {
                        conn = jdbcUtil.getConn();
                        ps = conn.prepareStatement(sql);
                        jdbcUtil.psValues(ps,line);

                        row = ps.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        jdbcUtil.close(ps, conn);
                    }
                    if(row==1){
                        System.out.println("插入成功！");
                    }else {
                        System.out.println("新增失败！");
                    }
                }
                //关闭
                fdis.close();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fs != null) {
                fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

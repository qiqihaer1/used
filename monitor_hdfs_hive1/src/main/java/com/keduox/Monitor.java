package com.keduox;


import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;

import java.net.URI;

import java.util.concurrent.TimeUnit;

public class Monitor {

    public static void main(String[] args) throws Exception{
        //连接HDFS
        Configuration conf = new Configuration();
        conf.set("dfs.replication","3");
        FileSystem fs = FileSystem.get(new URI( "hdfs://192.168.200.100:9000"),conf,"root");
        //查看目录,只能查看本地目录！！！！！！！
//        File directory = new File("D:\\HDFS本地文件");
        File directory = new File("/bigdata/hive_data");


//        FileStatus[] fileList = fs.listStatus(new Path("/bank"));
//        for (int i=0;i<fileList.length;i++){
//            FileStatus fileStatus=fileList[i];
//            String filename=fileStatus.getPath().getName();
//            System.out.println("filename:"+fileStatus.getPath().getName());
//            //下载文件
//            fs.copyToLocalFile(false,new Path("/bank/"+filename),new Path("D:/HDFS本地文件"),true);
//        }
        // 轮询间隔 30 秒
        long interval = TimeUnit.SECONDS.toMillis(30);
        // 创建一个文件观察器用于处理文件的格式
        FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),FileFilterUtils.suffixFileFilter(".success")));
        //设置文件变化监听器
        observer.addListener(new MyFileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
        monitor.start();
        //Thread.sleep(30000);
        //monitor.stop();

    }
}

final class MyFileListener implements FileAlterationListener {
    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
        System.out.println("monitor start scan files..");
    }


    @Override
    public void onDirectoryCreate(File file) {
        System.out.println(file.getName()+" director created.");
    }


    @Override
    public void onDirectoryChange(File file) {
        System.out.println(file.getName()+" director changed.");
    }


    @Override
    public void onDirectoryDelete(File file) {
        System.out.println(file.getName()+" director deleted.");
    }


    @Override
    public void onFileCreate(File file) {
        System.out.println(file.getName()+" created.");
        String filename1=file.getName();
        String filename=filename1.substring(0,filename1.length()-8);
        String date=filename1.substring(0,8);
        String filepath="/bank/"+filename;
        System.out.println(filename);
        try {
            //加载驱动
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //创建连接
            String url="jdbc:hive2://192.168.200.100:10000/default";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql="load data inpath'"+filepath+"'overwrite into table bankmessage PARTITION(day="+date+")";
            //执行语句
//            PreparedStatement ps=conn.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("sql插入成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onFileChange(File file) {
        System.out.println(file.getName()+" changed.");
    }


    @Override
    public void onFileDelete(File file) {
        System.out.println(file.getName()+" deleted.");
    }


    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
        System.out.println("monitor stop scanning..");
    }
}

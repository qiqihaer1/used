package com.keduox.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LocationReduce extends Reducer<Text,IntWritable,Text,NullWritable> {
//     JDBCUtil jdbcUtil;
//    public LocationReduce() {
//        jdbcUtil=JDBCUtil.getInstance();
//    }

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //values是由mapreduce处理过程中，将相同的key进行合并后，传递给我们的数据
//       long total=0L;
//        for (IntWritable value:values){
//            total+=value.get();
//        }
        //发送出去
        context.write(key,NullWritable.get());


    }

//        public int insertLocation(Text key) throws UidException, LoginNameException {
//            Connection conn = null;
//            PreparedStatement ps = null;
//
//            int row = 0;
//            String sql = "INSERT INTO `t_location` VALUES(NULL ,?);";
//
//            try {
//                conn = jdbcUtil.getConn();
//                ps = conn.prepareStatement(sql);
//                jdbcUtil.psValues(ps,key);
//
//                row = ps.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }finally {
//                jdbcUtil.close(ps, conn);
//            }
//            if(row==1){
//                System.out.println("插入成功！");
//            }else {
//                System.out.println("新增失败！");
//            }
//            return row;
//        }
    }


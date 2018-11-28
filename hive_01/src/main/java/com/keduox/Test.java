package com.keduox;

import java.sql.*;

import static java.lang.Class.forName;

public class Test{
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            String url="jdbc:hive2://192.168.200.21:10000/default";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql="select * from t02";
//            Statement st =conn.createStatement();
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //取出内容
            while (rs.next()){
                String str=rs.getString(1);
                System.out.println(str);
            }
            //关闭连接
            ps.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

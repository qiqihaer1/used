package com.keduox.chart;

import com.keduox.util.JDBCUtil;
import com.keduox.entity.message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Getchart {
    private JDBCUtil jdbcUtil;

    public Getchart() {
    }

    public Getchart(JDBCUtil jdbcUtil) {
        jdbcUtil = JDBCUtil.getInstance();
    }

    public  List<message> selectAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        List<message> list = new ArrayList<>();

        String sql = "SELECT `name`,`total` FROM t_name_total;";

        try {
            //注册，取得连接
            conn = jdbcUtil.getConn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                message ms =new message();
                ms.setName(rs.getString("name"));
                ms.setTotal(rs.getInt("total"));
                list.add(ms);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(st, rs, conn);
        }

        return list;
    }

}

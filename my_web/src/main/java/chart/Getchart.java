package chart;

import entity.Message;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Getchart {
    private JDBCUtil jdbcUtil;

    public Getchart() {
    }

    public Getchart(JDBCUtil jdbcUtil) {
        jdbcUtil = JDBCUtil.getInstance();
    }

    public  List<Message> selectAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        List<Message> list = new ArrayList<>();

        String sql = "SELECT `name`,`total` FROM t_name_total;";

        try {
            //注册，取得连接
            conn = jdbcUtil.getConn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Message ms =new Message();
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

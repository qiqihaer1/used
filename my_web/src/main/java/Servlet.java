import chart.Getchart;
import entity.Message;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet",urlPatterns = "/Servlet")
public class Servlet  {


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //申明一个String json变量用于返回给前台
//        String json = null;
        Getchart gc=new Getchart();
        List<Message> list=gc.selectAll();
//        JDBCUtil jdbcUtil=JDBCUtil.getInstance();
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//
//        List<message> list = new ArrayList<>();
//
//        String sql = "SELECT `name`,`total` FROM t_name_total;";
//
//        try {
//            //注册，取得连接
//            conn = jdbcUtil.getConn();
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                message ms =new message();
//                ms.setName(rs.getString("name"));
//                ms.setTotal(rs.getInt("total"));
//                list.add(ms);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            jdbcUtil.close(st, rs, conn);
//    }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("list1", list);
//        PrintWriter out = response.getWriter();
//        out.println(jsonObject.toJSONString());
        System.out.println(list);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/echart3.jsp").forward(request,response);
    }

}

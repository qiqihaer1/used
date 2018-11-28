package com.keduox.servlet;

import com.keduox.entity.Client;
import com.keduox.util.ClientUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet1",value= "/Servlet1")
public class Servlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        ClientUtil clientUtil = ClientUtil.getInstance();
        //获取将数据打印进入flume的方法

        Logger logger = Logger.getLogger("Servlet01");
        for (int i=0;i<6;i++){
            Client client = clientUtil.getMessage();
            System.out.println(client);
            logger.info(client);
        }
//        JSONObject jsonObject = new JSONObject();
//
//        String json = jsonObject.toJSONString();
//        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println("hahahahaahaha");
    }
}

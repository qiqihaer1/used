package com.kd.servlet;

import com.alibaba.fastjson.JSONObject;
import com.kd.controller.ErrorNameController;
import com.kd.entity.Hbase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet1",value= "/Servlet1")
public class Hbase_Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        ErrorNameController errorNameController =new ErrorNameController();
        List<Hbase> list = errorNameController.ScanData1();

//建立JSON对象，放入属性
        JSONObject jsonObject = new JSONObject();
        //easyui分页功能传入参数集合list和多少条记录total
        jsonObject.put("list", list);

        String json = jsonObject.toJSONString();
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println(json);
    }
}

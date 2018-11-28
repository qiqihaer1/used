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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet3",value= "/Servlet3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        ErrorNameController errorNameController =new ErrorNameController();
        List<Hbase> list = errorNameController.ScanData3();

//建立JSON对象，放入属性
        JSONObject jsonObject = new JSONObject();
        //easyui分页功能传入参数集合list和多少条记录total
       // jsonObject.put("list", list);
        Map map=new HashMap();
        map.put("list",list);
        String json = jsonObject.toJSONString(map);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println(json);

    }
}

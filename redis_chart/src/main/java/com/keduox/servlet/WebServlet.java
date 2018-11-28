package com.keduox.servlet;

import com.alibaba.fastjson.JSONObject;
import com.keduox.util.Redis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@javax.servlet.annotation.WebServlet(name = "Web",value= "/Web")
public class WebServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取redis里的省份数据
        Redis redis = Redis.getInstance();
        List<Map> list = redis.getWebRedis();
        //建立JSON对象，放入属性
        JSONObject jsonObject = new JSONObject();
        //不能这么用
        //jsonObject.put("List", list);
        //转为json字符串
        String json = jsonObject.toJSONString(list);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println(json);
    }
}

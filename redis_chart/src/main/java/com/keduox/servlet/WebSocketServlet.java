package com.keduox.servlet;

import com.alibaba.fastjson.JSONObject;
import com.keduox.util.Redis;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * 这个类即实现了进行数据库操作的Servlet类，又实现了Websocket的功能.
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket，类似Servlet的注解mapping；
// servlet的注册放在了web.xml中。
@ServerEndpoint(value = "/websocket")
public class WebSocketServlet extends HttpServlet {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketServlet> webSocketSet = new CopyOnWriteArraySet<WebSocketServlet>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    private  javax.websocket.Session session=null;
    private  String message1;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String msg;
        String name=request.getParameter("name");
        //这里submit是数据库操作的方法，如果插入数据成功，则发送更新信号
        if(onMessage(message1,session)){
            //发送更新信号!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            sendMessage();
            msg="ok!";
            //建立JSON对象，放入属性
            JSONObject jsonObject = new JSONObject();
            //不能这么用
            //jsonObject.put("List", list);
            //转为json字符串
//            Redis redis = Redis.getInstance();
//            List<Map> mapList = redis.getWebRedis();
            List<Map> list = showMap();
            String json = jsonObject.toJSONString(list);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.println(json);
        }else {
            msg="error!";
        }
        response.sendRedirect("user.jsp?msg="+msg);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse reponse) throws ServletException, IOException {
        doGet(request,reponse);
    }

    /**
     * 向数据库插入一个name
     * @param
     * @return
     */
    public  List<Map> showMap(){
        List<Map> list = new ArrayList<>();

        String[] arraydata=new String[]{"ie","chrome","firefox","safari","opera"};
        //连接redis
        Jedis jedis = new Jedis("localhost",6379);
        //获取redis里的数据
        for (int i=0;i<arraydata.length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("name",arraydata[i]);
            map.put("value",jedis.get(arraydata[i]));
            list.add(map);
        }
        System.out.println(list);
        //关闭连接
        jedis.close();
        return list;
    }

    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     * 接收到客户端消息时使用，这个例子里没用，我补充了
     */
    @OnMessage
    public boolean onMessage(String message, Session session){
        sendMessage(message);
        System.out.println("=====客户端发来消息:" + message);
        System.out.println("======websocket 数量:" + webSocketSet.size());
        //群发消息
        for(WebSocketServlet wss: webSocketSet) {
          wss.sendMessage("服务端发来的消息"); //向客户端发送消息
         }
        if(message=="1"){
            System.out.println("接收到了JS的数据");
            return true;
        }
        System.out.println("Message from " + session.getId() + ": " + message);
         return false;
    }
//send message  发送消息处理方法
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
          System.out.println("===============发送了消息:" + message);
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     * 建立websocket连接时调用
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
        try {
            this.session=session;
            //将客户加入到set中
            webSocketSet.add(this);
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     * 关闭连接时调用
     */
    @OnClose
    public void onClose(Session session){
        //从set中删除
        webSocketSet.remove(this);
        System.out.println("Session " +session.getId()+" has closed!");
    }

    /**
     * 注意: OnError() 只能出现一次. 异常时抛出异常
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
        throw new IllegalArgumentException(t);
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     */
    public void sendMessage() throws IOException{
        //群发消息
        for(WebSocketServlet item: webSocketSet){
            try {
                item.session.getBasicRemote().sendText("1");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }
}


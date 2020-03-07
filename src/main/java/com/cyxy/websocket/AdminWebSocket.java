package com.cyxy.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/adminWebsocket/{adminName}")
//@ResponseBody
@Component
public class AdminWebSocket {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String , AdminWebSocket> webSocketConcurrentHashMap = new ConcurrentHashMap<>();
    private Session session;

    @OnMessage
    public void onMessage(String message,Session session){
        //return "adadad";
    }

    @OnOpen
    public void onOpen(@PathParam("adminName") String name, Session session){
        this.session = session;
        webSocketConcurrentHashMap.putIfAbsent(name,this);
        addOnlineCount();
        System.out.println( "有新游客[" + name + "]加入聊天室!");
    }

    @OnClose
    public void onClose(@PathParam("adminName") String name, Session session){
        this.session = session;
        webSocketConcurrentHashMap.remove(name);
        subOnlineCount();
        System.out.println( "[" + name + "]退出聊天室!");
    }

    @OnError
    public void onError(@PathParam("adminName") String name, Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        AdminWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        AdminWebSocket.onlineCount--;
    }
}

package com.renly.listener;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ${任岚杨}
 * @title: com.renly.listener.OnlineListener
 * @projectName rlyJavaEE
 * @description: 监听在线用户(网页版)
 * @date 2019/5/2110:13
 */
//@WebListener
public class OnlineListener  implements HttpSessionListener {
    /**
     * 用户与服务器之间开始session触发
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        //获取session ID
        String sessionId = session.getId();
        //如果是一次新的对话
        if (session.isNew()){
            String user = (String) session.getAttribute( "user" );
            //未登录用户当游客处理
            user=(user==null)?"游客":user;
            Map online = (Map) servletContext.getAttribute( "online" );
            if (online==null){
                online = new HashMap(  );
            }
            //已登录用户信息放到Map
            online.put( sessionId,user );
            servletContext.setAttribute( "online",online );
        }
    }

    /**
     * 用户与服务器之间session断开触发
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        String sessionId= session.getId();
        Map online = (Map)servletContext.getAttribute( "online" );
        if (online!=null) {
            //删除用户在线信息
            online.remove( sessionId );
        }
        servletContext.setAttribute( "online",online );
    }
}

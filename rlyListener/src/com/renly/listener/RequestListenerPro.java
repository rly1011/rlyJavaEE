package com.renly.listener;

import com.renly.util.DbDao;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ${任岚杨}
 * @title: com.renly.listener.OnlineListenerPro
 * @projectName rlyJavaEE
 * @description:
 * 1.监听多少人在线
 * 2.监听用户停留在哪个页面
 * 3.监听本次在线停留时间
 * 4.监听用户访问IP
 * @date 2019/5/2112:31
 */


/**
 * 定义ServletRequestListener，负责监听每次用户请求：每次用户请求到达，
 * 如果是新的用户会话，放入数据库，如果是老的用户会话，更新已有的在线记录
 * @author rly10
 */
@WebListener
public class RequestListenerPro implements ServletRequestListener {
    /**
     * 用户请求到达、被初始化触发该方法
     * @param servletRequestEvent
     */
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        HttpSession session = request.getSession();
        //获取sessionID
        String sesssionId= session.getId();
        //获取访问的IP和正在访问的页面
        String ip = request.getRemoteAddr();
        String page = request.getRequestURI();
        String user = (String) session.getAttribute( "user" );

        //未登陆用户当游客处理
        user = (user==null)?"游客":user;
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date(  ) );
        String sql;
        try {
            List rs = DbDao.query( "select *  from online_inf where sessionId= '"+sesssionId+"'" );
                //如果是老用户，修改数据
            if(rs.size()!=0){
                sql ="update online_inf set ip='"+ip+"',page='"+page+"',systime='"+now+"' where sessionId='"+sesssionId+"';";
                DbDao.modify(sql);

            }else {
                //新用户
                sql = "insert into online_inf value('"+sesssionId+"','"+ip+"','"+page+"','"+now+"','"+user+"')";
                DbDao.insert( sql );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

}

package com.renly.listener;

import com.renly.util.DbDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ${任岚杨}
 * @title: OnlineListenerPro
 * @projectName rlyJavaEE
 * @description:
 *  定义ServletContextListener,他负责启动一条后台线程，定期检查在线记录，
 *  删除那些长时间未重新请求的记录
 * @date 2019/5/2113:48
 */
@WebListener
public class OnlineListenerPro implements ServletContextListener {
    public final int MAX_MILLIS= 10 * 1000;

    /**
     * 应用启动触发该方法
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
       ServletContext application= servletContextEvent.getServletContext();
        try {
            application.setAttribute( "conn", DbDao.getConn() );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------看天下-------------------------------------");
        new Timer( 1000 * 5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList list = (ArrayList) DbDao.query( "select  *  from online_inf");
                    String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date(  ) );
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    StringBuffer sb = new StringBuffer(  "('");
                    Date date1 = sdf.parse(now);
                    System.out.println("-------------------------------定时验证--------------------");
                    for (int i = 0; i <list.size() ; i++) {
                        Object[] list1 = (Object[]) list.get( i );
                        System.out.println(list1[3]);
                        Date date2 =new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( list1[3].toString() );

                       //如果距离上次访问时间超过指定时间
                        if ((date1.getTime() - date2.getTime() )> MAX_MILLIS) {
                            sb.append( list1[0] );
                            sb.append( "','" );
                        }
                    }
                    if (sb.length()>3){
                        sb.setLength( sb.length()-3 );
                        sb.append( "')" );
                        DbDao.modify( "delete from online_inf where sessionId  in " +sb.toString());
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

package com.renly.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * @author ${任岚杨}
 * @title: GetConnListener
 * @projectName rlyJavaEE
 * @description: 使用Druid连接池创建一个获取数据库连接的Listener，
 * 该Listener会在应用启动时获取数据库连接，
 * 并将连接设置成application范围内的属性
 * @date 2019/5/2320:44
 */
@WebListener
public class GetConnListener implements ServletContextListener {
    // 第一步：定义数据驱动名称和url路径
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/rlyjavaee?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    // 第二步：定义数据库用户名和密码
    static final String USER = "root";
    static final String PASS = "zxcvbnm2019";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //取得该应用的ServletContext实例
        ServletContext application = servletContextEvent.getServletContext();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setUrl(DB_URL);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        try {
           DruidPooledConnection pooledConnection = dataSource.getConnection();
            //将数据库连接设置成application范围内的属性
            application.setAttribute( "conn", pooledConnection);
            // 启用监控统计功能
            dataSource.setFilters("stat");
            dataSource.setPoolPreparedStatements(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //当应用关闭时，回收连接
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        DruidPooledConnection pooledConnection = (DruidPooledConnection) application.getAttribute( "conn" );
        if(pooledConnection !=null){
            try {
                pooledConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.renly.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.*;
import java.util.List;

/**
 * @author ${任岚杨}
 * @title: DbDao
 * @projectName rlyJavaEE
 * @description: JDBC版本DAO层
 * @date 2019/5/2113:13
 */
public  class DbDao {
    // 第一步：定义数据驱动名称和url路径
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost/rlyjavaee?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    // 第二步：定义数据库用户名和密码
    private static String USER = "root";
    private static String PASS = "zxcvbnm2019";

    private static Connection conn;

    /**
     *获取数据库连接
     * @return
     */
    public static DruidDataSource getConn() throws ClassNotFoundException, SQLException {
        DruidDataSource dataSource =null;
        if(conn == null){
            //第三步：注册驱动
            Class.forName(JDBC_DRIVER);
            //第四步：连接数据库，得到Connection对象
            System.out.println( "正在连接数据库..." );
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName(JDBC_DRIVER);
            dataSource.setUsername(USER);
            dataSource.setPassword(PASS);
            dataSource.setUrl(DB_URL);
            dataSource.setInitialSize(15);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(20);
            // 启用监控统计功能
            dataSource.setFilters("stat");
            // for mysql
            dataSource.setPoolPreparedStatements(false);

//          配置访问地址：http://localhost:8080/druid/sql.html
        }
        return dataSource;
    }

    /**
     * 新增
     * @param sql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void insert(String sql) throws SQLException, ClassNotFoundException {
        DruidDataSource druidDataSource = getConn();
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        queryRunner.update( sql );
        druidDataSource.close();
    }

    /**
     * 查询
     * @param sql
     * @return
     */
    public static List query(String sql) throws SQLException, ClassNotFoundException{
        DruidDataSource druidDataSource = getConn();
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        List<Object[]> list = queryRunner.query( sql,new ArrayListHandler() );
        druidDataSource.close();
        return list;
    }

    /**
     * 修改
     * @param sql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static  void  modify(String sql) throws SQLException, ClassNotFoundException{
        DruidDataSource druidDataSource = getConn();
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        queryRunner.update( sql );
        druidDataSource.close();
    }

    /**
     * 删除
     * @param sql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  static  void delete(String sql) throws SQLException, ClassNotFoundException {
        DruidDataSource druidDataSource = getConn();
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        queryRunner.update( sql );
        druidDataSource.close();
    }

//
//    /**
//     * 查询或修改数据
//     * @return
//     */
//    public static Integer getAll(String sessionId,String ip,String page,String systime,String user) {
//        Connection conn = getConn();
//        String sql = "select * from online_inf where sessionid= '"+sessionId+"'";
//        PreparedStatement pstmt;
//        try {
//            String sql2=null;
//            pstmt = (PreparedStatement)conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()){
//                Connection conn2 = getConn();
//                sql2 = "update online_inf set ip='"+ip +"',page='"+page+"',systime='"+systime+"',user='"+user+"'  where sessionId='"+sessionId+"'";
//                PreparedStatement pstmt2 = conn2.prepareStatement( sql2 );
//                pstmt2.executeUpdate();
//                pstmt2.close();
//            }else {
//                Connection conn2 = getConn();
//                sql2="insert into online_inf values(?,?,?,?,?)";
//                PreparedStatement pstmt2 = conn2.prepareStatement( sql2 );
//                pstmt2 = (PreparedStatement) conn2.prepareStatement(sql2);
//                pstmt2.setString(1, sessionId);
//                pstmt2.setString(2, ip);
//                pstmt2.setString(3, page);
//                pstmt2.setString(4, systime);
//                pstmt2.setString(5,user);
//                pstmt2.executeUpdate();
//                pstmt2.close();
//            }
//            pstmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//    public static Integer query() {
//        Connection conn = getConn();
//        Connection conn2 = getConn();
//        String sql = "select * from online_inf ";
//        PreparedStatement pstmt;
//        PreparedStatement pstmt2;
//        try {
//            pstmt = (PreparedStatement)conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//            StringBuffer beRemove = new StringBuffer( "(" );
//            while (rs.next()){
//                if (System.currentTimeMillis()-(int)rs.getLong( "systime" )>(60*1000)){
//                    beRemove.append( "'" );
//                    beRemove.append( rs.getString( 1 ) );
//                    beRemove.append( "'," );
//                };
//            }
//            pstmt.close();
//            conn.close();
//            //有需要的删除记录
//            if (beRemove.length()>3){
//                beRemove.setLength( beRemove.length()-3 );
//                beRemove.append( ")" );
//                //删除所有超过指定时间未重新请求的记录
//                String sql2 = "delete from online_inf where sessionId in "+beRemove;
//                pstmt2 = (PreparedStatement)conn2.prepareStatement(sql2);
//                pstmt2.executeQuery();
//                pstmt2.close();
//                conn2.close();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}

package com.ff.util;

import java.sql.*;

public class JDBCUtil {

    /*
    在web项目中记载一次
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //连接数据库
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_db?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        Connection connection = DriverManager.getConnection(url, "root", "wyf523");
        return connection;
    }

    //用于执行新增修改,删除sql
    public static void excuteUpdateSql(PreparedStatement ps,Object[] objects) throws SQLException {
        if (objects != null){
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i+1,objects[i]);
            }
        }
        ps.executeUpdate();
    }

    //用于执行新增修改,删除sql,并获取生成的主键值,返回
    public static int excuteUpdateSqlReturnKey(PreparedStatement ps,Object[] objects) throws SQLException {
        if (objects != null){
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i+1,objects[i]);
            }
        }
        ps.executeUpdate();
        int id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){//只有一个结果
            id = rs.getInt(1);
        }
        rs.close();
        return id;
    }

    //为空关闭
    public static void closeAll(Connection connection, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

}

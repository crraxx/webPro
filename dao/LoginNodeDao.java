package com.ff.dao;

import com.ff.bean.Course;
import com.ff.bean.Grade;
import com.ff.bean.Login_infor;
import com.ff.bean.Student;
import com.ff.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginNodeDao {

    //从数据库中查询日志数据并封装到loginInforsList对象中
    public List<Login_infor> findLoginList() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Login_infor> loginInforsList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select *  from login_infor ";
            ps = connection.prepareStatement(sql);
            rs =  ps.executeQuery();
            while(rs.next()){
                Login_infor login_infor = new Login_infor();
                login_infor.setId(rs.getInt("id"));
                login_infor.setAccount(rs.getString("account"));
                login_infor.setIp(rs.getString("ip"));
                login_infor.setLogin_time(rs.getTimestamp("login_time"));
                loginInforsList.add(login_infor);
            }
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
        return loginInforsList;
    }
}

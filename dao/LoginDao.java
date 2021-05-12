package com.ff.dao;

import com.ff.bean.Admin;
import com.ff.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static Admin checkAdmin(String account, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from admin where account = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, account);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setPasswor(rs.getString("password"));
            }
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
        return admin;
    }
}

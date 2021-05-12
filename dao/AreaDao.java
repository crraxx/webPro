package com.ff.dao;

import com.ff.bean.Area;
import com.ff.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {
    public  List<Area> findArea(String pid) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Area> list = null;
        try {
            list = new ArrayList<>();
            connection = JDBCUtil.getConnection();
            String sql = "select * from t_area where pid = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt("id"));
                area.setName(rs.getString("name"));
                list.add(area);
            }
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
        System.out.println(list);
        return list;
    }
}

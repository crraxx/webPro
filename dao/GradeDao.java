package com.ff.dao;

import com.ff.bean.Course;
import com.ff.bean.Grade;
import com.ff.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {

    //从数据库中查询课程数据并封装到course对象中
    public List<Grade> findGradeList() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grade> courseList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from grade";
            ps = connection.prepareStatement(sql);
            rs =  ps.executeQuery();
            while(rs.next()){
                Grade grade = new Grade();
                grade.setId(rs.getInt("id"));
                grade.setName(rs.getString("name"));
                courseList.add(grade);
            }
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
        return courseList;
    }

    //接收从表单中提交过来的数据,向数据库中保存
    public void saveGrade(String id, String name) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            //保存学生与课程的关系
            String sql = "insert into grade(id,name)"+"values(?,?)";
            ps = connection.prepareStatement(sql);
            Object[] objects1 = {id,name};
            JDBCUtil.excuteUpdateSql(ps,objects1);
            connection.commit();//当这一次所有操作都没有问题后,提交事务
            findGradeList();
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
    }

    //删除年级信息,先删除课程学生表中的数据再删除课程表中的数据
    public void deleteGrade(String ids) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] idArr = ids.split(",");
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            for (String id:idArr) {
                String sql ="delete from grade where id= ? ";
                ps = connection.prepareStatement(sql);
                Object[] objects = {id};
                JDBCUtil.excuteUpdateSql(ps,objects);
            }
            connection.commit();//当这一次所有操作都没有问题后,提交事务
        }finally {
            JDBCUtil.closeAll(connection, ps,rs);
        }
    }
}

package com.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ff.bean.*;
import com.ff.util.JDBCUtil;

public class StudentDao {

    //从数据库中查询学生数据并封装到student对象中
    public List<Student> findStudentList() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT \n" +
                    "               stu.id,stu.num,\n" +
                    "               stu.name sname,\n" +
                    "               stu.sex,\n" +
                    "               stu.birthday,\n" +
                    "               stu.mobile,\n" +
                    "               stu.address,\n" +
                    "               stu.oper_time,\n" +
                    "               g.name gname,\n" +
                    "               GROUP_CONCAT(co.name) cname,\n" +
                    "               a.account\n" +
                    "           FROM student stu LEFT JOIN grade g ON stu.gradeId = g.id\n" +
                    "                                    LEFT JOIN student_course sc ON stu.id = sc.studentId\n" +
                    "                                    LEFT JOIN course co ON sc.courseId = co.id\n" +
                    "                                    LEFT JOIN admin a ON stu.adminId = a.id\n" +
                    "             GROUP BY stu.num,stu.name,stu.sex,\n" +
                    "               stu.id,\n" +
                    "               stu.birthday,\n" +
                    "               stu.mobile,\n" +
                    "               stu.address,\n" +
                    "               stu.oper_time,\n" +
                    "               g.name,\n" +
                    "               a.account";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setNum(rs.getInt("num"));
                student.setName(rs.getString("sname"));
                student.setSex(rs.getString("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setMobile(rs.getString("mobile"));
                student.setAddress(rs.getString("address"));
                student.setOper_time(rs.getTimestamp("oper_time"));
                student.setGname(rs.getString("gname"));
                student.setCname(rs.getString("cname"));
                student.setAccount(rs.getString("account"));
                studentList.add(student);
            }
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
        return studentList;
    }

    //从数据库中查询课程数据并封装到course对象中
    public List<Course> findCourseList() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from course";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courseList.add(course);
            }
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
        return courseList;
    }

    //从数据库中查询年级数据并封装到grade对象中
    public List<Grade> findGrade() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grade> gradeList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from grade";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setId(rs.getInt("id"));
                grade.setName(rs.getString("name"));
                gradeList.add(grade);
            }
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
        return gradeList;
    }

    //接收从表单中提交过来的数据,向数据库中保存
    public void saveStudent(String num, String name, String sex, String birthday,
                            String mobile, String address, String gradeId, String[] course, int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grade> gradeList = new ArrayList<>();
        try {
            /*
            数据库事务:由若干个操作组成的一次对数据库的操作过程
            原子性:这些操作要么都成功要么都失败
            jdbc默认是执行完后自动事务提交,数据库只有在事务执行后真正执行sql
             */
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            //保存学生信息到学生表
            String sql = "INSERT INTO student\n" +
                    "(num,NAME,sex,birthday,mobile,address,gradeId,adminId,oper_time)\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?) ";
            //执行后返回生成的主键
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Object[] objects = {num, name, sex, birthday, mobile, address, gradeId, id, new Date()};
            //获得学生id
            int studentId = JDBCUtil.excuteUpdateSqlReturnKey(ps, objects);
            //保存学生与课程的关系
            sql = "insert into student_course(studentId,courseId)" + "values(?,?)";
            ps = connection.prepareStatement(sql);
            for (String courseId : course) {
                Object[] objects1 = {studentId, courseId};
                JDBCUtil.excuteUpdateSql(ps, objects1);
            }
            connection.commit();//当这一次所有操作都没有问题后,提交事务
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }

    }

    public void deleteStudent(String ids) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] idArr = ids.split(",");
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            for (String id : idArr) {
                String sql = "delete from student_course where studentId= ? ";
                ps = connection.prepareStatement(sql);
                Object[] objects = {id};
                JDBCUtil.excuteUpdateSql(ps, objects);
                //删除学生
                sql = "delete  from student where id= ? ";
                ps = connection.prepareStatement(sql);
                JDBCUtil.excuteUpdateSql(ps, objects);
            }
            connection.commit();//当这一次所有操作都没有问题后,提交事务
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
    }

    public static void inserts(String account, String ip, String login_time) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            String update = "insert into login_infor(account,ip,login_time)" + "values(?,?,?)";
            ps = connection.prepareStatement(update);
            Object[] objects = {account, ip, login_time};
            JDBCUtil.excuteUpdateSql(ps, objects);
            connection.commit();//当这一次所有操作都没有问题后,提交事务
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
    }

    public Student findStudentById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = new Student();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT\n" +
                    "  stu.id,\n" +
                    "  stu.name,\n" +
                    "  stu.num,\n" +
                    "  stu.sex,\n" +
                    "  stu.birthday,\n" +
                    "  stu.mobile,\n" +
                    "  stu.address,\n" +
                    "  stu.gradeId,\n" +
                    "  GROUP_CONCAT(sc.courseId) ck \n" +
                    "FROM\n" +
                    "  student stu\n" +
                    "  LEFT JOIN student_course sc\n" +
                    "    ON stu.id = sc.studentId\n" +
                    "WHERE stu.id = ? ";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setNum(rs.getInt("num"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setMobile(rs.getString("mobile"));
                student.setAddress(rs.getString("address"));
                student.setGradeId(rs.getInt("gradeId"));
                student.setCkCourseId(rs.getString("ck"));
            }
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
        return student;
    }

    public void updateStudent(String id, String name, String sex, String birthday, String mobile, String address, String gradeId, String[] course, int id1) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grade> gradeList = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置手动提交事务
            //修改学生表信息
            String sql = "UPDATE student SET NAME = ?,\n" +
                    "sex=?,\n" +
                    "birthday=?,\n" +
                    "mobile=?,\n" +
                    "address=?,\n" +
                    "gradeId=?,\n" +
                    "adminId=?,\n" +
                    "oper_time=?\n" +
                    "WHERE id = ?";
            //执行后返回生成的主键
            ps = connection.prepareStatement(sql);
            Object[] objects = { name, sex, birthday, mobile, address, gradeId, id1, new Date(),id};
            JDBCUtil.excuteUpdateSql(ps, objects);
            //学生选课表删除
            sql="delete from student_course where studentId=?";
            Object[] objects1 = {id};
            ps = connection.prepareStatement(sql);
            JDBCUtil.excuteUpdateSql(ps, objects1);

            //重新添加学生选课记录
            sql = "insert into student_course(studentId,courseId)" + "values(?,?)";
            ps = connection.prepareStatement(sql);
            for (String courseId : course) {
                Object[] objects2= {id, courseId};
                JDBCUtil.excuteUpdateSql(ps, objects2);
            }
            connection.commit();//当这一次所有操作都没有问题后,提交事务
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
    }

    public int findNum(String num) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int res = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT count(*) from student where num = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,num);
            rs = ps.executeQuery();
            while (rs.next()) {
                /*if (rs.getString("num").equals(num)){
                    return true;
                }*/
                res = rs.getInt(1);
            }
        } finally {
            JDBCUtil.closeAll(connection, ps, rs);
        }
        return res;
    }
}

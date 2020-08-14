package com.yibo.parking.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;

public class GeneratorUser {

    private static final String URL = "jdbc:mysql://localhost:3306/cloud?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            con = getConnection();
            String sql = "insert into user(id, username, password, sex, phone, email, regist_time, login_time, login_ip) values(?,?,?,?,?,?,?,?,?)";
            if (con != null) {
                stmt = con.prepareStatement(sql);
                stmt.setString(1, EntityIdGenerate.generateId());
                stmt.setString(2, EntityIdGenerate.generateId());
                stmt.setString(3, "admin");
                stmt.setString(4, passwordEncoder.encode("123"));
                stmt.setString(4, "1");
                stmt.setString(4, "15769271840");
                stmt.setString(4, "18204099093@163.com");
                stmt.setString(5, "2020-04-26 00:00:00");
                stmt.setString(6, null);
                stmt.setString(7, "");
            }
            if (stmt != null) {
                int result =stmt.executeUpdate();// 返回值代表收到影响的行数
            }
            System.out.println("插入成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(rs, stmt, con);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return   DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库的连接
    public static void close(ResultSet rs,Statement stmt,Connection con) throws SQLException {
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }
}

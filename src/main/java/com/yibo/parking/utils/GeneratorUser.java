package com.yibo.parking.utils;

import java.sql.*;

public class GeneratorUser {

    private static final String URL = "";
    private static final String NAME = "";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        Statement stmt = conn.createStatement();
        String sql = "";
        ResultSet rs = stmt.executeQuery("select * from user");//选择import java.sql.ResultSet;
        System.out.println();
    }
}

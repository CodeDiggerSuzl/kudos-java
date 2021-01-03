package com.suzl.basic.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * <p>Jdbc step test</p>
 *
 * @author suzailong
 * @date 2021/1/3 3:45 下午
 */
public class JDBCTest {
    private static final String JDBC_URL      = "jdbc:mysql://localhost:3306/test";
    private static final String JDBC_USER     = "root";
    private static final String JDBC_PASSWORD = "password";


    @Test // 使用 Statement
    public void statementTest() throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        int gender = rs.getInt(4);
                    }
                }
            }
        }
    }

    @Test
    public void prepareStatementTest() throws SQLException {
        // Conn
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // prepare statement
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")) {
                ps.setObject(1, "m");
                ps.setObject(2, 3);
                try (ResultSet resultSet = ps.getResultSet()) {
                    while (resultSet.next()) {
                        resultSet.getLong("id");
                    }
                }
            }
        }
    }
}

package com.ze.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.fail;

@Log4j2
public class JDBCTest {
    public static Connection oracleConn;

    public static String userid = "gwang_1";
    public static String password = "gwang_1";

    public static String mySQLUrl = "jdbc:mysql://localhost:3307/gwang1";
    public static String oracleUrl = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";


    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        try {
            oracleConn = DriverManager.getConnection(oracleUrl, userid, password);
            log.info(oracleConn);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}

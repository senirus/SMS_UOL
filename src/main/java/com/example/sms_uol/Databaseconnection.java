package com.example.sms_uol;
import java.sql.*;

public class Databaseconnection {

    public Connection getDBconnection () throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iityoungwriters", "root", "12345");

        return connection;


    }
}

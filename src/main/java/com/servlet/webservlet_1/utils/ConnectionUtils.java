package com.servlet.webservlet_1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    public static final String url ="jdbc:mysql://localhost:3306/UserList_v1";
    public static final String username ="root";
    public static final String password ="123456";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return connection;
    }
}

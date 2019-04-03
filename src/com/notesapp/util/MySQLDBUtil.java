package com.notesapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBUtil {
    private static Connection conn = null;

    public static Connection getConnection(){
        // assign db parameters
        String url = "jdbc:mysql://localhost:3310/notesapp";
        String user = "root";
        String password = "mysql";

        // create a connection to the database
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            System.out.println("Error connecting to DB : " +e.getMessage());
        }
        return conn;
    }
}

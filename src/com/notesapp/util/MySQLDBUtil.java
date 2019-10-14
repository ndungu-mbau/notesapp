package com.notesapp.util;

import java.sql.*;

public class MySQLDBUtil {
    private static Connection conn = null;
    public static Connection getConnection(){
            // database path, if it's new database, it will be created in the project folder
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:NotesApp.db");
            initialise();
        } catch(Exception e){
            System.err.println("Error connecting to DB" + e.getMessage());
        }
        return conn;
    }

    private static void initialise() throws SQLException {
        Statement pragmaStmt = conn.createStatement();
        Statement createUserTable = conn.createStatement();
        Statement createNoteTable = conn.createStatement();

        pragmaStmt.executeUpdate("PRAGMA foreign_keys = ON;");
        createUserTable.executeUpdate("create table if not exists users(id integer, username varchar(60), password varchar(60), primary key (id));");
        createNoteTable.executeUpdate("create table if not exists notes(id integer, title varchar(60), content text, userId integer, primary key (id), foreign key(userId) references users(id));");
    }
}

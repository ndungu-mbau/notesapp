package com.notesapp.models;

import com.notesapp.util.MySQLDBUtil;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

import java.sql.*;

public class User {
    public  String username, password;
    public int id;

    private static Connection conn = MySQLDBUtil.getConnection();

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int id,String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @NotNull
    public static User insertUserToDB(String username, String password) throws SQLException{
        int userId = 0;
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        int rowAffected = pstmt.executeUpdate();
        System.out.println(rowAffected);
        if(rowAffected == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                userId = rs.getInt(1);
                System.out.println(userId);
            }
        }
       return new User(userId, username, password);
    }

    public static ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               userList.add(new User(rs.getInt("id"),rs.getString("username"), rs.getString("password")));
            }
        } catch(SQLException e){
            System.out.println("Error getting info from db : " +e.getMessage());
        }
        return userList;
    }
}

package com.notesapp.models;

import java.sql.SQLException;
import java.util.ArrayList;
public class UserTest {
    public static void main(String []args) throws SQLException {
        ArrayList<User> userList;
        User.insertUserToDB("mbau2", "mbau001");
        User.insertUserToDB("mbau3", "mbau001");
        User.insertUserToDB("mbau4", "mbau001");

        userList = User.getAllUsers();

        for(User user : userList){
            System.out.printf("%s, %s", user.username, user.password);
        }
    }
}

package com.notesapp.services;

import com.notesapp.models.User;
import com.notesapp.util.UsernameExistsError;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    static UserService ourInstance = new UserService();
    ArrayList<User> userArrayList;
    public User loggedInUser;

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        userArrayList = User.getAllUsers();
    }

    public void createUser(String username, String password) throws UsernameExistsError {
        try {
            User newUser = User.insertUserToDB(username, password);
            userArrayList.add(newUser);
            loggedInUser = newUser;
        } catch(SQLException e){
            if(e.getMessage().startsWith("Duplicate entry")){
                throw new UsernameExistsError();
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean login(String username, String password){
        User loggingInUser = new User(username, password);
        boolean loggedIn = false;

        for(User usr: userArrayList){
            if(loggingInUser.username.equals(usr.username) && loggingInUser.password.equals(usr.password)){
                loggedInUser = usr;
                loggedIn =true;
                break;
            }
        }

        return  loggedIn;
    }
}

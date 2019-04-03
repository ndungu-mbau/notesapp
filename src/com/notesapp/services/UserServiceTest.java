package com.notesapp.services;

import java.util.Scanner;
public class UserServiceTest {
    private static Scanner sc = new Scanner(System.in);
    private static String username, password;
    private static UserService userService = UserService.getInstance();

    public static void main(String []args){
        System.out.println("Enter your username");
        username = sc.nextLine();
        System.out.println("Enter password");
        password = sc.nextLine();

        if(userService.login(username, password)){
            System.out.println("Logged in successfully");
        } else {
            System.out.println("Error logging in. Incorrect username/password combination");
        }

        System.out.println(userService.loggedInUser.id);
    }
}

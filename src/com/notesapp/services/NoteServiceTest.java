package com.notesapp.services;

import com.notesapp.models.Note;

import java.util.*;

public class NoteServiceTest {
    private static Scanner sc = new Scanner(System.in);
    private static String username, password;
    private static UserService userService = UserService.getInstance();

    public static void main(String ...args){
        System.out.println("Enter your username");
        username = sc.nextLine();
        System.out.println("Enter password");
        password = sc.nextLine();

        if(userService.login(username, password)){
            System.out.println("Logged in successfully");
        } else {
            System.out.println("Error logging in. Incorrect username/password combination");
        }

        ArrayList<Note> noteArrayList = Note.getAllNotesByUserId(userService.loggedInUser.id);
        for(Note note: noteArrayList) {
            System.out.printf("id:%d, \ntitle: %s,\ncontent : %s\n userid: %d\n\n", note.id, note.title, note.content, note.userid);
        }
    }
}

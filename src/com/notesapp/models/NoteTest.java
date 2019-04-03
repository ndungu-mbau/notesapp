package com.notesapp.models;

import java.sql.SQLException;

public class NoteTest {
    public static void main(String []args) throws SQLException {
        Note note1 = Note.insertNoteToDB("Hello", "Hello world from our notes app", 2);
        System.out.printf("%s\n%s", note1.title, note1.content);
    }
}

package com.notesapp.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.notesapp.models.Note;

import com.notesapp.views.Home;

public class NotesService {
    public ArrayList<Note> noteArrayList;
    private static UserService userService = UserService.getInstance();
    private static NotesService ourInstance = new NotesService();

    public static NotesService getInstance() {
        return ourInstance;
    }
     public ArrayList<Note> getUserNotes(int id){
        try {
            noteArrayList = Note.getAllNotesByUserId(id);
        } catch(NullPointerException e){
            noteArrayList = new ArrayList<>();
            System.out.println("Error loading data : " +e.getMessage());
        }
        return noteArrayList;
    }

    public void createNote(String title, String content) throws SQLException{
        Note newNote = Note.insertNoteToDB(title, content, userService.loggedInUser.id);
        noteArrayList.add(newNote);
    }

    public void updateNote(Note note, String title, String content) throws SQLException{
        Note updatedNote = Note.updateNoteInDB(note.id, title, content, userService.loggedInUser.id);
        noteArrayList.set(noteArrayList.indexOf(note), updatedNote);
    }

    public void deleteNote(Note note) throws SQLException{
        Note.deleteNoteInDB(note.id);
        noteArrayList.remove(note);
    }
}

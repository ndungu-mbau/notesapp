package com.notesapp.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.notesapp.models.Note;

public class NotesService {
    public ArrayList<Note> noteArrayList;
    private static UserService userService = UserService.getInstance();
    private static NotesService ourInstance = new NotesService();

    public static NotesService getInstance() {
        return ourInstance;
    }
    private NotesService(){
        noteArrayList = Note.getAllNotesByUserId(userService.loggedInUser.id);
    }

    public void createNote(String title, String content) throws SQLException{
        Note newNote = Note.insertNoteToDB(title, content, userService.loggedInUser.id);
        noteArrayList.add(newNote);
    }

    public void updateNote(int noteId, String title, String content) throws SQLException{
        Note updatedNote = Note.updateNoteInDB(noteId, title, content);
        noteArrayList.add(noteId, updatedNote);
    }

    public void deleteNote(int noteId) throws SQLException{
        Note.deleteNoteInDB(noteId);
        noteArrayList.remove(noteId);
    }
}

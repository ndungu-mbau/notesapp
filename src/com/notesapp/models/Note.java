package com.notesapp.models;

import com.notesapp.util.MySQLDBUtil;
import java.sql.*;
import java.util.ArrayList;

public class Note {
    public String title, content;
    public int id, userid;
    private static Connection conn = MySQLDBUtil.getConnection();

    public Note(String title, String content, int userid){
        this.title = title;
        this.content = content;
        this.userid = userid;
    }
    public Note(int id,String title, String content, int userid){
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }
    public static Note insertNoteToDB(String title, String content, int userId) throws SQLException{
        String sql = "INSERT INTO notes(title, content, userid) VALUES(?,?,?)";
        int noteId = 0;

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, title);
        pstmt.setString(2, content);
        pstmt.setInt(3, userId);

        int rowAffected = pstmt.executeUpdate();
        System.out.println(rowAffected);
        if(rowAffected == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                noteId = rs.getInt(1);
                System.out.println(noteId);
            }
        }
        return new Note(noteId, title, content, userId);
    }

    public static void deleteNoteInDB(int noteId) throws SQLException{
        String sql = "DELETE FROM notes WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, noteId);
        pstmt.executeUpdate();
    }

    public static Note updateNoteInDB(int noteId, String title, String content, int userId) throws SQLException{
        String sql = "UPDATE notes SET title=?, content=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, title);
        pstmt.setString(2, content);
        pstmt.setInt(3, noteId);

        pstmt.executeUpdate();

        return new Note(noteId, title, content, userId);
    }

    public static ArrayList<Note> getAllNotesByUserId(int userId){
        ArrayList<Note> notesArrayList = new ArrayList<>();
        String sql = "SELECT * FROM notes WHERE userId=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                do{
                    notesArrayList.add(new Note(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("userid")));
                }while(rs.next());
            } else {
                notesArrayList = new ArrayList<>();
            }
        } catch(SQLException e){
            System.out.println("Error getting info from db : " +e.getMessage());
        } catch(NullPointerException e){
            System.out.println("Error : " +e.getMessage());
        }
        return notesArrayList;
    }

    public String toString(){
        return this.id + " : " +this.title;
    }
}

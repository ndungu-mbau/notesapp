package com.notesapp.util;

public class UsernameExistsError extends Error {
    public UsernameExistsError(){
        super("Username has already been taken try another");
    }
}

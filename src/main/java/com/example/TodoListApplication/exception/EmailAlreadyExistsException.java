package com.example.TodoListApplication.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(){
        super("Email already exists! Please login instead.");
    }
}

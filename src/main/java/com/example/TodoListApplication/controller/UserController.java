package com.example.TodoListApplication.controller;

import com.example.TodoListApplication.Security.config.AuthService;
import com.example.TodoListApplication.dto.TodoCreationDto;
import com.example.TodoListApplication.dto.UserLoginDto;
import com.example.TodoListApplication.dto.UserLoginResponseDto;
import com.example.TodoListApplication.dto.UserSignUpDto;
import com.example.TodoListApplication.service.TodoService;
import com.example.TodoListApplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/to-do")
public class UserController {

    private final UserService userService;
    private final TodoService todoService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(authService.login(userLoginDto));
    }

    @PostMapping("/create-todo")
    public ResponseEntity<TodoCreationDto> createTodo(@Valid @RequestBody TodoCreationDto todoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoRequest));
    }
}
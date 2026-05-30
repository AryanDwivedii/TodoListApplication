package com.example.TodoListApplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}

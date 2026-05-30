package com.example.TodoListApplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreationDto {
    private Long id;
    private String title;
    private String description;
}

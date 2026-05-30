package com.example.TodoListApplication.service;

import com.example.TodoListApplication.dto.TodoCreationDto;
import com.example.TodoListApplication.entity.TodoItem;
import com.example.TodoListApplication.entity.User;
import com.example.TodoListApplication.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public TodoCreationDto createTodo(TodoCreationDto todoRequest) {
        User user = getUserFromSecurityContext();
        TodoItem item = new TodoItem();
        item.setTitle(todoRequest.getTitle());
        item.setDescription(todoRequest.getDescription());
        item.setUser(user);
        TodoItem todoItem = repository.save(item);
        TodoCreationDto response = new TodoCreationDto();
        response.setTitle(todoItem.getTitle());
        response.setDescription(todoItem.getDescription());
        return response;
    }

    private User getUserFromSecurityContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}

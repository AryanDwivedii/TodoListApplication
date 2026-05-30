package com.example.TodoListApplication.service;

import com.example.TodoListApplication.dto.UserLoginDto;
import com.example.TodoListApplication.dto.UserSignUpDto;
import com.example.TodoListApplication.entity.User;
import com.example.TodoListApplication.exception.EmailAlreadyExistsException;
import com.example.TodoListApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto){
        String normalizedEmail = userSignUpDto.getEmail().trim().toLowerCase(Locale.ROOT);

        if(userRepository.existsByEmail(normalizedEmail)){
            throw new EmailAlreadyExistsException();
        }
        User user=new User();
        user.setEmail(normalizedEmail);
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));

        userRepository.save(user);

        log.info("User with email : {} signed up successfully.", userSignUpDto.getEmail());

    }
}

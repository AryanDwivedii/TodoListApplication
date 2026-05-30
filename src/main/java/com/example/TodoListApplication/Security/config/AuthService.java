package com.example.TodoListApplication.Security.config;

import com.example.TodoListApplication.dto.UserLoginDto;
import com.example.TodoListApplication.dto.UserLoginResponseDto;
import com.example.TodoListApplication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    public UserLoginResponseDto login(UserLoginDto userLoginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUserName(), userLoginDto.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateToken(user);

        return new UserLoginResponseDto(user.getId().toString(), token);
    }

}

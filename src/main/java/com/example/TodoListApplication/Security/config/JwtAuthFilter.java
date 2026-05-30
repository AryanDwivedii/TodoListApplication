package com.example.TodoListApplication.Security.config;

import com.example.TodoListApplication.entity.User;
import com.example.TodoListApplication.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("Incoming request {}", request.getRequestURI());

        final String requestHeader = request.getHeader("Authorization");
        if(requestHeader == null || !requestHeader.startsWith("Bearer")) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestHeader.split("Bearer ")[1];
        String userName = authUtil.getUsernameFromToken(token);

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user= userRepository.findByEmail(userName).orElseThrow();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}

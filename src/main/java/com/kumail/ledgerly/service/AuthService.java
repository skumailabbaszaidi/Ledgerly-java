package com.kumail.ledgerly.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kumail.ledgerly.dto.auth.AuthResponse;
import com.kumail.ledgerly.dto.auth.LoginRequest;
import com.kumail.ledgerly.dto.auth.RegisterRequest;
import com.kumail.ledgerly.entity.User;
import com.kumail.ledgerly.exception.EmailAlreadyExistsException;
import com.kumail.ledgerly.exception.InvalidCredentialsException;
import com.kumail.ledgerly.exception.UsernameAlreadyExistsException;
import com.kumail.ledgerly.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already taken");
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        return new AuthResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getUsername());
        
        
        
    }

    public AuthResponse login(LoginRequest request){
        User user;
        if (request.getIdentifier().contains("@")){
         user = userRepository.findByEmail(request.getIdentifier()).orElseThrow(() -> new InvalidCredentialsException("Invalid Credentials"));

        }
       else {
       user =  userRepository.findByUsername(request.getIdentifier()).orElseThrow(() -> new InvalidCredentialsException("Invalid Credentials"));

       }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");


        }
            return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getUsername());
        
    }
}

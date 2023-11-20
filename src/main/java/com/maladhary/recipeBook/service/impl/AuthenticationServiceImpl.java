package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.dto.JwtAuthenticationResponse;
import com.maladhary.recipeBook.dto.SignInRequest;
import com.maladhary.recipeBook.dto.SignUpRequest;
import com.maladhary.recipeBook.model.Role;
import com.maladhary.recipeBook.model.User;
import com.maladhary.recipeBook.repository.UserRepository;
import com.maladhary.recipeBook.service.interfaces.AuthenticationService;
import com.maladhary.recipeBook.service.interfaces.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j //used for logs
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Override
    public User signup(SignUpRequest signUpRequest) {
        log.info("Saving a new user {} inside of the database", signUpRequest.getName());
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

//    @Override
//    public User signup(SignUpRequest signUpRequest) {
//        log.info("Saving a new user {} inside of the database", signUpRequest.getName());
//        User user = new User(
//                signUpRequest.getName(),
//                signUpRequest.getPassword(),
//                signUpRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getName(), signInRequest.getPassword()));
        var user = userRepository.findByName(signInRequest.getName());
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        var token = jwtService.generateToken(user, user.getUserId(), user.getRole());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(token);
        return jwtAuthenticationResponse;
    }
}
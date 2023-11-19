package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.dto.JwtAuthenticationResponse;
import com.maladhary.recipeBook.dto.SignInRequest;
import com.maladhary.recipeBook.dto.SignUpRequest;
import com.maladhary.recipeBook.dto.UserVerify;
import com.maladhary.recipeBook.model.User;
import com.maladhary.recipeBook.repository.UserRepository;
import com.maladhary.recipeBook.service.impl.UserServiceImpl;
import com.maladhary.recipeBook.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public UserVerify verifyToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User userFromDb = userRepository.findByName(user.getUsername());
        UserVerify userVerify = new UserVerify(userFromDb.getUserId(),userFromDb.getUsername(),userFromDb.getRole());
        return userVerify;
    }
}
package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.dto.JwtAuthenticationResponse;
import com.maladhary.recipeBook.dto.SignInRequest;
import com.maladhary.recipeBook.dto.SignUpRequest;
import com.maladhary.recipeBook.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}

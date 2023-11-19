package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.Role;
import com.maladhary.recipeBook.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Optional;

public interface JWTService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails, int userId, Role role);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}

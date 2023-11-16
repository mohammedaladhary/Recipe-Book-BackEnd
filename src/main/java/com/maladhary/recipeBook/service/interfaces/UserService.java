package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    UserDetailsService userDetailsService();
}

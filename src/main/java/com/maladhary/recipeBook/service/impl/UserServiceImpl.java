package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.model.User;
import com.maladhary.recipeBook.repository.UserRepository;
import com.maladhary.recipeBook.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
                UserDetails userDetails =  userRepository.findByName(name);
                if(userDetails == null){
                    throw new UsernameNotFoundException("User not found");
                }
                return userDetails;
            }
        };
    }
}
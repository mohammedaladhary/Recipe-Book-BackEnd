package com.maladhary.recipeBook.repository;

import com.maladhary.recipeBook.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest{

    @Autowired
    private UserRepository userRepository;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 = new User("Mohammed","mohammed@sda.com","Sda@ironhack");
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testJPA(){
        Optional<User> userFromDb = userRepository.findById(user1.getUserId());
        assertTrue(userFromDb.isPresent());
        assertEquals(user1, userFromDb.get());
    }

    @Test
    public void FindByUserName(){
        Optional<User> userFromDb = userRepository.findByName("Mohammed");
        assertEquals(user1.getName(), "Mohammed");
    }
}
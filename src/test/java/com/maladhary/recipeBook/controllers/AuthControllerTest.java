package com.maladhary.recipeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maladhary.recipeBook.dto.SignUpRequest;
import com.maladhary.recipeBook.model.User;
import com.maladhary.recipeBook.repository.UserRepository;
import com.maladhary.recipeBook.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class AuthControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserRepository userRepository;
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    private AuthenticationServiceImpl authenticationServiceImpl;
    User user1;
    User user2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user1 = new User("Raneem", "raneem@gmail.com", "raneem1234");
        user2 = new User("Nawaf", "nawaf@gmail.com", "nawaf1234");
        userRepository.saveAll(List.of(user1, user2));
    }

    @AfterEach
    public void tearDown() {
        if (user1 != null && userRepository.existsById(user1.getUserId())) {
            userRepository.deleteById(user1.getUserId());
        }

        if (user2 != null && userRepository.existsById(user2.getUserId())) {
            userRepository.deleteById(user2.getUserId());
        }
    }

    @Test
    void testAddUser() throws Exception {
        // Given
        User mockUser = new User("JohnDoe", "john.doe@example.com", "password123");
        when(authenticationServiceImpl.signup(any(SignUpRequest.class))).thenReturn(mockUser);

        // Convert the object to JSON
        String requestBody = mapper.writeValueAsString(mockUser);

        // When
        MvcResult result = mockMvc.perform(post("/auth/signup")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertTrue(result.getResponse().getContentAsString().contains("User added successfully"));

        // Verify that the service method was called
        verify(authenticationServiceImpl, times(1)).signup(any(SignUpRequest.class));
    }
}
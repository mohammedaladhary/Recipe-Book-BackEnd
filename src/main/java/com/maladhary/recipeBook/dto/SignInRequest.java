package com.maladhary.recipeBook.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String name;
    private String password;
}

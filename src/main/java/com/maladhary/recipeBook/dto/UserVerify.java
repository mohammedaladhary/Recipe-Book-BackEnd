package com.maladhary.recipeBook.dto;

import com.maladhary.recipeBook.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerify {
    private int userId;
    private String name;
    private Role role;
}
package com.prasant.instagram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInInput {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

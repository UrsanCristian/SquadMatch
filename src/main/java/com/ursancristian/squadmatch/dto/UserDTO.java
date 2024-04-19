package com.ursancristian.squadmatch.dto;

import com.ursancristian.squadmatch.validators.PasswordMatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Valid
@PasswordMatch
public class UserDTO {

    @NotNull
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Username cannot be empty.")
    private String username;

    @NotNull
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty(message = "Email cannot be empty.")
    @Email
    private String email;
}

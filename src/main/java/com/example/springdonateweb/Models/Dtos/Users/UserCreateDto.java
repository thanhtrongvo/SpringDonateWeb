package com.example.springdonateweb.Models.Dtos.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserCreateDto {
    @NotBlank(message = "Id is required")
    @Pattern(regexp = "^[0-9]*$", message = "Id must be a number")
    public int userId;

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    public String email;

    @NotBlank(message = "Password is required")
    public String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must be a number")
    public String phoneNumber;

    @NotBlank(message = "Address is required")
    public String address;

}

package com.example.springdonateweb.Models.Dtos.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserUpdateDto {
    public int id;

    @NotBlank(message = "Name is required")
    public String name;

    @Email
    @NotBlank(message = "Email is required")
    public String email;

    @NotBlank(message = "Address is required")
    public String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+62|0)[0-9]{9,12}$", message = "Phone number is invalid")
    public String phoneNumber;

}

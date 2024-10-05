package com.example.springdonateweb.Models.Dtos.Users;

import lombok.*;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {
    private int userId;
    public String name;
    public String email;
    public String password;
    public String roleId;
    public String address;
    public String phoneNumber;

}

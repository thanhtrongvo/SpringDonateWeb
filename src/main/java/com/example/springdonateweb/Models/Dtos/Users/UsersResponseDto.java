package com.example.springdonateweb.Models.Dtos.Users;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto implements Serializable {
    private int userId;
    public String name;
    public String email;
    public String password;
    public Integer roleId;
    public String address;
    public String phoneNumber;

}

package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;

import java.util.List;

public interface IUsersService {
    List<UsersResponseDto> findAll();
    UsersResponseDto findById(int id);
    UsersResponseDto create(UsersResponseDto usersResponseDto);
    UsersResponseDto update(UsersResponseDto usersResponseDto);
    UsersResponseDto delete(int id);
    boolean existsById(int id);
    void changePassword(int id, String password);
    UsersResponseDto findByEmail(String email);
    void sendEmail(String email);

}

package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Users.UserAddDto;
import com.example.springdonateweb.Models.Dtos.Users.UserCreateDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Models.Entities.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUsersService {
    List<UsersResponseDto> findAll();
    UsersResponseDto findById(int id);
    UsersResponseDto register(UserCreateDto userCreateDto);
    UsersResponseDto update(UsersResponseDto usersResponseDto);
    UsersResponseDto delete(int id);
    boolean existsById(int id);
    void changePassword(int id, String password);
    UsersResponseDto findByEmail(String email);
    void sendEmail(String email);
    UserDetails createUserDetailFromRegister(UsersEntity usersEntity);


    List<UsersResponseDto> findByStatusTrue();
    UsersResponseDto findByIdAndStatusTrue(int id);

    UsersResponseDto create(UserAddDto userAddDto);
}

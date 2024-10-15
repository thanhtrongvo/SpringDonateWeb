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
    UserDetails createUserDetailFromRegister(UsersEntity usersEntity);


    List<UsersResponseDto> findByStatusTrue();
    UsersResponseDto findByIdAndStatusTrue(int id);
    UsersResponseDto findByEmailAndStatusTrue(String email);

    UsersResponseDto create(UserAddDto userAddDto);
    boolean existsByEmail(String email);
    boolean checkPassword(int id, String password);
    boolean checkComfirmPassword(String password, String confirmPassword);
    void changePassword(String email, String password);
    void resetPassword(String email);
    void sendOtp(int id, String email);
    void changeEmail(int id, String email);

    boolean checkOtp(int id, String otp);
}

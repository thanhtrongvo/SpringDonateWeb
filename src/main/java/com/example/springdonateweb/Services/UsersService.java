package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Models.Entities.UsersEntity;
import com.example.springdonateweb.Repositories.UsersRepository;
import com.example.springdonateweb.Services.mappers.UsersMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersService  {
//    UsersEntity usersEntity;
//    UsersMapper usersMapper;
//    UsersRepository usersRepository;
//    @Override
//    public UsersResponseDto findByEmail(String email) {
//        Optional<UsersEntity> usersEntity = usersRepository.findByEmail(email);
//        return usersEntity.map(usersMapper::toResponseDto).orElse(null);
//    }

}

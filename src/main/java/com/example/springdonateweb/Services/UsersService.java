package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Entities.UsersEntity;
import com.example.springdonateweb.Repositories.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class UsersService implements UserDetailsService {

    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByEmail(email);
        if (user != null) {
            var springUser = User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRoleId().toString())
                    .build();
            return springUser;
        } else {
            throw new UsernameNotFoundException("User not found");

        }
    }
}

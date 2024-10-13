package com.example.springdonateweb.TestServices;

import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Services.UsersService;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SpringJUnitWebConfig
public class UserTest {

    @Autowired
    private IUsersService usersService;

    @Test
    public void testFindAll() {
        List<UsersResponseDto> users = usersService.findAll();
        assertNotNull(users);

    }
    @Test
    public void create() {
        UsersResponseDto usersResponseDto = new UsersResponseDto();
    }

    @Test
    public void delete() {
        UsersResponseDto delete = usersService.delete(8);
        if (delete == null) {
            System.out.println("User not found");
        } else {
            System.out.println("User deleted");
        }

    }
    @Test
    public void findByIdAndStatusTrue() {
        UsersResponseDto result = usersService.findByIdAndStatusTrue(8);
        System.out.println(result);

    }

}
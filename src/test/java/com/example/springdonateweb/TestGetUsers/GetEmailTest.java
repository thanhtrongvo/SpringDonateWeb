package com.example.springdonateweb.TestGetUsers;

import com.example.springdonateweb.Models.Entities.UsersEntity;
import com.example.springdonateweb.Repositories.UsersRepository;
import com.example.springdonateweb.Config.CustomUserDetailService;
import com.example.springdonateweb.Models.Dtos.Users.UsersDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetEmailTest {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    CustomUserDetailService customUserDetailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmail_Success() {
        // Mock một đối tượng UsersEntity
        UsersEntity userEntity = new UsersEntity();
        userEntity.setEmail("votrong1471@gmail.com");
        userEntity.setPassword("password123"); // Đảm bảo mật khẩu hợp lệ trong đối tượng

        // Khi gọi usersRepository.getUsersByEmail sẽ trả về đối tượng userEntity
        when(usersRepository.findByEmail("votrong1471@gmail.com")).thenReturn(Optional.of(userEntity));

        // Gọi phương thức loadUserByUsername
        UsersDetail userDetails = (UsersDetail) customUserDetailService.loadUserByUsername("votrong1471@gmail.com");

        // Kiểm tra kết quả
        assertNotNull(userDetails);
        assertEquals("votrong1471@gmail.com", userDetails.getUsername());
    }

    @Test
    public void testGetEmail_UserNotFound() {
        // Khi gọi usersRepository.getUsersByEmail sẽ trả về null
        when(usersRepository.getUsersByEmail("votrong1471@gmail.com")).thenReturn(null);

        // Gọi phương thức loadUserByUsername và kiểm tra xem nó có ném ra UsernameNotFoundException không
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailService.loadUserByUsername("votrong1471@gmail.com");
        });
    }
}

package com.example.springdonateweb.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    // Cấu hình encoder cho mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailService;
    }

    // Cấu hình DaoAuthenticationProvider để sử dụng UserDetailsService và PasswordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Cấu hình SecurityFilterChain để xử lý các yêu cầu HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login", "/register", "/register/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/scss/**")
                        .permitAll() // Cho phép truy cập vào các tài nguyên này mà không cần đăng nhập
                        .requestMatchers("/admin/**")
                        .hasAnyAuthority("ROlE_ADMIN")// Chỉ cho phép người dùng có quyền ADMIN truy cập vào các URL bắt đầu bằng /admin
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login

                        .defaultSuccessUrl("/", true) // Điều hướng tới trang /home sau khi đăng nhập thành công
                        .loginProcessingUrl("/login") // URL submit form login
                        .failureUrl("/login?error=true")  // Điều hướng tới trang login nếu đăng nhập thất bại
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL logout tùy chỉnh
                        .logoutSuccessUrl("/login") // Điều hướng tới trang login sau khi logout
                        .invalidateHttpSession(true) // Hủy session
                        .deleteCookies("JSESSIONID") // Xóa cookie session
                )
                .csrf(csrf -> csrf.disable()) // Tắt CSRF (tùy vào yêu cầu của bạn)
                .build();
               // Hiển thị thông tin debug
    }

    // Cấu hình AuthenticationManager để quản lý xác thực
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

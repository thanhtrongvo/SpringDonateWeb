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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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

                        .requestMatchers("/login", "/register", "/register/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/scss/**")
                        .permitAll()
                        .requestMatchers("/admin/**")
                        .hasAnyAuthority("ROlE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login

                        .defaultSuccessUrl("/", true)

                        .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true) // Hủy session
                        .deleteCookies("JSESSIONID")
                )
                .csrf(csrf -> csrf.disable())
                .build();

    }

    // Cấu hình AuthenticationManager để quản lý xác thực
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

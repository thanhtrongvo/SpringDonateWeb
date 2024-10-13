package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Users.UserAddDto;
import com.example.springdonateweb.Models.Dtos.Users.UserCreateDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Models.Entities.UsersEntity;
import com.example.springdonateweb.Repositories.UsersRepository;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import com.example.springdonateweb.Services.mappers.UsersMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {



    private final PasswordEncoder passwordEncoder;

    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;

    private final HttpSession session;

    @Override
    public List<UsersResponseDto> findAll() {
        return usersRepository.findAll().stream().map(usersMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public UsersResponseDto findById(int id) {
        return null;
    }



    @Override
    public UsersResponseDto update(UsersResponseDto usersResponseDto) {
        return null;
    }

    @Override
    @Transactional
    public UsersResponseDto delete(int id) {
        Optional<UsersEntity> usersEntity = usersRepository.findByIdAndStatusTrue(id);
        return usersEntity
                .map(user -> {
                    user.setStatus(false);
                    return usersMapper.toResponseDto(usersRepository.save(user));
                })
                .orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return usersRepository.existsById(id);
    }

    @Override
    public void changePassword(int id, String password) {

    }

    public UsersResponseDto findByEmail(String email) {
        Optional<UsersEntity> usersEntity = usersRepository.findByEmail(email);
        return usersEntity.map(usersMapper::toResponseDto).orElse(null);
    }

    @Override
    public void sendEmail(String email) {

    }

    @Override
    public UsersResponseDto register(UserCreateDto userCreateDto) {
        UsersEntity usersEntity = usersMapper.toEntity(userCreateDto);
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        usersEntity.setStatus(true);
        usersEntity.setRoleId(2);
        UsersEntity result = usersRepository.save(usersEntity);
        UserDetails userDetails = createUserDetailFromRegister(result);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // Lưu Authentication vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        return usersMapper.toResponseDto(result);
    }

    @Override
    public UserDetails createUserDetailFromRegister(UsersEntity usersEntity) {
        return new org.springframework.security.core.userdetails.User(
                usersEntity.getEmail(),
                usersEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(usersEntity.getRoleId().toString()))
        );
    }

    @Override
    public List<UsersResponseDto> findByStatusTrue() {
        List<UsersEntity> usersEntities = usersRepository.findByStatusTrue();
        return usersEntities.stream()
                .map(usersMapper::toResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public UsersResponseDto findByIdAndStatusTrue(int id) {
        Optional<UsersEntity> usersEntity = usersRepository.findByIdAndStatusTrue(id);
        return usersEntity.map(usersMapper::toResponseDto).orElse(null);
    }

    @Override
    public UsersResponseDto create(UserAddDto userAddDto) {
        UsersEntity usersEntity = usersMapper.toEntity(userAddDto);
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        usersEntity.setStatus(true);
        UsersEntity result = usersRepository.save(usersEntity);
        return usersMapper.toResponseDto(result);
    }

//    @Override
//    @Transactional
//    public void resetPassword(int id) {
//        Optional<UsersEntity> personEntity = usersRepository.findByIdAndStatusTrue(id);
//        personEntity.ifPresent(person -> {
//            String rawPass = appUtil.generateRandomString(lengthOfPass);
//            person.setPassword(passwordEncoder.encode(rawPass));
//            try {
//                emailService.sendEmail(
//                        person.getEmail(),
//                        "Cấp lại mật khẩu",
//                        "Mật khẩu mới của bạn là " + rawPass + " , vui lòng không chia sẽ mật khẩu này cho bất kì ai!!"
//                );
//            } catch (MessagingException | UnsupportedEncodingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }


}

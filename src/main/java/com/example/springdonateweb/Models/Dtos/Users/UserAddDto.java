package com.example.springdonateweb.Models.Dtos.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class UserAddDto implements Serializable {
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Mã thành viên chỉ được chứa 10 ký tự và chỉ được là ký tự số")
    private int id;
    @NotBlank(message = "Email không được để trống")
    @Email
    private String email;
    @NotBlank(message = "Tên không được để trống")
    private String name;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10,11}", message = "Số điện thoại chỉ được chứa 10 hoặc 11 ký tự và chỉ được là ký tự số")
    private String phoneNumber;
    private int roleId;


}

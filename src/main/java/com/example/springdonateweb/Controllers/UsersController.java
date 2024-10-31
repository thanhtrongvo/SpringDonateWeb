package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Users.UserAddDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import com.example.springdonateweb.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class UsersController {
    private final IUsersService usersService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("list", usersService.findByStatusTrue());
        String user = SecurityUtil.getSessionUser();
        if(user != null){
            return "user/index";
        }
        return "user/index";

    }
    @PostMapping("/create")
    public String create(
            @Valid
            @ModelAttribute("user") UserAddDto userAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            return "user/create";
        }
        // Kiểm tra xem người dùng đã tồn tại chưa
        if(usersService.existsById(userAddDto.getId())){
            redirectAttributes.addAttribute("error", "User already exists");
            redirectAttributes.addFlashAttribute("user", userAddDto);
            return "redirect:/admin/user/create";
        }
        // Thực hiện tạo người dùng chỉ một lần
        usersService.create(userAddDto);
        redirectAttributes.addAttribute("success", "User created successfully");
        return "redirect:/admin/user";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new UserAddDto());
        return "user/create";
    }
    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable("id") int id,
            Model model

    ){
        UsersResponseDto usersResponseDto = usersService.findById(id);
        if (usersResponseDto == null){
            return "redirect:/admin/user";
        }
        else {
            model.addAttribute("user", usersResponseDto);
        }
        return "user/detail";
    }
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") int id,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if (usersService.delete(id)==null){
            redirectAttributes.addAttribute("error", "User not found");
            return "redirect:/admin/user";
        }
        else {
            redirectAttributes.addAttribute("success", "User deleted successfully");
            return "redirect:/admin/user";
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        UsersResponseDto user = usersService.findById(id);
        if (user == null) {
            return "redirect:/admin/user";
        }
        model.addAttribute("user", user);
        return "user/edit"; // View cho trang chỉnh sửa
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("user") UserAddDto userAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        userAddDto.setId(id); // Đảm bảo id được cập nhật đúng
        usersService.update(userAddDto); // Truyền UserUpdateDto thay vì UserAddDto

        redirectAttributes.addAttribute("success", "User updated successfully");
        return "redirect:/admin/user";
    }





}

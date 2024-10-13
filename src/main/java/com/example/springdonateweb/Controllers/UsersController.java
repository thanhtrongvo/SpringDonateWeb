package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Services.interfaces.IUsersService;
import com.example.springdonateweb.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}

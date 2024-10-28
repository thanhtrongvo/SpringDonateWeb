package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramResponseDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Services.CategoriesService;
import com.example.springdonateweb.Services.ProgramsService;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import com.example.springdonateweb.util.AmountFormatter;
import com.example.springdonateweb.util.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class HomeController {

    private final ProgramsService programsService;
    private final CategoriesService categoriesService;


    IUsersService usersService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "filter", required = false) String filter) {
        String userId = SecurityUtil.getSessionUser(); // Assuming this returns an email now


        if (userId != null) {
            UsersResponseDto user = usersService.findByEmail(userId); // Change to find by email
            if (user != null) {
                // Check if the user has an admin role
                if (user.getRoleId() == 1) {  // Assuming roleId 1 is for Admin
                    return "redirect:/admin/user"; // Redirect to admin page if the user is an admin
                }

                // If not admin, proceed as a normal user
                model.addAttribute("userEmail", user.getEmail());

                return "index"; // Return home.html (ensure this is the correct view name)
            } else {
                return "redirect:/login"; // Redirect to login if user not found
            }
        } else {
            return "index"; // Redirect to login if userId is null
        }
    }

    @GetMapping("/program")
    public String program(Model model) {
        List<ProgramResponseDto> program = programsService.findAll();
        model.addAttribute("program", program);
        model.addAttribute("amountFormatter", new AmountFormatter());
        return "client/program";

    }

    @GetMapping("/program/{id}")
    public String showProgramDetail(@PathVariable int id, Model model) {
        // Assume programService.getProgramById(id) fetches the ProgramResponseDto
        ProgramResponseDto program = programsService.findByProgramId(id);
        model.addAttribute("program", program);
        return "client/program-detail";
    }

    @GetMapping("/test")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/test2")
    public String test() {
        return "admin/user/index";
    }

    @GetMapping("/test3")
    public String testt() {
        return "admin/user/add";
    }


}

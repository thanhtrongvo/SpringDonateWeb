package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Services.interfaces.ICategoriesService;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import com.example.springdonateweb.Services.interfaces.IProgramsService;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ChartController {

    private final IUsersService usersService;
    private final IProgramsService programsService;
    private final IDonationsService donationsService;

    @GetMapping({ "", "/", "/index", "/chart" })
    public String showChartPage(Model model) {

        // Add basic counts
        long totalUsers = usersService.findAll().size();
        long totalPrograms = programsService.findAll().size();

        // Compute total donation amount
        BigDecimal totalDonationsAmount = donationsService.findAll().stream()
                .map(d -> d.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Count total donations
        long totalDonationsCount = donationsService.findAll().size();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalPrograms", totalPrograms);
        model.addAttribute("totalDonationsAmount",
                totalDonationsAmount != null ? totalDonationsAmount : BigDecimal.ZERO);
        model.addAttribute("totalDonationsCount", totalDonationsCount);

        return "admin/Chart/index";
    }
}

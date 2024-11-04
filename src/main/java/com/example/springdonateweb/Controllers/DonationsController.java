package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationResponseDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/donations")
public class DonationsController {

    private final IDonationsService donationsService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("donations", donationsService.findAll());
        return "admin/Donations/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("donation", new DonationCreateDto());
        return "admin/Donations/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DonationCreateDto donationCreateDto) {
        donationsService.create(donationCreateDto);
        return "redirect:/admin/donations";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        DonationResponseDto donation = donationsService.findById(id);
        if (donation == null) return "redirect:/admin/donations";
        model.addAttribute("donation", donation);
        return "admin/Donations/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute DonationUpdateDto donationUpdateDto) {
        donationsService.update(id, donationUpdateDto);
        return "redirect:/admin/donations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        donationsService.delete(id);
        return "redirect:/admin/donations";
    }
}

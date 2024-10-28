package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Donations.DonationAddDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/donations")
public class DonationsController {

    private final IDonationsService donationsService;

    @GetMapping("")
    public String listDonations(Model model) {
        List<DonationDto> donations = donationsService.findAll();
        model.addAttribute("donations", donations);
        return "donations/list";
    }

    @GetMapping("/create")
    public String createDonationForm(Model model) {
        model.addAttribute("donation", new DonationCreateDto());
        return "donations/create";
    }

    @PostMapping("/create")
    public String createDonation(
            @Valid @ModelAttribute("donation") DonationCreateDto donationCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "donations/create";
        }
        donationsService.create(donationCreateDto);
        redirectAttributes.addFlashAttribute("success", "Donation created successfully");
        return "redirect:/admin/donations";
    }

    @GetMapping("/edit/{id}")
    public String editDonationForm(@PathVariable int id, Model model) {
        DonationDto donation = donationsService.findById(id);
        if (donation == null) {
            return "redirect:/admin/donations";
        }
        model.addAttribute("donation", donation);
        return "donations/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDonation(
            @PathVariable int id,
            @Valid @ModelAttribute("donation") DonationUpdateDto donationUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "donations/edit";
        }
        donationsService.update(donationUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Donation updated successfully");
        return "redirect:/admin/donations";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonation(@PathVariable int id, RedirectAttributes redirectAttributes) {
        donationsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Donation deleted successfully");
        return "redirect:/admin/donations";
    }
}

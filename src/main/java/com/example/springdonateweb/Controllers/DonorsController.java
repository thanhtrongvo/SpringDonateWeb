package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Donors.DonorAddDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;
import com.example.springdonateweb.Services.interfaces.IDonorsService;
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
@RequestMapping("/admin/donors")
public class DonorsController {

    private final IDonorsService donorsService;

    @GetMapping("")
    public String listDonors(Model model) {
        List<DonorDto> donors = donorsService.findAll();
        model.addAttribute("donors", donors);
        return "donors/list";
    }

    @GetMapping("/create")
    public String createDonorForm(Model model) {
        model.addAttribute("donor", new DonorCreateDto());
        return "donors/create";
    }

    @PostMapping("/create")
    public String createDonor(
            @Valid @ModelAttribute("donor") DonorCreateDto donorCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "donors/create";
        }
        donorsService.create(donorCreateDto);
        redirectAttributes.addFlashAttribute("success", "Donor created successfully");
        return "redirect:/admin/donors";
    }

    @GetMapping("/edit/{id}")
    public String editDonorForm(@PathVariable int id, Model model) {
        DonorDto donor = donorsService.findById(id);
        if (donor == null) {
            return "redirect:/admin/donors";
        }
        model.addAttribute("donor", donor);
        return "donors/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDonor(
            @PathVariable int id,
            @Valid @ModelAttribute("donor") DonorUpdateDto donorUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "donors/edit";
        }
        donorsService.update(donorUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Donor updated successfully");
        return "redirect:/admin/donors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonor(@PathVariable int id, RedirectAttributes redirectAttributes) {
        donorsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Donor deleted successfully");
        return "redirect:/admin/donors";
    }
}

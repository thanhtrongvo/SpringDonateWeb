package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorResponseDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;
import com.example.springdonateweb.Services.interfaces.IDonorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/donors")
public class DonorsController {

    private final IDonorsService donorsService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("donors", donorsService.findAll());
        return "admin/Donors/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("donor", new DonorCreateDto());
        return "admin/Donors/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DonorCreateDto donorCreateDto) {
        donorsService.create(donorCreateDto);
        return "redirect:/admin/donors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        DonorResponseDto donor = donorsService.findById(id);
        if (donor == null) return "redirect:/admin/donors";
        model.addAttribute("donor", donor);
        return "admin/Donors/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute DonorUpdateDto donorUpdateDto) {
        donorsService.update(id, donorUpdateDto);
        return "redirect:/admin/donors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        donorsService.delete(id);
        return "redirect:/admin/donors";
    }
}

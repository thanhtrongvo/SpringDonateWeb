package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Services.interfaces.ICategoriesService;
import com.example.springdonateweb.Services.interfaces.IProgramsService;
import com.example.springdonateweb.util.CloudinaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/programs")
public class ProgramsController {

    private final IProgramsService programsService;
    private final ICategoriesService categoriesService;
    private final CloudinaryService cloudinaryService;

    @GetMapping("")
    public String listPrograms(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProgramsResponseDto> programPage = programsService.findProgramsByPageAndStatusTrue(page, size);
        model.addAttribute("programs", programPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", programPage.getTotalPages());
        return "admin/Programs/index";
    }

    @GetMapping("/create")
    public String createProgramForm(Model model) {
        model.addAttribute("program", new ProgramCreateDto());
        model.addAttribute("categories", categoriesService.findAll2());
        return "admin/Programs/create";
    }

    @PostMapping("/create")
    public String createProgram(
            @Valid @ModelAttribute("program") ProgramCreateDto programCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/Programs/create";
        }

        MultipartFile file = programCreateDto.getImage();

        if (file != null && !file.isEmpty()) {
            try {
                // Upload ảnh lên Cloudinary
                String imageUrl = cloudinaryService.uploadFile(file);
                programCreateDto.setImageUrl(imageUrl);  // Lưu URL ảnh vào DTO
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Error uploading image.");
                return "redirect:/admin/programs/create";
            }
        }

        programsService.create(programCreateDto);
        redirectAttributes.addFlashAttribute("success", "Program created successfully");
        return "redirect:/admin/programs";
    }

    @GetMapping("/edit/{id}")
    public String editProgramForm(@PathVariable int id, Model model) {
        ProgramsResponseDto program = programsService.findById(id);
        if (program == null) {
            return "redirect:/admin/programs";
        }
        model.addAttribute("program", program);
        return "admin/Programs/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProgram(
            @PathVariable int id,
            @Valid @ModelAttribute("program") ProgramUpdateDto programUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/Programs/edit";
        }
        programUpdateDto.setProgramId(id);
        programsService.update(programUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Program updated successfully");
        return "redirect:/admin/programs";
    }

    @GetMapping("/delete/{id}")
    public String deleteProgram(@PathVariable int id, RedirectAttributes redirectAttributes) {
        programsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Program deleted successfully");
        return "redirect:/admin/programs";
    }
}

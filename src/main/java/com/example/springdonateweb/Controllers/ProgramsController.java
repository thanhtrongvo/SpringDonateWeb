package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Services.interfaces.ICategoriesService;
import com.example.springdonateweb.Services.interfaces.IProgramsService;
import com.example.springdonateweb.util.CloudinaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.springdonateweb.Repositories.CategoriesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Profile("local")
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/programs")
public class ProgramsController {

    private final IProgramsService programsService;
    private final ICategoriesService categoriesService;
    private final CloudinaryService cloudinaryService;

    private static final String UPLOAD_DIR = "src/main/resources/static/img/program/";
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

    // Trang thêm chương trình mới
    @GetMapping("/create")
    public String createProgramForm(Model model) {
        model.addAttribute("program", new ProgramCreateDto());
        model.addAttribute("categories", categoriesService.findAll2()); // Sử dụng findAll2 thông qua dependency injection
        return "admin/Programs/create";  // Đường dẫn tới create.html trong thư mục Programs
    }


    @PostMapping("/create")
    public String createProgram(
            @Valid @ModelAttribute("program") ProgramCreateDto programCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            CloudinaryService cloudinaryService) {

        if (result.hasErrors()) {
            return "admin/Programs/create";
        }

        MultipartFile file = programCreateDto.getImage();
        if (file != null && !file.isEmpty()) {
            try {
                // Upload ảnh lên Cloudinary, lấy URL trả về
                String imageUrl = cloudinaryService.uploadFile(file);
                programCreateDto.setImageUrl(imageUrl); // Lưu URL vào DTO
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Error uploading image to Cloudinary.");
                return "redirect:/admin/programs/create";
            }
        }

        programsService.create(programCreateDto);
        redirectAttributes.addFlashAttribute("success", "Program created successfully");
        return "redirect:/admin/programs";
    }


    // Trang chỉnh sửa chương trình
    @GetMapping("/edit/{id}")
    public String editProgramForm(@PathVariable int id, Model model) {
        ProgramsResponseDto program = programsService.findById(id);
        if (program == null) {
            return "redirect:/admin/programs";
        }
        model.addAttribute("program", program);
        return "admin/Programs/edit";  // Đường dẫn tới edit.html trong thư mục Programs
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
        programUpdateDto.setProgramId(id); // Đảm bảo ID được cập nhật đúng
        programsService.update(programUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Program updated successfully");
        return "redirect:/admin/programs";
    }

    // Xóa chương trình
    @GetMapping("/delete/{id}")
    public String deleteProgram(@PathVariable int id, RedirectAttributes redirectAttributes) {
        programsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Program deleted successfully");
        return "redirect:/admin/programs";
    }
}

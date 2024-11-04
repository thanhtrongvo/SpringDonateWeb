package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Services.interfaces.IProgramsService;
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
@RequestMapping("/admin/programs")
public class ProgramsController {

    private final IProgramsService programsService;

    // Trang hiển thị danh sách chương trình
    @GetMapping("")
    public String listPrograms(Model model) {
        List<ProgramsResponseDto> programs = programsService.findAll();
        model.addAttribute("programs", programs);
        return "admin/Programs/index";  // Đường dẫn tới index.html trong thư mục Programs
    }

    // Trang thêm chương trình mới
    @GetMapping("/create")
    public String createProgramForm(Model model) {
        model.addAttribute("program", new ProgramCreateDto());
        return "admin/Programs/create";  // Đường dẫn tới create.html trong thư mục Programs
    }

    @PostMapping("/create")
    public String createProgram(
            @Valid @ModelAttribute("program") ProgramCreateDto programCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/Programs/create";
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

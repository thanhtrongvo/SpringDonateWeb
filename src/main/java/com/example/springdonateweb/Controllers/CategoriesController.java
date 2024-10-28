package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Categories.CategoryCreateDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryUpdateDto;
import com.example.springdonateweb.Services.interfaces.ICategoriesService;
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
@RequestMapping("/admin/categories")
public class CategoriesController {

    private final ICategoriesService categoriesService;

    @GetMapping("")
    public String listCategories(Model model) {
        List<CategoriesResponseDto> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new CategoryCreateDto());
        return "categories/create";
    }

    @PostMapping("/create")
    public String createCategory(
            @Valid @ModelAttribute("category") CategoryCreateDto categoryCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "categories/create";
        }
        categoriesService.create(categoryCreateDto);
        redirectAttributes.addFlashAttribute("success", "Category created successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable int id, Model model) {
        CategoriesResponseDto category = categoriesService.findById(id);
        if (category == null) {
            return "redirect:/admin/categories";
        }
        model.addAttribute("category", category);
        return "categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(
            @PathVariable int id,
            @Valid @ModelAttribute("category") CategoryUpdateDto categoryUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "categories/edit";
        }
        categoriesService.update(categoryUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Category updated successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id, RedirectAttributes redirectAttributes) {
        categoriesService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully");
        return "redirect:/admin/categories";
    }
}

package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Categories.CategoryCreateDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryResponseDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryUpdateDto;

import java.util.List;

public interface ICategoriesService {
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(int id);
    CategoryResponseDto create(CategoryCreateDto categoryCreateDto);
    CategoryResponseDto update(int id, CategoryUpdateDto categoryUpdateDto);
    void delete(int id);
}

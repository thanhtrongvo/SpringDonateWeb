package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Categories.CategoryCreateDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryUpdateDto;

import java.util.List;

public interface ICategoriesService {
    List<CategoriesResponseDto> findAll();
    CategoriesResponseDto findById(int id);
    CategoriesResponseDto create(CategoryCreateDto categoryCreateDto);
    CategoriesResponseDto update(CategoryUpdateDto categoryUpdateDto);
    void delete(int id);
}

package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICategoriesService {
    CategoriesResponseDto findById(int id);
    List<CategoriesResponseDto> findAll();

    CategoriesResponseDto findByName(String name);
    CategoriesResponseDto create(CategoriesResponseDto categoriesResponseDto);
    CategoriesResponseDto update(CategoriesResponseDto categoriesResponseDto);
    CategoriesResponseDto delete(int id);
    boolean existsById(int id);
    boolean existsByName(String name);

}

package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import com.example.springdonateweb.Services.interfaces.ICategoriesService;
import com.example.springdonateweb.Services.mappers.CategroriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService implements ICategoriesService {

    private final CategoriesEntity categoriesEntity;
    private final CategoriesService categoriesService;
    private final CategoriesResponseDto categoriesResponseDto;
    private final CategroriesMapper categroriesMapper;



    @Override
    public CategoriesResponseDto findById(int id) {
        return categoriesService.findById(id);
    }

    @Override
    public List<CategoriesResponseDto> findAll() {
//        return categoriesService.findAll().stream().map(categoriesService::toResponseDto).collect(Collectors.toList());
        return null;
    }


    @Override
    public CategoriesResponseDto findByName(String name) {
        return null;
    }

    @Override
    public CategoriesResponseDto create(CategoriesResponseDto categoriesResponseDto) {
        return null;
    }

    @Override
    public CategoriesResponseDto update(CategoriesResponseDto categoriesResponseDto) {
        return null;
    }

    @Override
    public CategoriesResponseDto delete(int id) {
        return null;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}

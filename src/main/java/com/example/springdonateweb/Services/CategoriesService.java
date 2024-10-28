package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Dtos.Users.UsersResponseDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import com.example.springdonateweb.Repositories.CategoriesRepository;
import com.example.springdonateweb.Services.interfaces.ICategoriesService;
import com.example.springdonateweb.Services.mappers.CategroriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService implements ICategoriesService {

    private final CategroriesMapper categroriesMapper;
    private final CategoriesRepository categoriesRepository;


    @Override
    public List<CategoriesResponseDto> findAllByCategoryId(int id) {
        return null;
    }

    @Override
    public List<CategoriesResponseDto> findAll() {
        return categoriesRepository.findAll().stream().map(categroriesMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public CategoriesResponseDto findByName(String name) {
        return null;
    };

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

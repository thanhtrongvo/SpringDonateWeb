package com.example.springdonateweb.Services.mappers;
import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;

import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategroriesMapper {

    CategoriesResponseDto toResponseDto(CategoriesEntity categoriesEntity);

    CategoriesEntity toEntity(CategoriesResponseDto categoriesResponseDto);
}

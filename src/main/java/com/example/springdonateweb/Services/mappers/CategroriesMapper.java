package com.example.springdonateweb.Services.mappers;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryCreateDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoriesResponseDto;
import com.example.springdonateweb.Models.Dtos.Categories.CategoryUpdateDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategroriesMapper {
    CategoriesEntity toEntity(CategoryCreateDto categoryCreateDto);
    CategoriesEntity toEntity(CategoryUpdateDto categoryUpdateDto);
    CategoriesResponseDto toDto(CategoriesEntity categoriesEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoriesEntity partialUpdate(CategoryUpdateDto categoryUpdateDto, @MappingTarget CategoriesEntity categoriesEntity);
}

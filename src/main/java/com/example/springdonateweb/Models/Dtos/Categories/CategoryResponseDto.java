package com.example.springdonateweb.Models.Dtos.Categories;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private int categoryId;
    private String name;
    private String description;
}

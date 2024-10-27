package com.example.springdonateweb.Models.Dtos.Categories;

import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * DTO for {@link CategoriesEntity}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponseDto implements Serializable {
    private int categoryId;
    private String name;
    private String description;

}

package com.example.springdonateweb.Models.Dtos.Categories;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto implements Serializable {
    private String name;
    private String description;
}

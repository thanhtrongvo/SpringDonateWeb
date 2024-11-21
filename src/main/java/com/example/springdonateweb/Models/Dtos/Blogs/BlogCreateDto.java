package com.example.springdonateweb.Models.Dtos.Blogs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogCreateDto {
    private String title;
    private String content;
    private String imageUrl;  // Chứa URL hình ảnh
    private boolean status;

}
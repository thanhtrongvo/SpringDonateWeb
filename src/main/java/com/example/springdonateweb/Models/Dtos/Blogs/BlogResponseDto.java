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
public class BlogResponseDto {
    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private boolean status;
}
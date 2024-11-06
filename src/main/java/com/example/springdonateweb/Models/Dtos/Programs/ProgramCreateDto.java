package com.example.springdonateweb.Models.Dtos.Programs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramCreateDto {
    private String name;
    private String description;
    private Integer goalAmount;

    private Date startDate;
    private Date endDate;
    private Integer categoryId;
    private String image;// ID của danh mục để liên kết

}

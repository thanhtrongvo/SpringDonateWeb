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
public class ProgramUpdateDto {
    private int programId;
    private String name;
    private String description;
    private Integer goalAmount;
    private Date startDate;
    private Date endDate;
    private boolean status;
    private int categoryId;
    private String imageUrl;
    private MultipartFile image;

}

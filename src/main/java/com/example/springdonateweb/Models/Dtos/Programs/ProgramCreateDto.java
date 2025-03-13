package com.example.springdonateweb.Models.Dtos.Programs;

import jakarta.validation.constraints.AssertTrue;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProgramCreateDto {
    private String name;
    private String description;
    private Integer goalAmount;

    private Date startDate;
    private Date endDate;
    private Integer categoryId;
    private String imageUrl;
    private MultipartFile image;

}

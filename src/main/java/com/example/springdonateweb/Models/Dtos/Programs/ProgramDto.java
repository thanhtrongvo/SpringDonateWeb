package com.example.springdonateweb.Models.Dtos.Programs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDto {
    private int programId;
    private String name;
    private String description;
    private BigDecimal goalAmount;
    private BigDecimal currentAmount;
    private Integer donationCount;
    private Date startDate;
    private Date endDate;
    private int status;
}
package com.example.springdonateweb.Models.Dtos.Programs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponseDto implements Serializable {
    private int programId;
    private String name;
    private String description;
    private String image;
    private Integer donationCount;
    private String startDate;
    private String endDate;
    private boolean status;
    private Integer goalAmount;
    private Integer currentAmount;
    private String category;

    private int percentageAchieved;
    private long remainingDays;

}

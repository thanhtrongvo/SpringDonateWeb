package com.example.springdonateweb.Models.Dtos.Donors;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonorResponseDto {
    private int donorId;
    private Integer programId;
    private Integer userId;
    private Integer donationId;
    private LocalDateTime donationDate;

    // Added fields
    private String programName;
    private String userName;
}

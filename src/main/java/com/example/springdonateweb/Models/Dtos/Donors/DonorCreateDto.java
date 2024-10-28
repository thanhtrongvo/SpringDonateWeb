package com.example.springdonateweb.Models.Dtos.Donors;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonorCreateDto {
    @NotNull(message = "Program ID is required")
    private Integer programId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Donation ID is required")
    private Integer donationId;

    private Timestamp donationDate;
}

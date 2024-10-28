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
public class DonorDto {
    private int donorId;
    private Integer programId;
    private Integer userId;
    private Integer donationId;
    private Timestamp donationDate;
}

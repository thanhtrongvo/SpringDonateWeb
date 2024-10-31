package com.example.springdonateweb.Models.Dtos.PaymentMethods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodUpdateDto {
    private int paymentMethodId;

    @NotBlank(message = "Method name is required")
    private String methodName;

    private String description;

    @NotNull(message = "Status is required")
    private Byte isActive;
}

package com.food.OrderFood.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentDTO(
        @NotNull
        Long id,
        BigDecimal amount,
        String name,
        String expiration,
        StatusPayment status
) {
}

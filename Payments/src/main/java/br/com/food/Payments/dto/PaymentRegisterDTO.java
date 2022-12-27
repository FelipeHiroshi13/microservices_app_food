package br.com.food.Payments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PaymentRegisterDTO(
        @NotNull
        @Positive
        BigDecimal amount,
        @NotBlank
        @Size(max=100)
        String name,
        @NotBlank
        @Size(max=19)
        String number,
        @NotBlank
        @Size(max=7)
        String expiration,
        @NotBlank
        @Size(min=3, max = 3)
        String code,
        @NotNull
        Long orderId,
        @NotNull
        Long paymentMethodId
) {
}

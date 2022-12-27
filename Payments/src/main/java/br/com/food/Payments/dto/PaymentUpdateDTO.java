package br.com.food.Payments.dto;

import br.com.food.Payments.model.Status;
import jakarta.validation.constraints.NotNull;

public record PaymentUpdateDTO(
    @NotNull
    Long id,
    String name,
    Status status,
    Long paymentMethodId
) {
}

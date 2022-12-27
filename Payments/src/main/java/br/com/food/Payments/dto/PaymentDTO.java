package br.com.food.Payments.dto;

import br.com.food.Payments.model.Payment;
import br.com.food.Payments.model.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentDTO(
        @NotNull
        Long id,
        BigDecimal amount,
        String name,
        String expiration,
        Status status
) {

    public PaymentDTO(Payment payment){
        this(payment.getId(), payment.getAmount(), payment.getName(), payment.getExpiration(), payment.getStatus());
    }
}

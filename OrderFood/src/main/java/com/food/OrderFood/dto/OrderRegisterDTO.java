package com.food.OrderFood.dto;

import com.food.OrderFood.model.OrderFood;
import com.food.OrderFood.model.OrderItem;
import com.food.OrderFood.model.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRegisterDTO(
        @NotNull
        Long id,
        LocalDateTime dateTime,
        Status status,
        List<OrderItem> items
) {
    public OrderRegisterDTO(OrderFood orderFood) {
        this(orderFood.getId() ,orderFood.getDateTime(), orderFood.getStatus(), orderFood.getItems());
    }
}

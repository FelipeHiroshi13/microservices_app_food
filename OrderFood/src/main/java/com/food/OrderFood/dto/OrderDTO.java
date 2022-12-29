package com.food.OrderFood.dto;

import com.food.OrderFood.model.OrderFood;
import com.food.OrderFood.model.OrderItem;
import com.food.OrderFood.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime dateTime,
        Status status,
        List<OrderItem> items
) {
    public OrderDTO(OrderFood orderFood) {
        this(orderFood.getId() ,orderFood.getDateTime(), orderFood.getStatus(), orderFood.getItems());
    }
}

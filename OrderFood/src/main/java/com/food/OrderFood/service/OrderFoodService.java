package com.food.OrderFood.service;

import com.food.OrderFood.dto.OrderRegisterDTO;
import com.food.OrderFood.model.OrderFood;
import com.food.OrderFood.model.Status;
import com.food.OrderFood.repository.OrderFoodRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderFoodService {

    @Autowired
    private OrderFoodRespository repository;

    public OrderRegisterDTO createOrder(OrderRegisterDTO data){
        OrderFood orderFood =  new OrderFood(data);

        orderFood.setDateTime(LocalDateTime.now());
        orderFood.setStatus(Status.CREATED);
        orderFood.getItems().forEach(item -> item.setOrderFood(orderFood));

        OrderFood saved =  repository.save(orderFood);

        return new OrderRegisterDTO(orderFood);
    }

}

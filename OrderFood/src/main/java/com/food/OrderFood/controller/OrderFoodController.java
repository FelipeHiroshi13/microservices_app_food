package com.food.OrderFood.controller;


import com.food.OrderFood.dto.OrderRegisterDTO;
import com.food.OrderFood.service.OrderFoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderFoodController {

    @Autowired
    private OrderFoodService service;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderRegisterDTO> makeOrder(@RequestBody @Valid OrderRegisterDTO dto,
                                                        UriComponentsBuilder uriBuilder) {

        OrderRegisterDTO order = service.createOrder(dto);

        URI uri = uriBuilder.path("/order/{id}").buildAndExpand(order.id()).toUri();


        return ResponseEntity.created(uri).body(order);
    }


}

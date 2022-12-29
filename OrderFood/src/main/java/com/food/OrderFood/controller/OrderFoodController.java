package com.food.OrderFood.controller;


import com.food.OrderFood.dto.OrderDTO;
import com.food.OrderFood.dto.StatusDTO;
import com.food.OrderFood.service.OrderFoodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderFoodController {

    @Autowired
    private OrderFoodService service;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody @Valid OrderDTO dto,
                                              UriComponentsBuilder uriBuilder) {

        OrderDTO order = service.createOrder(dto);

        URI uri = uriBuilder.path("/order/{id}").buildAndExpand(order.id()).toUri();


        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping
    public  ResponseEntity<List<OrderDTO>> list (@PageableDefault Pageable pagination){
        var page = service.getAll();

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> listById(@PathVariable @NotNull Long id){
        OrderDTO order = service.getById(id);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/port")
    public String returnPort(@Value("${local.server.port}") String port){
        return String.format("Instance at %s", port);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody StatusDTO status){
        OrderDTO order = service.updateStatus(id, status);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/shipped")
    public ResponseEntity<Void> orderShipped(@PathVariable Long id){
        service.orderDispatched(id);

        return ResponseEntity.ok().build();
    }




}

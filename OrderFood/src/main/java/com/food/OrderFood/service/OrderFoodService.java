package com.food.OrderFood.service;

import com.food.OrderFood.dto.OrderDTO;
import com.food.OrderFood.dto.StatusDTO;
import com.food.OrderFood.model.OrderFood;
import com.food.OrderFood.model.Status;
import com.food.OrderFood.repository.OrderFoodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.print.attribute.standard.PresentationDirection;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFoodService {

    @Autowired
    private OrderFoodRepository repository;

    public OrderDTO createOrder(OrderDTO data){
        OrderFood orderFood =  new OrderFood(data);

        orderFood.setDateTime(LocalDateTime.now());
        orderFood.setStatus(Status.CREATED);
        orderFood.getItems().forEach(item -> item.setOrderFood(orderFood));

        OrderFood saved =  repository.save(orderFood);

        return new OrderDTO(orderFood);
    }

    public List<OrderDTO> getAll(){
        return repository.findAll().stream().map(OrderDTO::new).toList();
    }

    public OrderDTO getById(Long id){
        OrderFood order = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new OrderDTO(order);
    }


    public OrderDTO updateStatus(Long id, StatusDTO statusDTO){
        OrderFood order = repository.getItemById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(statusDTO.status());
        repository.udpateStatus(statusDTO.status(), order);


        return new OrderDTO(order);
    }

    public void orderDispatched(Long id){
        OrderFood order = repository.getItemById(id);

        if(order == null){
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.SHIPPED);
        repository.udpateStatus(Status.SHIPPED, order);
    }

}

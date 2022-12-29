package com.food.OrderFood.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.food.OrderFood.dto.OrderDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_food")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "orderFood")
    @JsonIgnore
    private List<OrderItem> items = new ArrayList<>();


    public OrderFood(OrderDTO data) {
        this.items = data.items();
    }
}

package com.food.OrderFood.model;


import com.food.OrderFood.dto.OrderRegisterDTO;
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order_food")
    private List<OrderItem> items = new ArrayList<>();


    public OrderFood(OrderRegisterDTO data) {
        this.items = data.items();
    }
}

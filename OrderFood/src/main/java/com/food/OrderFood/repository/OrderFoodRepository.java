package com.food.OrderFood.repository;

import com.food.OrderFood.model.OrderFood;
import com.food.OrderFood.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update OrderFood p set p.status = :status where p = :orderFood")
    void udpateStatus(Status status, OrderFood orderFood);

    @Query(value = "SELECT p from OrderFood p LEFT JOIN FETCH p.items where p.id = :id")
    OrderFood getItemById(Long id);

}

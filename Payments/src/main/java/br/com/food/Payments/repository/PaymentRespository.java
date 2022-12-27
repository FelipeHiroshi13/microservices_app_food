package br.com.food.Payments.repository;

import br.com.food.Payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRespository extends JpaRepository<Payment, Long> {


}

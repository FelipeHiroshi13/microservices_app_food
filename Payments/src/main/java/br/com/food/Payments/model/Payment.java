package br.com.food.Payments.model;

import br.com.food.Payments.dto.PaymentRegisterDTO;
import br.com.food.Payments.dto.PaymentUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    @Size(max=100)
    private String name;

    @NotBlank
    @Size(max=19)
    private String number;

    @NotBlank
    @Size(max=7)
    private String expiration;

    @NotBlank
    @Size(min=3, max = 3)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long orderId;

    @NotNull
    private Long paymentMethodId;

    public Payment(PaymentRegisterDTO data) {
        this.amount = data.amount();
        this.name = data.name();
        this.number = data.number();
        this.expiration = data.expiration();
        this.code = data.code();
        this.orderId = data.orderId();
        this.paymentMethodId = data.paymentMethodId();
    }

    public void udpate(PaymentUpdateDTO data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.status() != null){
            this.status = data.status();
        }
        if(data.paymentMethodId() != null){
            this.paymentMethodId = data.paymentMethodId();
        }
    }
}

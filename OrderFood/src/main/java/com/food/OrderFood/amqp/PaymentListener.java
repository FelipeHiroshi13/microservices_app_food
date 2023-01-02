package com.food.OrderFood.amqp;
import com.food.OrderFood.dto.PaymentDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payment.details-order")
    public void receiveMessage(PaymentDTO payment){
        System.out.println("----> "+ payment.id());
        if(payment.name().equals("erro")){
            throw new RuntimeException("erro in name");
        }

        String message = """
                Payment data: %s
                Amount R$: %s
                Status: %s
                """.formatted(payment.id(), payment.amount(), payment.status());

        System.out.println("Message: " + message);
    }

}

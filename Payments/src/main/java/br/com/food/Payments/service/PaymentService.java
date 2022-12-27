package br.com.food.Payments.service;


import br.com.food.Payments.dto.PaymentDTO;
import br.com.food.Payments.dto.PaymentRegisterDTO;
import br.com.food.Payments.dto.PaymentUpdateDTO;
import br.com.food.Payments.model.Payment;
import br.com.food.Payments.model.Status;
import br.com.food.Payments.repository.PaymentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    @Autowired
    private PaymentRespository repository;


    public PaymentDTO register(PaymentRegisterDTO data) {
        var payment = new Payment(data);
        payment.setStatus(Status.CREATE);
        repository.save(payment);

        return new PaymentDTO(payment);
    }


    public Page<PaymentDTO> list(Pageable pagination) {
        return repository.findAll(pagination).map(PaymentDTO::new);
    }



    public PaymentDTO getById(@PathVariable Long id) {
        Payment payment = repository.getReferenceById(id);

        return new PaymentDTO(payment);
    }

    public PaymentDTO update(PaymentUpdateDTO data){
        Payment payment = repository.getReferenceById(data.id());
        payment.udpate(data);

        return new PaymentDTO(payment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}

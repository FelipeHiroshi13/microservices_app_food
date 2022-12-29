package br.com.food.Payments.controller;

import br.com.food.Payments.dto.PaymentDTO;
import br.com.food.Payments.dto.PaymentRegisterDTO;
import br.com.food.Payments.dto.PaymentUpdateDTO;
import br.com.food.Payments.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;


    @GetMapping
    public Page<PaymentDTO> listar(@PageableDefault(size = 10) Pageable pagination) {
        return service.list(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> details(@PathVariable @NotNull Long id) {
        PaymentDTO dto = service.getById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDTO> register(@RequestBody @Valid PaymentRegisterDTO dto, UriComponentsBuilder uriBuilder) {
        PaymentDTO payment = service.register(dto);
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.id()).toUri();

        return ResponseEntity.created(uri).body(payment);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentUpdateDTO dto) {
        PaymentDTO updated = service.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDTO> remove(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name="updateOrder", fallbackMethod = "")
    public void confirmShipp(@PathVariable @NotNull Long id){
        service.confirmShipp(id);
    }

}

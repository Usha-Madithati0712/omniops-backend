package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.AccountPayment;
import com.omniops.omniops_backend.service.AccountPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-payments")
@CrossOrigin(origins = "*")
public class AccountPaymentController {

    @Autowired
    private AccountPaymentService accountPaymentService;

    @GetMapping
    public List<AccountPayment> getAllPayments() {
        return accountPaymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public AccountPayment getPaymentById(@PathVariable Integer id) {
        return accountPaymentService.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @PostMapping
    public AccountPayment savePayment(@RequestBody AccountPayment payment) {
        return accountPaymentService.savePayment(payment);
    }

    @PutMapping("/{id}")
    public AccountPayment updatePayment(@PathVariable Integer id,
                                        @RequestBody AccountPayment payment) {
        return accountPaymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Integer id) {
        accountPaymentService.deletePayment(id);
    }
}
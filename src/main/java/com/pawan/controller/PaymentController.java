
package com.pawan.controller;

import com.pawan.DAO.PaymentDAO;
import com.pawan.model.Payment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentDAO paymentDAO;

    public PaymentController(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    @GetMapping("/{payment_id}")
    public Payment getPaymentById(@PathVariable int payment_id) {
        return paymentDAO.getPaymentById(payment_id);
    }

    @PostMapping
    public void addPayment(@RequestBody Payment payment) {
        paymentDAO.addPayment(payment);
    }

    @PutMapping("/{payment_id}")
    public void updatePayment(@PathVariable int payment_id, @RequestBody Payment payment) {
        payment.setPayment_id(payment_id);
        paymentDAO.updatePayment(payment);
    }

    @DeleteMapping("/{payment_id}")
    public void deletePayment(@PathVariable int payment_id) {
        paymentDAO.deletePayment(payment_id);
    }
}

package com.pawan.DAO;

import com.pawan.model.Payment;

import java.util.List;

public interface PaymentDAO {
    List<Payment> getAllPayments();
    Payment getPaymentById(int payment_id);
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int payment_id);
}

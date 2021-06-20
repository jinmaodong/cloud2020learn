package com.mdjin.springcloud.service;

import com.mdjin.springcloud.entities.Payment;

import java.util.List;

public interface PaymentService {

    public int create(Payment payment);

    Payment getPaymentById(long id);

    List<Payment> getAllPayment();
}

package com.cosmetics.shop.service.impl;

import com.cosmetics.shop.entity.Order;
import com.cosmetics.shop.entity.Payment;
import com.cosmetics.shop.entity.Product;
import com.cosmetics.shop.model.PaymentStatus;
import com.cosmetics.shop.repository.PaymentRepository;
import com.cosmetics.shop.repository.ProductRepository;
import com.cosmetics.shop.service.PaymentService;
import com.cosmetics.shop.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Order order, String method) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMethod(method);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public void CancelPayment(Long paymentId) {
        Payment p = paymentRepository.findById(paymentId)
                .orElseThrow(
                        () -> new RuntimeException("Payment not found")
                );
        p.setStatus(PaymentStatus.CANCELLED);
        paymentRepository.save(p);
    }

    @Override
    public void validatePayment(Long paymentId) {
        Payment p = paymentRepository.findById(paymentId)
                .orElseThrow(
                        () -> new RuntimeException("Payment not found")
                );
        p.setStatus(PaymentStatus.PAID);
        paymentRepository.save(p);
    }
}

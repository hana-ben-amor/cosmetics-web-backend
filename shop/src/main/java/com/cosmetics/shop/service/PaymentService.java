package com.cosmetics.shop.service;

import com.cosmetics.shop.entity.Order;
import com.cosmetics.shop.entity.Payment;

public interface PaymentService {

    Payment createPayment(Order order, String method);

    void CancelPayment(Long paymentId);

    void validatePayment(Long paymentId);
}

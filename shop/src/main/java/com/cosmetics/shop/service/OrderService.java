package com.cosmetics.shop.service;

import com.cosmetics.shop.entity.Customer;
import com.cosmetics.shop.entity.Order;
import com.cosmetics.shop.entity.OrderItem;
import com.cosmetics.shop.model.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrder(Customer c, List<OrderItem> items);

    void updateOrderStatus(Long orderId, OrderStatus status);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByCustomer(Long customerId);

    double calculateTotal(Order order);

}

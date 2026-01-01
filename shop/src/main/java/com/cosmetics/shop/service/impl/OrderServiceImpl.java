package com.cosmetics.shop.service.impl;

import com.cosmetics.shop.entity.Customer;
import com.cosmetics.shop.entity.Order;
import com.cosmetics.shop.entity.OrderItem;
import com.cosmetics.shop.model.OrderStatus;
import com.cosmetics.shop.repository.OrderRepository;
import com.cosmetics.shop.service.OrderService;
import com.cosmetics.shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public Order createOrder(Customer c, List<OrderItem> items) {
        Order order = new Order();
        order.setCustomer(c);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        items.forEach(
                item -> {
                    productService.updateStock(item.getProduct().getId(),item.getQuantity());
                    item.setOrder(order);
                }
        );

        order.setItems(items);
        order.setTotalAmount(calculateTotal(order));
        return orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public double calculateTotal(Order order) {
        return order
                .getItems()
                .stream()
                .mapToDouble(item -> item.getProduct().getPrice()*item.getQuantity())
                .sum();
    }
}

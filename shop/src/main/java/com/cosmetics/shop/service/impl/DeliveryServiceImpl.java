package com.cosmetics.shop.service.impl;

import com.cosmetics.shop.entity.Delivery;
import com.cosmetics.shop.entity.Order;
import com.cosmetics.shop.model.DeliveryStatus;
import com.cosmetics.shop.repository.DeliveryRepository;
import com.cosmetics.shop.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery createDelivery(Order order) {
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setStatus(DeliveryStatus.PREPARING);
        delivery.setCreatedAt(LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }

    @Override
    public void shipOrder(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.SHIPPED);
        deliveryRepository.save(delivery);
    }

    @Override
    public void deliverOrder(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.DELIVERED);
        deliveryRepository.save(delivery);
    }
}

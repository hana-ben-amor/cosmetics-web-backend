package com.cosmetics.shop.service;

import com.cosmetics.shop.entity.Delivery;
import com.cosmetics.shop.entity.Order;

public interface DeliveryService {
    Delivery createDelivery(Order order);

    void shipOrder(Long deliveryId);

    void deliverOrder(Long deliveryId);
}

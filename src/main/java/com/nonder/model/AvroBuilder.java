package com.nonder.model;

import io.axual.client.example.schema.Order;
import io.axual.client.example.schema.OrderKey;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class AvroBuilder {
    public OrderKey buildOrderKey() {
        return OrderKey.newBuilder()
                .setOrderId(UUID.randomUUID().toString())
                .build();
    }

    public Order buildOrder(OrderKey orderKey, String productCode, int quantity) {
        return Order.newBuilder()
                .setOrderId(orderKey.getOrderId())
                .setProductCode(productCode)
                .setQuantity(quantity)
                .build();
    }
}
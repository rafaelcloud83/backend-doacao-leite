package edu.rafael.doacao_leite.controllers.dtos;

import edu.rafael.doacao_leite.entities.Order;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.OrderStatus;

import java.time.Instant;

public record OrderDto(
    Long id,
    String productName,
    String estimatedPrice,
    OrderStatus status,
    Instant createdAt,
    Instant updatedAt,
    Users receiver,
    Users donor
) {

    public OrderDto(Order order) {
        this(order.getId(),
                order.getProductName(),
                order.getEstimatedPrice(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getReceiver(),
                order.getDonor()
        );
    }
}

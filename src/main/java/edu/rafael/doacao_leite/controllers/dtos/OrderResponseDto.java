package edu.rafael.doacao_leite.controllers.dtos;

import java.util.List;

public record OrderResponseDto(
    List<OrderDto> orders
) {
    public OrderResponseDto(List<OrderDto> orders) {
        this.orders = orders;
    }
}

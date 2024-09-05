package edu.rafael.doacao_leite.entities.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AGUARDANDO("AGUARDANDO"),
    DOADO("DOADO"),
    CONCLUIDO("CONCLUIDO");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}

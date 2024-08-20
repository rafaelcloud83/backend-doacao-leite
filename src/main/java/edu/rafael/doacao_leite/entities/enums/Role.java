package edu.rafael.doacao_leite.entities.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    DOADOR("DOADOR"),
    RECEBEDOR("RECEBEDOR");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}

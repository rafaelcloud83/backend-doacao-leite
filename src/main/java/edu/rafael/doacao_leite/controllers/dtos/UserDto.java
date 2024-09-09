package edu.rafael.doacao_leite.controllers.dtos;

import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;

import java.time.Instant;

public record UserDto(
        Long id,
        String name,
        String email,
        String password,
        String phone,
        String address,
        Boolean active,
        Role role,
        Instant createdAt,
        Instant updatedAt
) {

    public UserDto(Users user) {
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getActive(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}

package edu.rafael.doacao_leite.controllers.dtos;

import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;

public record UserDto(
        Long id,
        String name,
        String email,
        String password,
        Role role
) {

    public UserDto(Users user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }

}

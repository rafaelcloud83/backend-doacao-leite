package edu.rafael.doacao_leite.controllers.dtos;

import java.util.List;

public record UserResponseDto(
        List<UserDto> users
) {
    public UserResponseDto(List<UserDto> users) {
        this.users = users;
    }
}

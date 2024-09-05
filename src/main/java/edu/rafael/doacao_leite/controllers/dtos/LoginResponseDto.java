package edu.rafael.doacao_leite.controllers.dtos;

public record LoginResponseDto(String token, String userId, String userName, String userRole) {
}

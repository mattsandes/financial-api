package io.sandes.project.DTOs;

public record CreateUserDTO(
        String name,
        String password,
        String email) {
}

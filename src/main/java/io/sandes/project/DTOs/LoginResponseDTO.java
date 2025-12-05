package io.sandes.project.DTOs;

public record LoginResponseDTO(
        String token,
        Long userId,
        String name,
        String email
) {
}

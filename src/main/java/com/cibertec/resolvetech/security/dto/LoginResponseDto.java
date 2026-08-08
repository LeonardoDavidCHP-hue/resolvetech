package com.cibertec.resolvetech.security.dto;

public record LoginResponseDto(
        String token,
        String tipo,
        long expiresIn,
        String nombre,
        String rol
) {
}

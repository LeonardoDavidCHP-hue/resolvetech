package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.security.dto.LoginRequestDto;
import com.cibertec.resolvetech.security.dto.LoginResponseDto;
import com.cibertec.resolvetech.security.dto.RegistroUsuarioRequestDto;
import com.cibertec.resolvetech.security.dto.UsuarioResponseDto;
import com.cibertec.resolvetech.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDto> registrar(
            @Valid @RequestBody RegistroUsuarioRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }
}

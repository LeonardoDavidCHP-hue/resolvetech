package com.cibertec.resolvetech.security.impl;

import com.cibertec.resolvetech.security.AuthService;
import com.cibertec.resolvetech.security.JwtService;
import com.cibertec.resolvetech.security.dto.LoginRequestDto;
import com.cibertec.resolvetech.security.dto.LoginResponseDto;
import com.cibertec.resolvetech.security.dto.RegistroUsuarioRequestDto;
import com.cibertec.resolvetech.security.dto.UsuarioResponseDto;
import com.cibertec.resolvetech.entity.Usuario;
import com.cibertec.resolvetech.exception.RecursoDuplicadoException;
import com.cibertec.resolvetech.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UsuarioResponseDto registrar(RegistroUsuarioRequestDto request) {

        if (usuarioRepository.existsByNombre(request.nombre())) {
            throw new RecursoDuplicadoException("El nombre de usuario ya está registrado");
        }

        Usuario usuario = new Usuario(
                request.nombre(),
                passwordEncoder.encode(request.password()),
                "SOPORTE"
        );

        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setFechaActualizacion(LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getRol(),
                usuarioGuardado.isActivo(),
                usuarioGuardado.getFechaCreacion()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto request) {

        UsernamePasswordAuthenticationToken solicitud =
                new UsernamePasswordAuthenticationToken(
                        request.nombre(),
                        request.password()
                );

        UserDetails userDetails =
                (UserDetails) authenticationManager.authenticate(solicitud).getPrincipal();

        String token = jwtService.generarToken(userDetails);

        String rol = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");

        return new LoginResponseDto(
                token,
                "Bearer",
                jwtService.obtenerTiempoExpiracion(),
                userDetails.getUsername(),
                rol
        );
    }
}

package com.cibertec.resolvetech.security;

import com.cibertec.resolvetech.security.dto.LoginRequestDto;
import com.cibertec.resolvetech.security.dto.LoginResponseDto;
import com.cibertec.resolvetech.security.dto.RegistroUsuarioRequestDto;
import com.cibertec.resolvetech.security.dto.UsuarioResponseDto;

public interface AuthService {

    UsuarioResponseDto registrar(RegistroUsuarioRequestDto request);

    LoginResponseDto login(LoginRequestDto request);
}

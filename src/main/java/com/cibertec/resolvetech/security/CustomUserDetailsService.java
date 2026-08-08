package com.cibertec.resolvetech.security;

import com.cibertec.resolvetech.entity.Usuario;
import com.cibertec.resolvetech.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario o contraseña incorrectos"));

        return User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .authorities("ROLE_" + usuario.getRol())
                .disabled(!usuario.isActivo())
                .build();
    }
}

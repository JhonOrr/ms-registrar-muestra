package com.labsoluciones.laboratoriolubricante.infraestructure.service.impl;


import com.labsoluciones.laboratoriolubricante.infraestructure.repository.UsuarioRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }
}
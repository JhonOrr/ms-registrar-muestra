package com.labsoluciones.laboratoriolubricante.infraestructure.service.impl;


import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignInRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignUpRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.response.AuthenticationResponse;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.Role;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ClienteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.UsuarioRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.service.AuthenticationService;
import com.labsoluciones.laboratoriolubricante.infraestructure.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ClienteRepository clienteRepository;


    @Override
    public UsuarioEntity signUpUser(SignUpRequest signUpRequest) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidoPaterno(signUpRequest.getApellidoPaterno());
        usuario.setApellidoMaterno(signUpRequest.getApellidoMaterno());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setRole(Role.CLIENTE);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        usuario.setCliente(clienteRepository.findByRazonSocial(signUpRequest.getCliente()));
        usuario.setDni(signUpRequest.getDni());
        usuario.setEstado(1);
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity signUpAdmin(SignUpRequest signUpRequest) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidoPaterno(signUpRequest.getApellidoPaterno());
        usuario.setApellidoMaterno(signUpRequest.getApellidoMaterno());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setRole(Role.ANALISTA);
        usuario.setCliente(clienteRepository.findByRazonSocial(signUpRequest.getCliente()));
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        usuario.setDni(signUpRequest.getDni());
        usuario.setEstado(1);
        return usuarioRepository.save(usuario);
    }


    //metodo de login

    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(), signInRequest.getPassword())
        );
        var user = usuarioRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));
        var jwt = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
}

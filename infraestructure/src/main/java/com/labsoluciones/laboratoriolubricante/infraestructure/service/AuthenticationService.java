package com.labsoluciones.laboratoriolubricante.infraestructure.service;


import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignInRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignUpRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.response.AuthenticationResponse;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;

public interface AuthenticationService {
    UsuarioEntity signUpUser(SignUpRequest signUpRequest);
    UsuarioEntity signUpAdmin(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
    //AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}

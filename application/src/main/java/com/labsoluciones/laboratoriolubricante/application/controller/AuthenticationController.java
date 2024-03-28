package com.labsoluciones.laboratoriolubricante.application.controller;


import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignInRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.SignUpRequest;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.response.AuthenticationResponse;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signupuser")
    public ResponseEntity<UsuarioEntity> signUpUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpUser(signUpRequest));
    }
    @PostMapping("/signupadmin")
    public ResponseEntity<UsuarioEntity> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

}
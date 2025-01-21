package com.forohub.PoquitasDudas.controller;

import com.forohub.PoquitasDudas.domain.usuarios.DatosAutenticacionUsuario;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
import com.forohub.PoquitasDudas.infra.security.DatosJWTToken;
import com.forohub.PoquitasDudas.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuntenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario usuario){
        //Hacer el tokoen
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuario.nombre(),usuario.contrasena());
        var usuarioAuntenticado = authenticationManager.authenticate(authToken);
        //getPrincipañ() es el usuario autenticado
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAuntenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));


    }
}

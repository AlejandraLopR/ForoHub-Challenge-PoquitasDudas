package com.forohub.PoquitasDudas.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            System.out.println("EL TOKEN ES" + algorithm);
            return JWT.create()
                    .withIssuer("dudas")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            System.out.println("Este es el error: "+exception.toString());
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {

        DecodedJWT verifier = null;
        if (token == null){
            throw new RuntimeException();
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("dudas")
                    .build()
                    .verify(token);
            //verifier.getSubject();


        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if(verifier.getSubject() == null || verifier == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }


}

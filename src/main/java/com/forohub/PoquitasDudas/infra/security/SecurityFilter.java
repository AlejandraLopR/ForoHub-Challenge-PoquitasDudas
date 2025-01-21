package com.forohub.PoquitasDudas.infra.security;

import com.forohub.PoquitasDudas.domain.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Solicito Header Authorization
        var authHeader =request.getHeader("Authorization");

        if (authHeader  != null){
            System.out.println("Validar que el token no es nulo");
            var token=authHeader.replace("Bearer ","");
            var subject = tokenService.getSubject(token); //extract username
            //Obtener el token
            if(subject != null){
                //Token valido o suarios valido
                var usuario = usuarioRepository.findByNombre(subject);

                //Coon estas dos lineas le decimos al login que es valido y estamos verificando que el usuario existe
                //Forzando un incio de sesion
                var authentication= new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
                //Le digo a Spring el usuario ya esta autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }

}

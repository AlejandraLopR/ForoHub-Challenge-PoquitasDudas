package com.forohub.PoquitasDudas.controller;


import com.forohub.PoquitasDudas.domain.usuarios.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuariosController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosUsuario));
        DatosRespuestaUsuario respuestaUsuario = new DatosRespuestaUsuario(usuario.getId(),usuario.getNombre(),
                usuario.getCorreo());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuario);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario=usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizaUsuarios(@RequestBody @Valid DatosActualizaUsuarios datosActualizaUsuarios){
        Usuario usuario= usuarioRepository.getReferenceById(datosActualizaUsuarios.id());
        usuario.actualizarDatos(datosActualizaUsuarios);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(),usuario.getNombre(), usuario.getCorreo()));
    }

}

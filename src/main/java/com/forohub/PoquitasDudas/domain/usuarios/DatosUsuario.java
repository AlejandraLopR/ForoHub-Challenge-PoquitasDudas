package com.forohub.PoquitasDudas.domain.usuarios;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String correo,
        @NotBlank
        String contrasena

) {
}

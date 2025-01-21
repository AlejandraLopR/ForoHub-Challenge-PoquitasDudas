package com.forohub.PoquitasDudas.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaUsuario(
        @NotNull
        Long id,
        String nombre,
        String correo


) {
}

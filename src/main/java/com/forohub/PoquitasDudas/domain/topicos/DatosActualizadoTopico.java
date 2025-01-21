package com.forohub.PoquitasDudas.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Boolean status
) {
}

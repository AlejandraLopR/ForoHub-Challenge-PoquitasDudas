package com.forohub.PoquitasDudas.domain.respuestas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespuestaDTO(
        @NotBlank
        String solucion,
        @NotNull
        @Future
        LocalDateTime fecha,
        @NotNull
        Long idAutor,
        Long idTopico
) {
}

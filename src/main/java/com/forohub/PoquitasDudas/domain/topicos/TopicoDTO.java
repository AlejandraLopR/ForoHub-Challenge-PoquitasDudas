package com.forohub.PoquitasDudas.domain.topicos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @Future
        LocalDateTime fecha,
        @NotNull
        Long idAutor,
        Long idCurso

) {
}

package com.forohub.PoquitasDudas.domain.cursos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizaCurso(
        @NotNull
        Long id,
        String titulo,
        Categoria categoria
) {
}

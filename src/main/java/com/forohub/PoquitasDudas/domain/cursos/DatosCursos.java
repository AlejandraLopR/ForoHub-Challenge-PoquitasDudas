package com.forohub.PoquitasDudas.domain.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCursos(
        @NotBlank
        String titulo,
        @NotNull
        Categoria categoria

) {
}

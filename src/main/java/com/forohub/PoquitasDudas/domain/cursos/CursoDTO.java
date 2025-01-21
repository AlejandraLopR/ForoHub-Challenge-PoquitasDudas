package com.forohub.PoquitasDudas.domain.cursos;

public record CursoDTO(
        Long id,
        String titulo,
        Categoria especialidad
) {
    public CursoDTO(Curso curso){
        this(curso.getId(), curso.getTitulo(), curso.getCategoria());
    }
}

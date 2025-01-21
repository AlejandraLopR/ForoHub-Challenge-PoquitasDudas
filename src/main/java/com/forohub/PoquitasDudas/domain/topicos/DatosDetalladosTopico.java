package com.forohub.PoquitasDudas.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetalladosTopico(Long id, String titulo, String mensaje, Long idAutor, Long idCurso,
                                    LocalDateTime fecha ) {
    public DatosDetalladosTopico(Topico registro){
        this(registro.getId(),registro.getTitulo(), registro.getMensaje(),registro.getAutor().getId(),registro.getCursoTopico().getId(),registro.getFecha());
    }
}

package com.forohub.PoquitasDudas.domain.respuestas;

import java.time.LocalDateTime;

public record DatosDetalladosRespuesta(Long id,
                                       String solucion,
                                       LocalDateTime fecha,
                                       Long idAutor,
                                       Long idTopico) {
    public  DatosDetalladosRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getSolucion(), respuesta.getFecha(),respuesta.getAutor().getId(),respuesta.getTopico().getId());
    }
}

package com.forohub.PoquitasDudas.domain.respuestas;

import java.time.LocalDateTime;

public record DatosRespuestaRespuestas(
        Long id,
        Long idTopico,
        String nombreTopico,
        LocalDateTime fecha,
        String nombreUsuario,
        String solucion
) {
}

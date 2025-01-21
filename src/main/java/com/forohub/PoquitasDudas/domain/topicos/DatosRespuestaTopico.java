package com.forohub.PoquitasDudas.domain.topicos;

import com.forohub.PoquitasDudas.domain.cursos.Curso;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   String autorAutor,
                                   Long cursoid,
                                   LocalDateTime fecha,
                                   String status ) {
}
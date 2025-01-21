package com.forohub.PoquitasDudas.domain.respuestas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespuestasRepository extends JpaRepository<Respuesta, Long> {
    @Query(value = "SELECT * FROM respuestas r WHERE r.topico_id = :id",nativeQuery = true)
    List<Respuesta> respuestasPorTopico(Long id);
}

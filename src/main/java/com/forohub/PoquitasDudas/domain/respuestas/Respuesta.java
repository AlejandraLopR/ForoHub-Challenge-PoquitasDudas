package com.forohub.PoquitasDudas.domain.respuestas;

import com.forohub.PoquitasDudas.domain.topicos.Topico;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fecha;

    @OneToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String solucion;
}

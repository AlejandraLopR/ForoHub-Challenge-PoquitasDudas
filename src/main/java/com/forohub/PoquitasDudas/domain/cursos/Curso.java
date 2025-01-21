package com.forohub.PoquitasDudas.domain.cursos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name = "Cursos")
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso (DatosCursos cursos){
        this.titulo = cursos.titulo();
        this.categoria = cursos.categoria();
    }

    public void actualizarDatos(DatosActualizaCurso datosActualizaCurso) {
        if(datosActualizaCurso.titulo()!= null){
            this.titulo = datosActualizaCurso.titulo();
        }
        if(datosActualizaCurso.categoria()!= null){
            this.categoria = datosActualizaCurso.categoria();
        }
    }
}

package com.forohub.PoquitasDudas.domain.topicos;

import com.forohub.PoquitasDudas.domain.cursos.Curso;
import com.forohub.PoquitasDudas.domain.respuestas.Respuesta;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topicos")
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private boolean status;


    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso cursoTopico;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public boolean getStatus() {
        if(this.status == true){
            return true;
        }
        return false;
    }

    public void atualizarInformacion(DatosActualizadoTopico datos){
        if(datos.titulo()!= null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje()!= null){
            this.mensaje = datos.mensaje();
        }
        if(datos.status()!= null){
            this.status = datos.status();
        }

    }
}

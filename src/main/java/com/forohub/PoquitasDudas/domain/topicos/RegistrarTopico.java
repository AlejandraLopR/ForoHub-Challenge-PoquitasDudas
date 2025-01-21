package com.forohub.PoquitasDudas.domain.topicos;

import com.forohub.PoquitasDudas.domain.ValidacionException;
import com.forohub.PoquitasDudas.domain.cursos.Curso;
import com.forohub.PoquitasDudas.domain.cursos.CursoRepository;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
import com.forohub.PoquitasDudas.domain.usuarios.UsuarioRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RegistrarTopico {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    public DatosDetalladosTopico registrar(TopicoDTO topicoregestrar){
        if(!usuarioRepository.existsById(topicoregestrar.idAutor() ) || topicoregestrar.idAutor() == null){
            throw new ValidacionException("No existe un usuario activo asociado a ese ID");
        }

        var curso = elegirCurso(topicoregestrar);
        var autor = usuarioRepository.getReferenceById(topicoregestrar.idAutor());

        var registro =new Topico(null, topicoregestrar.titulo(), topicoregestrar.mensaje(), topicoregestrar.fecha(),false,autor,curso, null);

        topicoRepository.save(registro);

        return new DatosDetalladosTopico(registro);

    }

    private Curso elegirCurso(TopicoDTO topicoregestrar){
        if(topicoregestrar.idCurso() == null || !cursoRepository.existsById(topicoregestrar.idCurso())){
            return cursoRepository.getReferenceById(Long.valueOf(3));
        }
        return  cursoRepository.getReferenceById(topicoregestrar.idCurso());
    }

    public DatosRespuestaTopico actualizarTopcico(DatosActualizadoTopico datosActualizadoTopico, Long id){
        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(id));

        if(topico.isEmpty()){
            throw new ValidacionException("El topico con ese Id no existe !");
        }
        var actualizaTopico = topico.get();
        var status = "";
        if(actualizaTopico.getStatus()==true){ status="Respondido";}
        else {status="Sin Respuesta";}
        actualizaTopico.atualizarInformacion(datosActualizadoTopico);
        return  new DatosRespuestaTopico(actualizaTopico.getId(), actualizaTopico.getTitulo(), actualizaTopico.getMensaje(), actualizaTopico.getAutor().getNombre(),actualizaTopico.getCursoTopico().getId(),actualizaTopico.getFecha(),status);
    }


    public DatosRespuestaTopico elimina(Long id) {

        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(id));

        if(topico.isEmpty()){
            throw new ValidacionException("El topico con ese Id no existe !");
        }
        var actualizaTopico = topico.get();
        var status = "";
        if(actualizaTopico.getStatus()==true){ status="Respondido";}
        else {status="Sin Respuesta";}
        topicoRepository.deleteById(id);

        return  new DatosRespuestaTopico(actualizaTopico.getId(), actualizaTopico.getTitulo(), actualizaTopico.getMensaje(), actualizaTopico.getAutor().getNombre(),actualizaTopico.getCursoTopico().getId(),actualizaTopico.getFecha(),status);

    }
}

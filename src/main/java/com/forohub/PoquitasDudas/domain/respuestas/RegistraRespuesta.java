package com.forohub.PoquitasDudas.domain.respuestas;

import com.forohub.PoquitasDudas.domain.ValidacionException;
import com.forohub.PoquitasDudas.domain.topicos.DatosDetalladosTopico;
import com.forohub.PoquitasDudas.domain.topicos.Topico;
import com.forohub.PoquitasDudas.domain.topicos.TopicoDTO;
import com.forohub.PoquitasDudas.domain.topicos.TopicoRepository;
import com.forohub.PoquitasDudas.domain.usuarios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistraRespuesta {

    @Autowired
    private RespuestasRepository respuestasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalladosRespuesta registrar(RespuestaDTO registrarResp){
        if(!usuarioRepository.existsById(registrarResp.idAutor() ) || registrarResp.idAutor() == null){
            throw new ValidacionException("No existe un usuario activo asociado a ese ID");
        }
        if(!topicoRepository.existsById(registrarResp.idTopico() ) || registrarResp.idTopico() == null){
            throw new ValidacionException("No existe una Publicación o Tópico con este ID");
        }

        var autor = usuarioRepository.getReferenceById(registrarResp.idAutor());
        var topico = topicoRepository.getReferenceById(registrarResp.idTopico());

        var registro =new Respuesta(null,topico,  registrarResp.fecha(),autor, registrarResp.solucion());

        respuestasRepository.save(registro);
        topico.setStatus(true);

        return new DatosDetalladosRespuesta(registro);

    }
}

package com.forohub.PoquitasDudas.controller;

import com.forohub.PoquitasDudas.domain.ValidacionException;
import com.forohub.PoquitasDudas.domain.cursos.Curso;
import com.forohub.PoquitasDudas.domain.cursos.CursoRepository;
import com.forohub.PoquitasDudas.domain.cursos.DatosCursos;
import com.forohub.PoquitasDudas.domain.cursos.DatosRespuestaCurso;
import com.forohub.PoquitasDudas.domain.respuestas.*;
import com.forohub.PoquitasDudas.domain.topicos.*;
import com.forohub.PoquitasDudas.domain.usuarios.DatosRespuestaUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestasController {
    @Autowired
    private RespuestasRepository respuestasRepository;

    @Autowired
    private RegistraRespuesta registrar;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid RespuestaDTO registroRespuesta){
        var detallesTopico =registrar.registrar(registroRespuesta);
        return ResponseEntity.ok(detallesTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalladosRespuesta>> listadosRespuesta(@PageableDefault(size = 2) @RequestParam(required = false, defaultValue = "fecha") String sortBy,
                                                                         @RequestParam(required = false, defaultValue = "desc") String sortDirection, Pageable paginacion){
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(paginacion.getPageNumber(), paginacion.getPageSize(), sort);
        return ResponseEntity.ok(respuestasRepository.findAll(pageable).map(DatosDetalladosRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DatosRespuestaRespuestas>> retornarDatosUnaRespuesta(@PathVariable Long id){
        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(id));

        if(topico.isEmpty()){
            throw new ValidacionException("El topico con ese Id no existe !");
        }
        List<Respuesta> respuestasDeUnTopico = respuestasRepository.respuestasPorTopico(id);
        List<DatosRespuestaRespuestas> respuestasTopico = respuestasDeUnTopico.stream().map(e ->new DatosRespuestaRespuestas(e.getId(),e.getTopico().getId(),e.getTopico().getTitulo(),e.getFecha(),e.getAutor().getNombre(), e.getSolucion())).toList();
        return ResponseEntity.ok(respuestasTopico);

    }

}

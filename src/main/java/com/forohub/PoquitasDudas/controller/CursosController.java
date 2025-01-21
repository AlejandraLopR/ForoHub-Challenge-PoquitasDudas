package com.forohub.PoquitasDudas.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.forohub.PoquitasDudas.domain.cursos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")

public class CursosController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosCursos datosCursos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datosCursos));
        DatosRespuestaCurso respuestaCurso = new DatosRespuestaCurso(curso.getId(),
                curso.getTitulo(), curso.getCategoria());
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarCurso(@PathVariable Long id){
        Curso curso= cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);

    }

    @GetMapping
    public ResponseEntity<Page<CursoDTO>> listadosCursos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(CursoDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizaCurso(@RequestBody @Valid DatosActualizaCurso datosActualizaCurso){
        Curso curso= cursoRepository.getReferenceById(datosActualizaCurso.id());
        curso.actualizarDatos(datosActualizaCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getTitulo(), curso.getCategoria()));
    }

}

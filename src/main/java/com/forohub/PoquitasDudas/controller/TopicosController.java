package com.forohub.PoquitasDudas.controller;

import com.forohub.PoquitasDudas.domain.topicos.*;
import com.forohub.PoquitasDudas.domain.usuarios.Usuario;
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

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private RegistrarTopico registra;

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid TopicoDTO registroTopico){
        var detallesTopico =registra.registrar(registroTopico);
        return ResponseEntity.ok(detallesTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalladosTopico>> listadosCursos(@PageableDefault(size = 2) @RequestParam(required = false, defaultValue = "fecha") String sortBy,
                                                                      @RequestParam(required = false, defaultValue = "asc") String sortDirection, Pageable paginacion){
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(paginacion.getPageNumber(), paginacion.getPageSize(), sort);
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosDetalladosTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosMedico(@PathVariable Long id){
        Topico  topico= topicoRepository.getReferenceById(id);
        //System.out.println("INFORMACION DEL USUARIO: "+topico);
        var status= "";
        if(topico.getStatus() == false){
             status = "Sin respuestas";
        }
        else{
            status = "Respondido";
        }
        var datosTopico =new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getAutor().getNombre(),
               topico.getCursoTopico().getId(),topico.getFecha(),status);
        return ResponseEntity.ok(datosTopico);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DatosActualizadoTopico datos, @PathVariable Long id) {
        var topico = registra.actualizarTopcico(datos, id);
                //topicoRepository.getReferenceById(datos.id());

        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var registro= registra.elimina(id);

        return ResponseEntity.ok(registro);

    }


}

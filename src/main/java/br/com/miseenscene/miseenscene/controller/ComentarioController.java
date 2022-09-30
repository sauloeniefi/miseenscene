package br.com.miseenscene.miseenscene.controller;

import br.com.miseenscene.miseenscene.model.Comentario;
import br.com.miseenscene.miseenscene.repository.ComentarioRepository;
import br.com.miseenscene.miseenscene.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentarios/")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;


    @GetMapping
    public ResponseEntity<List<Comentario>> listAll() {

        if (comentarioRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<Comentario> comentarios = comentarioRepository.findAll();
            return ResponseEntity.ok(comentarios);
        }
    }

    @PostMapping
    public ResponseEntity<Comentario> postComentario(@RequestBody Comentario comentario) {
        if (comentario.getPublicacao().getIdPublicacao() <= 0) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            comentarioRepository.save(comentario);
            return ResponseEntity.created(null).body(comentario);
        }
    }

    @GetMapping("/publicacao/{id}")
    public ResponseEntity<List<Comentario>> findAllByPublicacaoId(@PathVariable("id") Long idPublicacao) {

        List<Comentario> listaComentarios = comentarioRepository.findAllByPublicacaoIdPublicacao(idPublicacao);

        if (listaComentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaComentarios);
        }
    }

}

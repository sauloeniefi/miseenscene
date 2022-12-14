package br.com.miseenscene.miseenscene.controller;

import br.com.miseenscene.miseenscene.model.Seguidor;
import br.com.miseenscene.miseenscene.model.Usuario;
import br.com.miseenscene.miseenscene.repository.SeguidorRepository;
import br.com.miseenscene.miseenscene.repository.UsuarioRepository;
import br.com.miseenscene.miseenscene.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SeguidorRepository seguidorRepository;


    @GetMapping
    public ResponseEntity<List<Usuario>> listAll() {
        if (usuarioRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<Usuario> listUsuarios = usuarioRepository.findAll();
            return ResponseEntity.ok(listUsuarios);
        }
    }

    @GetMapping("{email}")
    public ResponseEntity<Usuario> getByEmail(@PathVariable("email") String emailUsuario) {

        if (usuarioRepository.existsUsuarioByEmail(emailUsuario)) {
            Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);
            usuario.setSenha(UUID.randomUUID().toString());

            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("logar/")
    public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsUsuarioByEmail(usuario.getEmail())) {
            Usuario usuarioVerificador = usuarioRepository.getUsuarioByEmail(usuario.getEmail());
            if (usuarioVerificador.getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.ok(usuarioVerificador);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

        if (usuarioRepository.existsUsuarioByEmail(usuario.getEmail())) {
            return ResponseEntity.unprocessableEntity().body(usuario);
        } else {
            if (usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().isEmpty()) {
                return ResponseEntity.unprocessableEntity().body(usuario);
            }
            usuario.setDhRegistro(LocalDateTime.now());

            usuarioRepository.save(usuario);
            return ResponseEntity.created(null).body(usuario);
        }
    }

    @PutMapping("put-profile/{id}")
    public ResponseEntity<Usuario> putProfileImage(@PathVariable("id") Long idUsuario, @RequestParam("image") MultipartFile image) throws IOException {

        if (usuarioRepository.existsById(idUsuario)) {
            Usuario usuarioEncontrado = usuarioRepository.getById(idUsuario);
            usuarioService.saveProfilePicture(idUsuario, image);
            return ResponseEntity.created(null).body(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("put-descricao/{id}")
    public ResponseEntity<Usuario> putProfileDescricao(@PathVariable("id") Long idUsuario, @RequestBody String descricao) {

        if (usuarioRepository.existsById(idUsuario)) {
            Usuario usuarioAtualizado = usuarioRepository.getById(idUsuario);
            usuarioAtualizado.setDescricao(descricao);

            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.created(null).body(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/seguir")
    public ResponseEntity<Seguidor> seguir(@RequestParam("seguidor") String email,
                                 @RequestParam("artista") String emailArtista){
        try {
            if (usuarioRepository.existsUsuarioByEmail(email)) {
                Seguidor seguidores = new Seguidor();

                Usuario seguidor = usuarioRepository.getUsuarioByEmail(email);
                Usuario artista = usuarioRepository.getUsuarioByEmail(emailArtista);

                seguidores.setIdUsuarioSeguidor(seguidor.getIdUsuario());
                seguidores.setIdUsuario(artista.getIdUsuario());
                seguidorRepository.save(seguidores);
                return ResponseEntity.created(null).body(seguidores);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

}

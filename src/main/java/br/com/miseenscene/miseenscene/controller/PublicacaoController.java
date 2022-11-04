package br.com.miseenscene.miseenscene.controller;

import br.com.miseenscene.miseenscene.dto.PublicacaoDTO;
import br.com.miseenscene.miseenscene.model.Publicacao;
import br.com.miseenscene.miseenscene.model.Usuario;
import br.com.miseenscene.miseenscene.repository.PublicacaoRepository;
import br.com.miseenscene.miseenscene.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/publicacoes/")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<List<PublicacaoDTO>> listAll() {

        if (publicacaoRepository.findAllByOrderByDhInclusaoDesc().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<Publicacao> listPublicacao = publicacaoRepository.findAllByOrderByDhInclusaoDesc();
            List<PublicacaoDTO> publicacaoDTOList = new ArrayList<>();
            for (Publicacao publicacao : listPublicacao) {
                PublicacaoDTO publicacaoDTO = new PublicacaoDTO(publicacao.getIdPublicacao(),
                        publicacao.getTitulo(), publicacao.getDescricao(), publicacao.getDhInclusao(), publicacao.getImagem(),
                        publicacao.getUsuario());

                publicacaoDTO.getUsuario().setSenha(UUID.randomUUID().toString());

                publicacaoDTOList.add(publicacaoDTO);
            }

            return ResponseEntity.ok(publicacaoDTOList);
        }
    }

    @PostMapping("{usuario}/{titulo}/{descricao}")
    public ResponseEntity<Publicacao> savePublicacao(@PathVariable("usuario") String emailUsuario,
                                                     @PathVariable("titulo") String titulo,
                                                     @PathVariable("descricao") String descricao,
                                                     @RequestParam("image") MultipartFile image) throws IOException {

        Publicacao novaPublicacao = new Publicacao();
        Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);
        novaPublicacao.setUsuario(usuario);
        novaPublicacao.setTitulo(titulo);
        novaPublicacao.setDescricao(descricao);
        novaPublicacao.setImagem(image.getBytes());
        novaPublicacao.setDhInclusao(LocalDateTime.now());

        try {
            publicacaoRepository.save(novaPublicacao);
            return ResponseEntity.created(null).body(novaPublicacao);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(novaPublicacao);
        }

    }

    @GetMapping("list-by-user")
    public ResponseEntity<List<Publicacao>> listPublicacoesByUsuario(@RequestParam("email") String emailUsuario) {
        try {
            Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);
            List<Publicacao> listaPublicacoes = publicacaoRepository.getPublicacaosByUsuario(usuario);
            if (listaPublicacoes.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(listaPublicacoes);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @GetMapping("seguindo")
    public ResponseEntity<List<Publicacao>> listPublicacoesSeguindo(@RequestParam("email") String emailUsuario) {
        try {
            if (usuarioRepository.existsUsuarioByEmail(emailUsuario)) {
                Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);
                List<Publicacao> listPublicacoes = publicacaoRepository.findPublicacaosByUsuarioAndSeguidores(usuario.getIdUsuario());
                if (listPublicacoes.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.ok(listPublicacoes);
                }
            } else {
                return ResponseEntity.unprocessableEntity().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

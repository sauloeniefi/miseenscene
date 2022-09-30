package br.com.miseenscene.miseenscene.controller;

import br.com.miseenscene.miseenscene.dto.PublicacaoDTO;
import br.com.miseenscene.miseenscene.model.Publicacao;
import br.com.miseenscene.miseenscene.repository.PublicacaoRepository;
import br.com.miseenscene.miseenscene.service.PublicacaoService;
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
    private PublicacaoService publicacaoService;


    @GetMapping
    public ResponseEntity<List<PublicacaoDTO>> listAll() {

        if (publicacaoRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<Publicacao> listPublicacao = publicacaoRepository.findAll();
            List<PublicacaoDTO> publicacaoDTOList = new ArrayList<>();
            for (Publicacao publicacao : listPublicacao) {
                PublicacaoDTO publicacaoDTO = new PublicacaoDTO(publicacao.getIdPublicacao(),
                        publicacao.getTitulo(), publicacao.getDescricao(), publicacao.getDhInclusao(), publicacao.getImagem(),
                        publicacao.getUsuario(), publicacao.getComentarios());

                publicacaoDTO.getUsuario().setSenha(UUID.randomUUID().toString());

                publicacaoDTOList.add(publicacaoDTO);
            }

            return ResponseEntity.ok(publicacaoDTOList);
        }
    }

    @PostMapping
    public ResponseEntity<Publicacao> savePublicacao(@RequestBody Publicacao publicacao) {

        if (publicacao.getUsuario() == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            publicacao.setDhInclusao(LocalDateTime.now());

            publicacaoRepository.save(publicacao);
            return ResponseEntity.created(null).body(publicacao);
        }
    }

    @PutMapping("put-image/{id}")
    public ResponseEntity<PublicacaoDTO> putImagePublicacao(@PathVariable("id") Long idPublicacao, @RequestParam("image") MultipartFile image) throws IOException {

        if (publicacaoRepository.existsById(idPublicacao)) {
            publicacaoService.savePicturePublicacao(idPublicacao, image);

            Publicacao publicacao = publicacaoRepository.getById(idPublicacao);
            PublicacaoDTO publicacaoDTO = new PublicacaoDTO(publicacao.getIdPublicacao(), publicacao.getTitulo(), publicacao.getDescricao(),
                    publicacao.getDhInclusao(), publicacao.getImagem(), publicacao.getUsuario(), publicacao.getComentarios());

            return ResponseEntity.created(null).body(publicacaoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

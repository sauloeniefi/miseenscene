package br.com.miseenscene.miseenscene.service;

import br.com.miseenscene.miseenscene.model.Publicacao;
import br.com.miseenscene.miseenscene.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Publicacao savePicturePublicacao(Long idPublicacao, MultipartFile image) throws IOException {
        Publicacao publicacaoToUpdate = publicacaoRepository.getById(idPublicacao);
        publicacaoToUpdate.setImagem(image.getBytes());
        return publicacaoRepository.save(publicacaoToUpdate);
    }
}

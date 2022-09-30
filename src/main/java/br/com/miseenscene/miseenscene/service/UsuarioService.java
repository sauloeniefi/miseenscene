package br.com.miseenscene.miseenscene.service;

import br.com.miseenscene.miseenscene.model.Usuario;
import br.com.miseenscene.miseenscene.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveProfilePicture(Long idUsuario, MultipartFile image) throws IOException {
        Usuario userToUpdate = usuarioRepository.getById(idUsuario);
        userToUpdate.setProfile(image.getBytes());
        return usuarioRepository.save(userToUpdate);
    }
}

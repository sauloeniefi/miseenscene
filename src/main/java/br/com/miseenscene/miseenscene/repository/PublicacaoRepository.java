package br.com.miseenscene.miseenscene.repository;

import br.com.miseenscene.miseenscene.model.Publicacao;
import br.com.miseenscene.miseenscene.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    List<Publicacao> getPublicacaosByUsuario(Usuario usuario);

}

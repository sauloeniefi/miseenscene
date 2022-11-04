package br.com.miseenscene.miseenscene.repository;

import br.com.miseenscene.miseenscene.model.Publicacao;
import br.com.miseenscene.miseenscene.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    List<Publicacao> getPublicacaosByUsuario(Usuario usuario);

    @Query(value = "select * from publicacao p inner join seguidores as s on p.id_usuario = s.id_usuario where :idSeguidor = s.id_usuario_seguidor", nativeQuery = true)
    public List<Publicacao> findPublicacaosByUsuarioAndSeguidores(Long idSeguidor);

    List<Publicacao> findAllByOrderByDhInclusaoDesc();

}

package br.com.miseenscene.miseenscene.repository;

import br.com.miseenscene.miseenscene.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByPublicacaoIdPublicacao(Long idPublicacao);
}

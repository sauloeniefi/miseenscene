package br.com.miseenscene.miseenscene.repository;

import br.com.miseenscene.miseenscene.model.Seguidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, Long> {
}

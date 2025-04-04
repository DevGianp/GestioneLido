package it.rf.gestlido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.StatoAccesso;

@Repository
public interface StatoAccessoRepository extends JpaRepository<StatoAccesso, Integer>{

	public StatoAccesso getByIdStatoAccesso(Integer idStatoAccesso);
	
}

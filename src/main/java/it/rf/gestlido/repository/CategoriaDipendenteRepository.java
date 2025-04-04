package it.rf.gestlido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.CategoriaDipendente;
import it.rf.gestlido.model.Dipendente;

@Repository
public interface CategoriaDipendenteRepository extends JpaRepository<CategoriaDipendente, Integer>{
	
	public CategoriaDipendente getByIdCategoriaDipendente(Integer idCategoriaDipendente);

}

package it.rf.gestlido.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.Servizio;

@Repository
public interface ServizioRepository extends JpaRepository<Servizio, Integer>{
	
	public Servizio getByIdServizio(Integer idServizio);

}

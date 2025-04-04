package it.rf.gestlido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.ContoBar;

@Repository
public interface ContoBarRepository extends JpaRepository<ContoBar, Integer>{
	
	public ContoBar getByIdContoBar(Integer idContoBar);
	
	public Optional<ContoBar> findTopByOrderByIdContoBarDesc();
	
}

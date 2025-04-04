package it.rf.gestlido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.Prodotto;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{

	public long countByNomeProdotto(String nomeProdotto);
	
	public Optional<Prodotto> findByNomeProdotto(String nomeProdotto);
	
	public Prodotto getByNomeProdotto(String nomeProdotto);
	
}
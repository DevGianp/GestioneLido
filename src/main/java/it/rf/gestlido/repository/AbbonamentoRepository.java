package it.rf.gestlido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.DTO.AbbonamentoDTO;
import it.rf.gestlido.model.Abbonamento;

@Repository
public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Integer>{
	
	public Optional<Abbonamento> findTopByOrderByIdAbbDesc();
	
	@Query(value="SELECT DISTINCT abbonamento.id_abb FROM abbonamento JOIN acquista ON abbonamento.id_abb=acquista.id_abbonamento WHERE acquista.cf_cliente=?1 ", nativeQuery=true)
	public List<AbbonamentoDTO> listaAbbonamentiDaCliente(String cfCliente);
}

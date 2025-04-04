package it.rf.gestlido.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.DTO.AccessoDTO;
import it.rf.gestlido.model.Accesso;
import it.rf.gestlido.model.Cliente;

@Repository
public interface AccessoRepository extends JpaRepository<Accesso, Integer> {
	
	public List<Accesso> findByClienteAcc(Cliente clienteAcc);
	
	@Query("SELECT NEW it.rf.gestlido.DTO.AccessoDTO(MAX(accesso.idAccesso)) FROM Accesso accesso WHERE accesso.clienteAcc=?1")
	public AccessoDTO ultimoAccessoDaCliente(Cliente clienteAcc);
	
	public Accesso getByIdAccesso(Integer idAccesso);
	
	public List<Accesso> findAllByDataAccesso(LocalDate dataAccesso);
	
}

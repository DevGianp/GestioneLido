package it.rf.gestlido.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.DTO.AccessoDTO;
import it.rf.gestlido.model.Accesso;
import it.rf.gestlido.model.Cliente;
import it.rf.gestlido.model.StatoAccesso;
import it.rf.gestlido.repository.AccessoRepository;

@Service
public class AccessoService {
	
	@Autowired
	private AccessoRepository accR;
	
	public void creaAccesso(Accesso a)
	{
		accR.save(a);
	}
	
	public List<Accesso> accessoDaCliente(Cliente c)
	{
		return accR.findByClienteAcc(c);
	}
	
	public AccessoDTO ultimoAccessoDaCliente(Cliente c)
	{
		return accR.ultimoAccessoDaCliente(c);
	}
	
	public Accesso accessoCliente(Integer idAccesso)
	{
		return accR.getByIdAccesso(idAccesso);
	}
	
	public Accesso terminaAccesso(Accesso a, StatoAccesso terminato)
	{
		a.setStatoAccAss(terminato);
		
		accR.save(a);
		
		return a;
	}
	
	public List<Accesso> accessiOggi()
	{
		return accR.findAllByDataAccesso(LocalDate.now());
	}

}

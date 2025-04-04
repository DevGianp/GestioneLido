package it.rf.gestlido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.DTO.AcquistaDTO;
import it.rf.gestlido.model.Abbonamento;
import it.rf.gestlido.model.Acquista;
import it.rf.gestlido.model.Cliente;
import it.rf.gestlido.repository.AcquistaRepository;

@Service
public class AcquistaService {
	
	@Autowired
	private AcquistaRepository acqR;
	
	public void inserimentoAcquista(Acquista a)
	{
		acqR.save(a);
	}
	
	public Acquista cercaAcq(Abbonamento a)
	{
		return acqR.getByabbAcq(a);
	}

	public AcquistaDTO acqDaCliente(Cliente c)
	{
		return acqR.ultimoAcquistaDaCliente(c);
	}
	
	public Acquista cercaAcq(Integer codiceAcquisto)
	{
		return acqR.getByCodAcquisto(codiceAcquisto);
	}
	
	public List<Acquista> listaAcquista(Abbonamento a)
	{
		return acqR.findByAbbAcq(a);
	}
	
}

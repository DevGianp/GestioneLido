package it.rf.gestlido.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.DTO.PrevedeDTO;
import it.rf.gestlido.model.Abbonamento;
import it.rf.gestlido.model.Prevede;
import it.rf.gestlido.model.Servizio;
import it.rf.gestlido.repository.PrevedeRepository;
import it.rf.gestlido.repository.ServizioRepository;

@Service
public class PrevedeService {
	
	@Autowired
	private PrevedeRepository pR;
	
	@Autowired
	private ServizioRepository sR;
	
	public void inserisciPrevede(Abbonamento a, List<Servizio> elServizio)
	{
		for(Servizio s : elServizio)
		{
			pR.save(new Prevede(a, s));
		}
	}
	
	public List<Prevede> listaPrevede(Abbonamento a)
	{
		return pR.getByAbbPrev(a);
	}
	
	public List<PrevedeDTO> serviziDisponibiliDate(LocalDate dataInizio, LocalDate dataFine)
	{
		return pR.findServiziNonAssociatiAdAbbonamento(dataInizio, dataFine);
	}

}

package it.rf.gestlido.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.Servizio;
import it.rf.gestlido.repository.ServizioRepository;

@Service
public class ServizioService {
	
	@Autowired
	private ServizioRepository sR;
	
	public List<Servizio> elencoServ()
	{
		return sR.findAll();
	}
	
	public Servizio servizioById(Integer idServizio)
	{
		return sR.getByIdServizio(idServizio);
	}

	public void saveServ(Servizio s)
	{
		sR.save(s);
	}
	
	public void servNonDisp(Servizio s)
	{
		s.setServizioDisponibile(false);
		
		sR.save(s);
	}
	
	public void servDisp(Servizio s)
	{
		s.setServizioDisponibile(true);
		
		sR.save(s);
	}
	
	
	
}

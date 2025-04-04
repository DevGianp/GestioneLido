package it.rf.gestlido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.StatoAccesso;
import it.rf.gestlido.repository.StatoAccessoRepository;

@Service
public class StatoAccessoService {
	
	@Autowired
	private StatoAccessoRepository statoAccR;
	
	public StatoAccesso cercaStatoAccesso(Integer idStatoAccesso)
	{
		return statoAccR.getByIdStatoAccesso(idStatoAccesso);
	}

}

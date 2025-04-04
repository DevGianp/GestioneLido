package it.rf.gestlido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.PacchettoAbbonamento;
import it.rf.gestlido.repository.PacchettoAbbonamentoRepository;

@Service
public class PacchettoAbbonamentoService {
	
	@Autowired
	private PacchettoAbbonamentoRepository pR;
	
	public List<PacchettoAbbonamento> elencoPacchetti()
	{
		return pR.findAll();
	}

}

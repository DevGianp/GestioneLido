package it.rf.gestlido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.Fila;
import it.rf.gestlido.repository.FilaRepository;

@Service
public class FilaService {
	
	@Autowired
	private FilaRepository fR;
	
	public List<Fila> elencoFila()
	{
		return fR.findAll();
	}

}

package it.rf.gestlido.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.ContoBar;
import it.rf.gestlido.repository.ContoBarRepository;

@Service
public class ContoBarService {

	@Autowired
	private ContoBarRepository cBarR;
	
	public ContoBar cercaContoBar(Integer idContoBar)
	{
		return cBarR.getByIdContoBar(idContoBar);
	}
	
	public void inserisciContoBar(ContoBar cBar)
	{
		cBarR.save(cBar);
	}
	
	public Optional<ContoBar> ultimoConto()
	{
		return cBarR.findTopByOrderByIdContoBarDesc();
	}
	
}

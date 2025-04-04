package it.rf.gestlido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.ComprendeProdotto;
import it.rf.gestlido.model.Prodotto;
import it.rf.gestlido.repository.ComprendeProdottoRepository;

@Service
public class ComprendeProdottoService {
	
	@Autowired
	private ComprendeProdottoRepository comProdR;
	
	public List<ComprendeProdotto> elencoComprendeProdotti()
	{
		return comProdR.findAll();
	}
	
	public void inserisciComprendeProdotto(ComprendeProdotto cp)
	{
		comProdR.save(cp);
	}
	
	public List<ComprendeProdotto> cercaIdProdotto(Prodotto prod)
	{
		return comProdR.findByProdAcq(prod);
	}

	public void cancella(ComprendeProdotto compProd)
	{
		comProdR.delete(compProd);
	}
	
}

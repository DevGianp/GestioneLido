package it.rf.gestlido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.Prodotto;
import it.rf.gestlido.repository.ProdottoRepository;

@Service
public class ProdottoService {

	@Autowired
	private ProdottoRepository prdRep;
	
	public Boolean InserisciProdotto(Prodotto prd)
	{
		String nomeProdotto = prd.getNomeProdotto();
		Long prodottoTrovato = this.prdRep.countByNomeProdotto(nomeProdotto);
		if(prodottoTrovato == 0)
		{
			this.prdRep.save(prd);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Optional<Prodotto> ricercaProdotto(String nomeProdotto)
	{
		return this.prdRep.findByNomeProdotto(nomeProdotto);
	}
	
	public Prodotto trovaProdotto(String nomeProdotto)
	{
		return this.prdRep.getByNomeProdotto(nomeProdotto);
	}
	
	public Prodotto modificaProdotto(Prodotto prd)
	{
		return this.prdRep.save(prd);
	}
	
	public List<Prodotto> elencoProdotti()
	{
		return prdRep.findAll();
	}
	
}
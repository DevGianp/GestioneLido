package it.rf.gestlido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.CategoriaDipendente;
import it.rf.gestlido.model.Dipendente;
import it.rf.gestlido.repository.CategoriaDipendenteRepository;
import it.rf.gestlido.repository.DipendenteRepository;

@Service
public class DipendenteService {
	
	@Autowired
	private DipendenteRepository dR;
	
	@Autowired
	private CategoriaDipendenteRepository catDipR;
	
	public Boolean inserimentoDipendente(Dipendente d)
	{
		if(dR.countByCfDipendente(d.getCfDipendente())==0)
		{
			if(dR.countByUserDipendente(d.getCfDipendente())==0)
			{
				dR.save(d);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
	}
	
	public void modificaElencoDip(Dipendente d, Integer idCatDip)
	{
		d.setCatDipDisp(catDipR.getByIdCategoriaDipendente(idCatDip));
		
		dR.save(d);
	}
	
	public List<Dipendente> dipendenteCatDip(CategoriaDipendente catDipDisp)
	{
		return dR.getByCatDipDisp(catDipDisp);
	}
	
	
	public Optional<Dipendente> cercaDipendente(String cf)
	{
		return dR.findById(cf);
	}
	
	public void modificaDipendente(Dipendente d)
	{
		dR.save(d);
	}
	
	public List<Dipendente> elencaDipendenti()
	{
		return dR.findAll();
	}

	public void cancellaDipendente(String cf)
	{
		dR.deleteById(cf);
	}
	
	public Boolean loginDipendente(String username, String password)
	{
		if(dR.findByUserDipendenteAndPswDipendente(username, password).isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public Dipendente dipendenteUsername(String userDipendente)
	{
		return dR.getByUserDipendente(userDipendente);
	}
	
	public CategoriaDipendente catDipUsername(String userDipendente)
	{
		return dR.getByUserDipendente(userDipendente).getCatDipDisp();
	}
	
}

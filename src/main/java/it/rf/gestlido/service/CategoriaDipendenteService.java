package it.rf.gestlido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.CategoriaDipendente;
import it.rf.gestlido.model.Dipendente;
import it.rf.gestlido.repository.CategoriaDipendenteRepository;

@Service
public class CategoriaDipendenteService {
	
	@Autowired
	private CategoriaDipendenteRepository catDipR;

	public List<CategoriaDipendente> elencaCatDip()
	{
		return catDipR.findAll();
	}
	
	public CategoriaDipendente cercaCatDip(Integer idCategoriaDipendente)
	{
		return catDipR.getByIdCategoriaDipendente(idCategoriaDipendente);
	}
	
}

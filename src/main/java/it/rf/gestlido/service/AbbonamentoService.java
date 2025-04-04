package it.rf.gestlido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.DTO.AbbonamentoDTO;
import it.rf.gestlido.model.Abbonamento;
import it.rf.gestlido.repository.AbbonamentoRepository;

@Service
public class AbbonamentoService {
	
	@Autowired
	private AbbonamentoRepository aR;
	
	public void creaAbbonamento(Abbonamento a)
	{
		aR.save(a);
	}
	
	public Optional<Abbonamento> ultimoId()
	{
		return aR.findTopByOrderByIdAbbDesc();
	}
	
	public Optional<Abbonamento> cercaAbbonamento(Integer idAbb)
	{
		return aR.findById(idAbb);
	}

	public Abbonamento scadeAbbonamento(Abbonamento a)
	{
		a.setAbbScaduto(true);
		aR.save(a);
		
		return a;
	}
	
	public List<Abbonamento> listaAbbonamenti()
	{
		return aR.findAll();
	}
	
	public void saldaAbbonamento(Abbonamento a)
	{
		a.setSaldato(true);
		aR.save(a);
	}
	
	public List<AbbonamentoDTO> listaAbbonamentiDaCliente(String cfCliente)
	{
		return this.aR.listaAbbonamentiDaCliente(cfCliente);
	}
	
}

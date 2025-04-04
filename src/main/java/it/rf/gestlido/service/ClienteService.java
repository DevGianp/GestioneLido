package it.rf.gestlido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.gestlido.model.Cliente;
import it.rf.gestlido.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cR;
	
	public Boolean inserimentoCliente(Cliente c)
	{
		if(cR.countByCfCliente(c.getCfCliente())==0)
		{
			System.out.println("puoi inserire");
			cR.save(c);
			return true;
		}
		else
		{
			System.out.println("non puoi inserire");
			return false;
		}
		
	}
	
	public Optional<Cliente> cercaCliente(String cf)
	{
		return cR.findById(cf);
	}
	
	public void modificaCliente(Cliente c)
	{
		cR.save(c);
	}
	
	public List<Cliente> elencaClienti()
	{
		return cR.findAll();
	}

	public void cancellaCliente(String cf)
	{
		cR.deleteById(cf);
	}
	
	public Boolean loginCliente(String username, String password)
	{
		if(cR.findByUsernameClienteAndPswCliente(username, password).isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Cliente clienteUsername(String usernameCliente)
	{
		return cR.getByUsernameCliente(usernameCliente);
	}
	
}

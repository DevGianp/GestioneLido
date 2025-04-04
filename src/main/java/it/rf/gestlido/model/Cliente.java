package it.rf.gestlido.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente {
	
	@Column
	private String nomeCliente;
	
	@Column
	private String cognomeCliente;
	
	@Id
	@Column
	private String cfCliente;
	
	@Column
	private String nTelCliente;
	
	@Column
	private String usernameCliente;
	
	@Column
	private String pswCliente;
	
	@Column
	private Boolean abbonato;

	@OneToMany(mappedBy="idAccesso")
	private List<Accesso> elAccessi;
	
	@OneToMany(mappedBy="codAcquisto")
	private List<Acquista> elAcquistiCl;
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCognomeCliente() {
		return cognomeCliente;
	}

	public void setCognomeCliente(String cognomeCliente) {
		this.cognomeCliente = cognomeCliente;
	}


	public String getCfCliente() {
		return cfCliente.toUpperCase();
	}


	public void setCfCliente(String cfCliente) {
		this.cfCliente = cfCliente;
	}


	public String getnTelCliente() {
		return nTelCliente;
	}


	public void setnTelCliente(String nTelCliente) {
		this.nTelCliente = nTelCliente;
	}


	public String getUsernameCliente() {
		return usernameCliente;
	}


	public void setUsernameCliente(String usernameCliente) {
		this.usernameCliente = usernameCliente;
	}


	public String getPswCliente() {
		return pswCliente;
	}


	public void setPswCliente(String pswCliente) {
		this.pswCliente = pswCliente;
	}


	public Boolean getAbbonato() {
		return abbonato;
	}


	public void setAbbonato(Boolean abbonato) {
		this.abbonato = abbonato;
	}


	public List<Accesso> getElAccessi() {
		return elAccessi;
	}


	public void setElAccessi(List<Accesso> elAccessi) {
		this.elAccessi = elAccessi;
	}


	public List<Acquista> getElAcquistiCl() {
		return elAcquistiCl;
	}


	public void setElAcquistiCl(List<Acquista> elAcquistiCl) {
		this.elAcquistiCl = elAcquistiCl;
	}

	public Cliente(String nomeCliente, String cognomeCliente, String cfCliente, String nTelCliente,
			String usernameCliente, String pswCliente, Boolean abbonato, List<Accesso> elAccessi,
			List<Acquista> elAcquistiCl) {
		super();
		this.nomeCliente = nomeCliente;
		this.cognomeCliente = cognomeCliente;
		this.cfCliente = cfCliente;
		this.nTelCliente = nTelCliente;
		this.usernameCliente = usernameCliente;
		this.pswCliente = pswCliente;
		this.abbonato = abbonato;
		this.elAccessi = elAccessi;
		this.elAcquistiCl = elAcquistiCl;
	}
	
	public Cliente(String nomeCliente, String cognomeCliente, String cfCliente, String nTelCliente,
			String usernameCliente, String pswCliente, Boolean abbonato)
	{
		this.nomeCliente = nomeCliente;
		this.cognomeCliente = cognomeCliente;
		this.cfCliente = cfCliente;
		this.nTelCliente = nTelCliente;
		this.usernameCliente = usernameCliente;
		this.pswCliente = pswCliente;
		this.abbonato = abbonato;
	}
	
	public Cliente() {};

	@Override
	public String toString() {
		return "Cliente [nomeCliente=" + nomeCliente + ", cognomeCliente=" + cognomeCliente + ", cfCliente=" + cfCliente
				+ ", nTelCliente=" + nTelCliente + ", usernameCliente=" + usernameCliente + ", pswCliente=" + pswCliente
				+ "]";
	};

}

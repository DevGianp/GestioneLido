package it.rf.gestlido.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Dipendente")
public class Dipendente {
	
	@Id
	@Column
	private String cfDipendente;
	
	@Column
	private String nomeDipendente;
	
	@Column
	private String cognomeDipendente;
	
	@Column
	private String userDipendente;
	
	@Column
	private String pswDipendente;
	
	@Column
	private String nTelDipendente;
	
	@OneToMany(mappedBy="idAccesso")
	private List<Accesso> edDipAssoc;

	@ManyToOne
	@JoinColumn(name="idCategoriaDipendente")
	private CategoriaDipendente catDipDisp;

	public String getCfDipendente() {
		return cfDipendente.toUpperCase();
	}

	public void setCfDipendente(String cfDipendente) {
		this.cfDipendente = cfDipendente;
	}

	public String getNomeDipendente() {
		return nomeDipendente;
	}

	public void setNomeDipendente(String nomeDipendente) {
		this.nomeDipendente = nomeDipendente;
	}

	public String getCognomeDipendente() {
		return cognomeDipendente;
	}

	public void setCognomeDipendente(String cognomeDipendente) {
		this.cognomeDipendente = cognomeDipendente;
	}

	public String getUserDipendente() {
		return userDipendente;
	}

	public void setUserDipendente(String userDipendente) {
		this.userDipendente = userDipendente;
	}

	public String getPswDipendente() {
		return pswDipendente;
	}

	public void setPswDipendente(String pswDipendente) {
		this.pswDipendente = pswDipendente;
	}

	public String getnTelDipendente() {
		return nTelDipendente;
	}

	public void setnTelDipendente(String nTelDipendente) {
		this.nTelDipendente = nTelDipendente;
	}

	public List<Accesso> getEdDipAssoc() {
		return edDipAssoc;
	}

	public void setEdDipAssoc(List<Accesso> edDipAssoc) {
		this.edDipAssoc = edDipAssoc;
	}

	public CategoriaDipendente getCatDipDisp() {
		return catDipDisp;
	}

	public void setCatDipDisp(CategoriaDipendente catDipDisp) {
		this.catDipDisp = catDipDisp;
	}

	public Dipendente(String cfDipendente, String nomeDipendente, String cognomeDipendente, String userDipendente,
			String pswDipendente, String nTelDipendente, List<Accesso> edDipAssoc, CategoriaDipendente catDipDisp) {
		super();
		this.cfDipendente = cfDipendente;
		this.nomeDipendente = nomeDipendente;
		this.cognomeDipendente = cognomeDipendente;
		this.userDipendente = userDipendente;
		this.pswDipendente = pswDipendente;
		this.nTelDipendente = nTelDipendente;
		this.edDipAssoc = edDipAssoc;
		this.catDipDisp = catDipDisp;
	}
	
	public Dipendente() {};
	
	public Dipendente(String cfDipendente, String nomeDipendente, String cognomeDipendente, String userDipendente,
			String pswDipendente, String nTelDipendente, CategoriaDipendente catDipDisp)
	{
		this.cfDipendente = cfDipendente;
		this.nomeDipendente = nomeDipendente;
		this.cognomeDipendente = cognomeDipendente;
		this.userDipendente = userDipendente;
		this.pswDipendente = pswDipendente;
		this.nTelDipendente = nTelDipendente;
		this.catDipDisp = catDipDisp;
	}
	
	public Dipendente(String cfDipendente, String nomeDipendente, String cognomeDipendente, String userDipendente,
			String pswDipendente, String nTelDipendente)
	{
		this.cfDipendente = cfDipendente;
		this.nomeDipendente = nomeDipendente;
		this.cognomeDipendente = cognomeDipendente;
		this.userDipendente = userDipendente;
		this.pswDipendente = pswDipendente;
		this.nTelDipendente = nTelDipendente;
	}
	
	
}

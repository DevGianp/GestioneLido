package it.rf.gestlido.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="CategoriaDipendente")
public class CategoriaDipendente {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoriaDipendente;
	
	@Column
	private String nomeCategoriaDipendente;
	
	@Column
	private String descrCategoriaDipendente;
	
	@OneToMany(mappedBy="cfDipendente")
	private List<Dipendente> elDipDisp;

	public Integer getIdCategoriaDipendente() {
		return idCategoriaDipendente;
	}

	public void setIdCategoriaDipendente(Integer idCategoriaDipendente) {
		this.idCategoriaDipendente = idCategoriaDipendente;
	}

	public String getNomeCategoriaDipendente() {
		return nomeCategoriaDipendente;
	}

	public void setNomeCategoriaDipendente(String nomeCategoriaDipendente) {
		this.nomeCategoriaDipendente = nomeCategoriaDipendente;
	}

	public String getDescrCategoriaDipendente() {
		return descrCategoriaDipendente;
	}

	public void setDescrCategoriaDipendente(String descrCategoriaDipendente) {
		this.descrCategoriaDipendente = descrCategoriaDipendente;
	}

	public List<Dipendente> getElDipDisp() {
		return elDipDisp;
	}

	public void setElDipDisp(List<Dipendente> elDipDisp) {
		this.elDipDisp = elDipDisp;
	}

	public CategoriaDipendente(Integer idCategoriaDipendente, String nomeCategoriaDipendente,
			String descrCategoriaDipendente, List<Dipendente> elDipDisp) {
		super();
		this.idCategoriaDipendente = idCategoriaDipendente;
		this.nomeCategoriaDipendente = nomeCategoriaDipendente;
		this.descrCategoriaDipendente = descrCategoriaDipendente;
		this.elDipDisp = elDipDisp;
	}
	
	public CategoriaDipendente() {};
	

}

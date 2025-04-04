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
@Table(name="StatoAccesso")
public class StatoAccesso {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idStatoAccesso;
	
	@Column
	private String nomeStatoAccesso;
	
	@Column
	private String descrStatoAccesso;

	@OneToMany(mappedBy="idAccesso")
	private List<Accesso> elAcc;

	public Integer getIdStatoAccesso() {
		return idStatoAccesso;
	}

	public void setIdStatoAccesso(Integer idStatoAccesso) {
		this.idStatoAccesso = idStatoAccesso;
	}

	public String getNomeStatoAccesso() {
		return nomeStatoAccesso;
	}

	public void setNomeStatoAccesso(String nomeStatoAccesso) {
		this.nomeStatoAccesso = nomeStatoAccesso;
	}

	public String getDescrStatoAccesso() {
		return descrStatoAccesso;
	}

	public void setDescrStatoAccesso(String descrStatoAccesso) {
		this.descrStatoAccesso = descrStatoAccesso;
	}

	public List<Accesso> getElAcc() {
		return elAcc;
	}

	public void setElAcc(List<Accesso> elAcc) {
		this.elAcc = elAcc;
	}

	public StatoAccesso(Integer idStatoAccesso, String nomeStatoAccesso, String descrStatoAccesso,
			List<Accesso> elAcc) {
		super();
		this.idStatoAccesso = idStatoAccesso;
		this.nomeStatoAccesso = nomeStatoAccesso;
		this.descrStatoAccesso = descrStatoAccesso;
		this.elAcc = elAcc;
	}
	
	public StatoAccesso() {};

}

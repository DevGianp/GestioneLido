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
@Table (name = "Prodotto")
public class Prodotto {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProdotto;
	
	@Column
	private String nomeProdotto;
	
	@Column
	private Double przProdotto;
	
	@Column
	private Integer qntGiacenza;
	
	@OneToMany(mappedBy = "codAcqSingolo")
	private List<ComprendeProdotto> elProdAcq;

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public Double getPrzProdotto() {
		return przProdotto;
	}

	public void setPrzProdotto(Double przProdotto) {
		this.przProdotto = przProdotto;
	}

	public Integer getQntGiacenza() {
		return qntGiacenza;
	}

	public void setQntGiacenza(Integer qntGiacenza) {
		this.qntGiacenza = qntGiacenza;
	}

	public List<ComprendeProdotto> getElProdAcq() {
		return elProdAcq;
	}

	public void setElProdAcq(List<ComprendeProdotto> elProdAcq) {
		this.elProdAcq = elProdAcq;
	}

	public Prodotto(Integer idProdotto, String nomeProdotto, Double przProdotto, Integer qntGiacenza,
			List<ComprendeProdotto> elProdAcq) {
		super();
		this.idProdotto = idProdotto;
		this.nomeProdotto = nomeProdotto;
		this.przProdotto = przProdotto;
		this.qntGiacenza = qntGiacenza;
		this.elProdAcq = elProdAcq;
	}
	
	public Prodotto() {}

	
	
}

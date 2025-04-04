package it.rf.gestlido.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ComprendeProdotto")
public class ComprendeProdotto {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAcqSingolo;
	
	@Column
	private Boolean pagatoAcqSingolo;
	
	@ManyToOne
	@JoinColumn(name = "idContoBar")
	private ContoBar rifAcqConto;
	
	@ManyToOne
	@JoinColumn(name = "idProdotto")
	private Prodotto prodAcq;

	public Integer getCodAcqSingolo() {
		return codAcqSingolo;
	}

	public void setCodAcqSingolo(Integer codAcqSingolo) {
		this.codAcqSingolo = codAcqSingolo;
	}

	public Boolean getPagatoAcqSingolo() {
		return pagatoAcqSingolo;
	}

	public void setPagatoAcqSingolo(Boolean pagatoAcqSingolo) {
		this.pagatoAcqSingolo = pagatoAcqSingolo;
	}

	public ContoBar getRifAcqConto() {
		return rifAcqConto;
	}

	public void setRifAcqConto(ContoBar rifAcqConto) {
		this.rifAcqConto = rifAcqConto;
	}

	public Prodotto getProdAcq() {
		return prodAcq;
	}

	public void setProdAcq(Prodotto prodAcq) {
		this.prodAcq = prodAcq;
	}

	public ComprendeProdotto(Integer codAcqSingolo, Boolean pagatoAcqSingolo, ContoBar rifAcqConto, Prodotto prodAcq) {
		super();
		this.codAcqSingolo = codAcqSingolo;
		this.pagatoAcqSingolo = pagatoAcqSingolo;
		this.rifAcqConto = rifAcqConto;
		this.prodAcq = prodAcq;
	}
	
	public ComprendeProdotto(Boolean pagatoAcqSingolo, ContoBar rifAcqConto, Prodotto prodAcq)
	{
		this.pagatoAcqSingolo = pagatoAcqSingolo;
		this.rifAcqConto = rifAcqConto;
		this.prodAcq = prodAcq;
	}
	
	public ComprendeProdotto() {};
	
}

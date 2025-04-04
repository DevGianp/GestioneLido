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
@Table(name="Prevede")
public class Prevede {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAssociazione;
	
	@ManyToOne
	@JoinColumn(name="idAbb")
	private Abbonamento abbPrev;
	
	@ManyToOne
	@JoinColumn(name = "idServizio")
	private Servizio servPrev;

	public Integer getCodAssociazione() {
		return codAssociazione;
	}

	public void setCodAssociazione(Integer codAssociazione) {
		this.codAssociazione = codAssociazione;
	}

	public Abbonamento getAbbPrev() {
		return abbPrev;
	}

	public void setAbbPrev(Abbonamento abbPrev) {
		this.abbPrev = abbPrev;
	}

	public Servizio getServPrev() {
		return servPrev;
	}

	public void setServPrev(Servizio servPrev) {
		this.servPrev = servPrev;
	}

	public Prevede(Integer codAssociazione, Abbonamento abbPrev, Servizio servPrev) {
		super();
		this.codAssociazione = codAssociazione;
		this.abbPrev = abbPrev;
		this.servPrev = servPrev;
	}
	
	public Prevede(Abbonamento abbPrev, Servizio servPrev)
	{
		this.abbPrev = abbPrev;
		this.servPrev = servPrev;
	}
	
	public Prevede() {};
}

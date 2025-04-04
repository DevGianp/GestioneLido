package it.rf.gestlido.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Servizio")
public class Servizio {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idServizio;
	
	@Column
	private String nomeServizio;
	
	@Column
	private Double przServizio;
	
	@Column
	private Boolean servizioDisponibile;
	
	@OneToMany(mappedBy = "codAssociazione")
	private List<Prevede> elServPrev;
	
	@ManyToOne
	@JoinColumn(name="idFila")
	private Fila filaServ;

	public Integer getIdServizio() 
	{
		return idServizio;
	}

	public void setIdServizio(Integer idServizio) {
		this.idServizio = idServizio;
	}

	public String getNomeServizio() {
		return nomeServizio;
	}

	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}

	public Double getPrzServizio() {
		return przServizio;
	}

	public void setPrzServizio(Double przServizio) {
		this.przServizio = przServizio;
	}

	public Boolean getServizioDisponibile() {
		return servizioDisponibile;
	}

	public void setServizioDisponibile(Boolean servizioDisponibile) {
		this.servizioDisponibile = servizioDisponibile;
	}

	public List<Prevede> getElServPrev() {
		return elServPrev;
	}

	public void setElServPrev(List<Prevede> elServPrev) {
		this.elServPrev = elServPrev;
	}

	public Fila getFilaServ() {
		return filaServ;
	}

	public void setFilaServ(Fila filaServ) {
		this.filaServ = filaServ;
	}

	public Servizio(Integer idServizio, String nomeServizio, Double przServizio, Boolean servizioDisponibile,
			List<Prevede> elServPrev, Fila filaServ) {
		super();
		this.idServizio = idServizio;
		this.nomeServizio = nomeServizio;
		this.przServizio = przServizio;
		this.servizioDisponibile = servizioDisponibile;
		this.elServPrev = elServPrev;
		this.filaServ = filaServ;
	}
	
	public Servizio() {};

}

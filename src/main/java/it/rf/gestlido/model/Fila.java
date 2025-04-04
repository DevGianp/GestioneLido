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
@Table (name = "Fila")
public class Fila {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idFila;
	
	@Column
	private String nomeFila;
	
	@Column
	private String latoFila;
	
	@OneToMany(mappedBy="idServizio")
	private List<Servizio> elServFil;

	public Fila(Integer idFila, String nomeFila, String latoFila, List<Servizio> elServFil) {
		super();
		this.idFila = idFila;
		this.nomeFila = nomeFila;
		this.latoFila = latoFila;
		this.elServFil = elServFil;
	}
	
	public Fila() {};

	public Integer getIdFila() {
		return idFila;
	}

	public void setIdFila(Integer idFila) {
		this.idFila = idFila;
	}

	public String getNomeFila() {
		return nomeFila;
	}

	public void setNomeFila(String nomeFila) {
		this.nomeFila = nomeFila;
	}

	public String getLatoFila() {
		return latoFila;
	}

	public void setLatoFila(String latoFila) {
		this.latoFila = latoFila;
	}

	public List<Servizio> getElServFil() {
		return elServFil;
	}

	public void setElServFil(List<Servizio> elServFil) {
		this.elServFil = elServFil;
	};
	
}

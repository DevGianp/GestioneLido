package it.rf.gestlido.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="Acquista")
public class Acquista {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAcquisto;
	
	@Column
	private LocalDate dataAcquisto;
	
	@Column
	private LocalDate dataPagamento;
	
	@Column
	private Double importoVersato;

	@ManyToOne
	@JoinColumn(name="cfCliente")
	private Cliente acqCliente;
	
	@ManyToOne
	@JoinColumn(name="idAbbonamento")
	private Abbonamento abbAcq;

	public Acquista(Integer codAcquisto, LocalDate dataAcquisto, LocalDate dataPagamento, Cliente acqCliente,
			Abbonamento abbAcq, Double importoVersato) {
		super();
		this.codAcquisto = codAcquisto;
		this.dataAcquisto = dataAcquisto;
		this.dataPagamento = dataPagamento;
		this.acqCliente = acqCliente;
		this.abbAcq = abbAcq;
		this.importoVersato = importoVersato;
	}
	
	public Acquista(LocalDate dataAcquisto, LocalDate dataPagamento, Cliente acqCliente, Abbonamento abbAcq, Double importoVersato)
	{
		this.dataAcquisto = dataAcquisto;
		this.dataPagamento = dataPagamento;
		this.acqCliente = acqCliente;
		this.abbAcq = abbAcq;
		this.importoVersato = importoVersato;
	}
	
	public Acquista() {};

	public Integer getCodAcquisto() {
		return codAcquisto;
	}

	public void setCodAcquisto(Integer codAcquisto) {
		this.codAcquisto = codAcquisto;
	}

	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Cliente getAcqCliente() {
		return acqCliente;
	}

	public void setAcqCliente(Cliente acqCliente) {
		this.acqCliente = acqCliente;
	}

	public Abbonamento getAbbAcq() {
		return abbAcq;
	}

	public void setAbbAcq(Abbonamento abbAcq) {
		this.abbAcq = abbAcq;
	}

	public Double getImportoVersato() {
		return importoVersato;
	}

	public void setImportoVersato(Double importoVersato) {
		this.importoVersato = importoVersato;
	}
}

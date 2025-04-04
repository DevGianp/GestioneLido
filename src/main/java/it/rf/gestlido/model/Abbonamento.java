package it.rf.gestlido.model;

import java.time.LocalDate;
import java.util.Date;
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
@Table (name="Abbonamento")
public class Abbonamento {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAbb;
	
	@Column
	private LocalDate dataInizioAbb;
	
	@Column
	private LocalDate dataFineAbb;
	
	@Column
	private Boolean abbScaduto;
	
	@Column
	private String descrizioneAbb;
	
	@Column
	private Double costoAbb;
	
	@Column
	private Integer numIngressi;
	
	@Column
	private Boolean saldato;
	
	@OneToMany(mappedBy="codAcquisto")
	private List<Acquista> elAcqAbb;
	
	@ManyToOne
	@JoinColumn(name="idPacchetto")
	private PacchettoAbbonamento paccAbbAtt;
	
	@OneToMany(mappedBy="codAssociazione")
	private List<Prevede> elAssPrev;

	public Integer getIdAbb() {
		return idAbb;
	}

	public void setIdAbb(Integer idAbb) {
		this.idAbb = idAbb;
	}

	public LocalDate getDataInizioAbb() {
		return dataInizioAbb;
	}

	public void setDataInizioAbb(LocalDate dataInizioAbb) {
		this.dataInizioAbb = dataInizioAbb;
	}

	public LocalDate getDataFineAbb() {
		return dataFineAbb;
	}

	public void setDataFineAbb(LocalDate dataFineAbb) {
		this.dataFineAbb = dataFineAbb;
	}

	public Boolean getAbbScaduto() {
		return abbScaduto;
	}

	public void setAbbScaduto(Boolean abbScaduto) {
		this.abbScaduto = abbScaduto;
	}

	public String getDescrizioneAbb() {
		return descrizioneAbb;
	}

	public void setDescrizioneAbb(String descrizioneAbb) {
		this.descrizioneAbb = descrizioneAbb;
	}

	public Double getCostoAbb() {
		return costoAbb;
	}

	public void setCostoAbb(Double costoAbb) {
		this.costoAbb = costoAbb;
	}

	public List<Acquista> getElAcqAbb() {
		return elAcqAbb;
	}

	public void setElAcqAbb(List<Acquista> elAcqAbb) {
		this.elAcqAbb = elAcqAbb;
	}

	public PacchettoAbbonamento getPaccAbbAtt() {
		return paccAbbAtt;
	}

	public void setPaccAbbAtt(PacchettoAbbonamento paccAbbAtt) {
		this.paccAbbAtt = paccAbbAtt;
	}

	public List<Prevede> getElAssPrev() {
		return elAssPrev;
	}

	public void setElAssPrev(List<Prevede> elAssPrev) {
		this.elAssPrev = elAssPrev;
	}
	
	public Integer getNumIngressi() {
		return numIngressi;
	}

	public void setNumIngressi(Integer numIngressi) {
		this.numIngressi = numIngressi;
	}

	public Boolean getSaldato() {
		return saldato;
	}

	public void setSaldato(Boolean saldato) {
		this.saldato = saldato;
	}

	public Abbonamento(Integer idAbb, LocalDate dataInizioAbb, LocalDate dataFineAbb, Boolean abbScaduto, String descrizioneAbb,
			Double costoAbb, Integer numIngressi, List<Acquista> elAcqAbb, PacchettoAbbonamento paccAbbAtt, List<Prevede> elAssPrev) {
		super();
		this.idAbb = idAbb;
		this.dataInizioAbb = dataInizioAbb;
		this.dataFineAbb = dataFineAbb;
		this.abbScaduto = abbScaduto;
		this.descrizioneAbb = descrizioneAbb;
		this.costoAbb = costoAbb;
		this.elAcqAbb = elAcqAbb;
		this.paccAbbAtt = paccAbbAtt;
		this.elAssPrev = elAssPrev;
	}

	public Abbonamento() {};
	
	public Abbonamento(LocalDate dataInizioAbb, LocalDate dataFineAbb, Boolean abbScaduto, String descrizioneAbb,
			Double costoAbb, PacchettoAbbonamento paccAbbAtt, Integer numIngressi)
	{
		this.dataInizioAbb = dataInizioAbb;
		this.dataFineAbb = dataFineAbb;
		this.abbScaduto = abbScaduto;
		this.descrizioneAbb = descrizioneAbb;
		this.costoAbb = costoAbb;
		this.paccAbbAtt = paccAbbAtt;
		this.numIngressi = numIngressi;
	}
	

}

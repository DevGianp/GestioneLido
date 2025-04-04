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
@Table(name="PacchettoAbbonamento")
public class PacchettoAbbonamento {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPacchetto;
	
	@Column
	private String nomePacchetto;
	
	@Column
	private String descrizionePacchetto;
	
	@Column
	private String filaPrevista;
	
	@Column
	private Integer personePreviste;
	
	@Column
	private Integer ombrelloniPrevisti;
	
	@Column
	private Integer lettiniPrevisti;
	
	@Column
	private Integer sediePreviste;
	
	@OneToMany(mappedBy="idAbb")
	private List<Abbonamento> elAbbPacc;

	public Integer getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(Integer idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public String getNomePacchetto() {
		return nomePacchetto;
	}

	public void setNomePacchetto(String nomePacchetto) {
		this.nomePacchetto = nomePacchetto;
	}

	public String getDescrizionePacchetto() {
		return descrizionePacchetto;
	}

	public void setDescrizionePacchetto(String descrizionePacchetto) {
		this.descrizionePacchetto = descrizionePacchetto;
	}

	public String getFilaPrevista() {
		return filaPrevista;
	}

	public void setFilaPrevista(String filaPrevista) {
		this.filaPrevista = filaPrevista;
	}

	public Integer getPersonePreviste() {
		return personePreviste;
	}

	public void setPersonePreviste(Integer personePreviste) {
		this.personePreviste = personePreviste;
	}

	public Integer getOmbrelloniPrevisti() {
		return ombrelloniPrevisti;
	}

	public void setOmbrelloniPrevisti(Integer ombrelloniPrevisti) {
		this.ombrelloniPrevisti = ombrelloniPrevisti;
	}

	public Integer getLettiniPrevisti() {
		return lettiniPrevisti;
	}

	public void setLettiniPrevisti(Integer lettiniPrevisti) {
		this.lettiniPrevisti = lettiniPrevisti;
	}

	public Integer getSediePreviste() {
		return sediePreviste;
	}

	public void setSediePreviste(Integer sediePreviste) {
		this.sediePreviste = sediePreviste;
	}

	public List<Abbonamento> getElAbbPacc() {
		return elAbbPacc;
	}

	public void setElAbbPacc(List<Abbonamento> elAbbPacc) {
		this.elAbbPacc = elAbbPacc;
	}

	public PacchettoAbbonamento(Integer idPacchetto, String nomePacchetto, String descrizionePacchetto,
			String filaPrevista, Integer personePreviste, Integer ombrelloniPrevisti, Integer lettiniPrevisti,
			Integer sediePreviste, List<Abbonamento> elAbbPacc) {
		super();
		this.idPacchetto = idPacchetto;
		this.nomePacchetto = nomePacchetto;
		this.descrizionePacchetto = descrizionePacchetto;
		this.filaPrevista = filaPrevista;
		this.personePreviste = personePreviste;
		this.ombrelloniPrevisti = ombrelloniPrevisti;
		this.lettiniPrevisti = lettiniPrevisti;
		this.sediePreviste = sediePreviste;
		this.elAbbPacc = elAbbPacc;
	}
	
	public PacchettoAbbonamento() {};
	
}

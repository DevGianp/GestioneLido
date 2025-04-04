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
@Table (name = "Accesso")
public class Accesso {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAccesso;
	
	@Column
	private String noteAccesso;
	
	@Column
	private LocalDate dataAccesso;
	
	@ManyToOne
	@JoinColumn(name = "cfCliente")
	private Cliente clienteAcc;
	
	@ManyToOne
	@JoinColumn(name = "cfDipendente")
	private Dipendente dipAcc;
	
	@ManyToOne
	@JoinColumn(name = "idContoBar")
	private ContoBar contoBarAss;

	@ManyToOne
	@JoinColumn(name = "idStatoAccesso")
	private StatoAccesso statoAccAss;

	public Integer getIdAccesso() {
		return idAccesso;
	}

	public void setIdAccesso(Integer idAccesso) {
		this.idAccesso = idAccesso;
	}

	public String getNoteAccesso() {
		return noteAccesso;
	}

	public void setNoteAccesso(String noteAccesso) {
		this.noteAccesso = noteAccesso;
	}

	public LocalDate getDataAccesso() {
		return dataAccesso;
	}

	public void setDataAccesso(LocalDate dataAccesso) {
		this.dataAccesso = dataAccesso;
	}

	public Cliente getClienteAcc() {
		return clienteAcc;
	}

	public void setClienteAcc(Cliente clienteAcc) {
		this.clienteAcc = clienteAcc;
	}

	public Dipendente getDipAcc() {
		return dipAcc;
	}

	public void setDipAcc(Dipendente dipAcc) {
		this.dipAcc = dipAcc;
	}

	public ContoBar getContoBarAss() {
		return contoBarAss;
	}

	public void setContoBarAss(ContoBar contoBarAss) {
		this.contoBarAss = contoBarAss;
	}

	public StatoAccesso getStatoAccAss() {
		return statoAccAss;
	}

	public void setStatoAccAss(StatoAccesso statoAccAss) {
		this.statoAccAss = statoAccAss;
	}

	public Accesso(Integer idAccesso, String noteAccesso, LocalDate dataAccesso, Cliente clienteAcc, Dipendente dipAcc,
			ContoBar contoBarAss, StatoAccesso statoAccAss) {
		super();
		this.idAccesso = idAccesso;
		this.noteAccesso = noteAccesso;
		this.dataAccesso = dataAccesso;
		this.clienteAcc = clienteAcc;
		this.dipAcc = dipAcc;
		this.contoBarAss = contoBarAss;
		this.statoAccAss = statoAccAss;
	}
	
	public Accesso(String noteAccesso, LocalDate dataAccesso, Cliente clienteAcc, Dipendente dipAcc, ContoBar contoBarAss, StatoAccesso statoAccAss)
	{
		this.noteAccesso = noteAccesso;
		this.dataAccesso = dataAccesso;
		this.clienteAcc = clienteAcc;
		this.dipAcc = dipAcc;
		this.contoBarAss = contoBarAss;
		this.statoAccAss = statoAccAss;
	}
	
	public Accesso() {};
	
}

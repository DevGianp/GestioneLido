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
@Table(name="ContoBar")
public class ContoBar {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idContoBar;
	
	@Column
	private Double totContoBar;
	
	@OneToMany(mappedBy="idAccesso")
	private List<Accesso> elencoAccessiConto;

	@OneToMany(mappedBy="codAcqSingolo")
	private List<ComprendeProdotto> elCmpProd;

	public Integer getIdContoBar() {
		return idContoBar;
	}

	public void setIdContoBar(Integer idContoBar) {
		this.idContoBar = idContoBar;
	}

	public Double getTotContoBar() {
		return totContoBar;
	}

	public void setTotContoBar(Double totContoBar) {
		this.totContoBar = totContoBar;
	}

	public List<Accesso> getElencoAccessiConto() {
		return elencoAccessiConto;
	}

	public void setElencoAccessiConto(List<Accesso> elencoAccessiConto) {
		this.elencoAccessiConto = elencoAccessiConto;
	}

	public List<ComprendeProdotto> getElCmpProd() {
		return elCmpProd;
	}

	public void setElCmpProd(List<ComprendeProdotto> elCmpProd) {
		this.elCmpProd = elCmpProd;
	}

	public ContoBar(Integer idContoBar, Double totContoBar, List<Accesso> elencoAccessiConto,
			List<ComprendeProdotto> elCmpProd) {
		super();
		this.idContoBar = idContoBar;
		this.totContoBar = totContoBar;
		this.elencoAccessiConto = elencoAccessiConto;
		this.elCmpProd = elCmpProd;
	}
	
	public ContoBar() {};
	
	public ContoBar(Integer idContoBar, Double totContoBar)
	{
		this.idContoBar = idContoBar;
		this.totContoBar = totContoBar;
	}
	
	public ContoBar(Double totContoBar)
	{
		this.totContoBar = totContoBar;
	}

}

package it.rf.gestlido.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.rf.gestlido.DTO.AbbonamentoDTO;
import it.rf.gestlido.DTO.AcquistaDTO;
import it.rf.gestlido.DTO.PrevedeDTO;
import it.rf.gestlido.model.Abbonamento;
import it.rf.gestlido.model.Accesso;
import it.rf.gestlido.model.Acquista;
import it.rf.gestlido.model.CategoriaDipendente;
import it.rf.gestlido.model.Cliente;
import it.rf.gestlido.model.ComprendeProdotto;
import it.rf.gestlido.model.ContoBar;
import it.rf.gestlido.model.Dipendente;
import it.rf.gestlido.model.Prevede;
import it.rf.gestlido.model.Prodotto;
import it.rf.gestlido.model.Servizio;
import it.rf.gestlido.model.StatoAccesso;
import it.rf.gestlido.service.AbbonamentoService;
import it.rf.gestlido.service.AccessoService;
import it.rf.gestlido.service.AcquistaService;
import it.rf.gestlido.service.CategoriaDipendenteService;
import it.rf.gestlido.service.ClienteService;
import it.rf.gestlido.service.ComprendeProdottoService;
import it.rf.gestlido.service.ContoBarService;
import it.rf.gestlido.service.DipendenteService;
import it.rf.gestlido.service.FilaService;
import it.rf.gestlido.service.PacchettoAbbonamentoService;
import it.rf.gestlido.service.PrevedeService;
import it.rf.gestlido.service.ProdottoService;
import it.rf.gestlido.service.ServizioService;
import it.rf.gestlido.service.StatoAccessoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
	private PacchettoAbbonamentoService paccS;
    
    @Autowired
    private AcquistaService acqS;
    
    @Autowired
    private ClienteService cS;
    
    @Autowired
    private AbbonamentoService aS;
    
    @Autowired
    private ServizioService sS;
    
    @Autowired
    private FilaService fS;
    
    @Autowired
    private PrevedeService prevS;
    
    @Autowired
    private CategoriaDipendenteService catDipS;
    
    @Autowired
    private DipendenteService dS;
    
    @Autowired
    private AccessoService accS;
    
    @Autowired
    private StatoAccessoService statoAccS;
    
    @Autowired
    private ContoBarService cBarS;
    
    @Autowired
    private ProdottoService prdServ;
    
    @Autowired
    private ComprendeProdottoService cmprServ;
    
    
    

    //HOME CLIENTE
    @GetMapping("/homeCliente")
    public String vaiHomeCliente(HttpSession session, Model m)
    {
    	if(session.getAttribute("sessioneCliente")!=null)
    	{
    		String userCliente = (String) session.getAttribute("sessioneCliente");
    		m.addAttribute("infoCliente", cS.clienteUsername(userCliente));
    		
    		Cliente c = cS.clienteUsername(userCliente);
    		
    		List<AbbonamentoDTO> idAbb = aS.listaAbbonamentiDaCliente(c.getCfCliente());
    		
    		List<Abbonamento> elAbb = new ArrayList<>();
    		
    		for(AbbonamentoDTO abb : idAbb)
    		{
    			Optional<Abbonamento> a = aS.cercaAbbonamento(abb.getIdAbb());
    			elAbb.add(a.get());
    		}
    		
    		m.addAttribute("abbonamentiPassati", elAbb);
    		
    		return "homeCliente";
    	}
    	else
    	{
    		return "redirect:loginCliente";
    	}
    	
    }
    
    //HOME ADMIN
    @GetMapping("/homeAmministratore")
    public String vaiHomeAmministratore(HttpSession session, Model m)
    {
    	if(session.getAttribute("sessioneAmministratore")!=null)
    	{
    		String userDipendente = (String) session.getAttribute("sessioneAmministratore");
    		m.addAttribute("infoDipendente", dS.dipendenteUsername(userDipendente));
    		
    		return "homeAmministratore";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    	
    }
	
    //HOME SEGRETERIA
    @GetMapping("/homeSegretaria")
    public String vaiHomeSegretaria(HttpSession session, Model m)
    {
    	if(session.getAttribute("sessioneSegretaria")!=null)
    	{
    		String userDipendente = (String) session.getAttribute("sessioneSegretaria");
    		m.addAttribute("infoDipendente", dS.dipendenteUsername(userDipendente));
    		
    		//setta a false tutti gli abbonamenti scaduti
    		
    		List<Abbonamento> elAbb = aS.listaAbbonamenti();
    		
    		for(Abbonamento a : elAbb)
    		{
    			if(a.getDataFineAbb().isBefore(LocalDate.now()))
    			{
    				a.setAbbScaduto(true);
    				aS.creaAbbonamento(a);
    			}
    		}
    		
    		return "homeSegretaria";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    	
    }    
   
  	//HOME BARISTA
  	@GetMapping("/homeBarista")
	public String vaiHomeBarista(HttpSession session, Model m)
	{
		if(session.getAttribute("sessioneBarista")!=null)
    	{
    		String userDipendente = (String) session.getAttribute("sessioneBarista");
    		m.addAttribute("infoDipendente", dS.dipendenteUsername(userDipendente));
    		
    		return "homeBarista";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
	}
    
  	
  	

    
    //REGISTRAZIONECLIENTE (DIPENDENTI)
    @GetMapping("/registrazioneCliente")
    public String vaiRegistrazioneCliente(HttpSession session, Model m)
    {
    	if(session.getAttribute("sessioneSegretaria")!=null || session.getAttribute("sessioneAmministratore")!=null)
    	{
    		String dipendente="";
    		
    		if(session.getAttribute("sessioneSegretaria")!=null)
    		{
    			dipendente="Segretaria";
    		}
    		
    		if(session.getAttribute("sessioneAmministratore")!=null)
    		{
    			dipendente="Amministratore";
    		}
    		
    		m.addAttribute("dipendente", dipendente);
    		
    		return "registrazioneCliente";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    	
    }
    
    @PostMapping("/salvaCliente")
	public String salvaCliente(@ModelAttribute Cliente c, Model m, HttpSession session)
	{
		String messaggio;
		
		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		c.setAbbonato(false);
		
		if(cS.inserimentoCliente(c))
		{
			messaggio="Inserimento avvenuto";
		}
		else
		{
			messaggio="Codice Fiscale già presente. Inserimento non avvenuto.";
		}

		m.addAttribute("esitoInserimento", messaggio);
		
		return "registrazioneCliente";
	}      
    
    //ELENCOCLIENTI
    @GetMapping("/elencoClienti")
    public String vaiElencoClienti(HttpSession session, Model m)
    {
    	if(session.getAttribute("sessioneSegretaria")!=null || session.getAttribute("sessioneAmministratore")!=null)
    	{
    		String dipendente="";
    		
    		if(session.getAttribute("sessioneSegretaria")!=null)
    		{
    			dipendente="Segretaria";
    		}
    		
    		if(session.getAttribute("sessioneAmministratore")!=null)
    		{
    			dipendente="Amministratore";
    		}
    		
    		m.addAttribute("elencoClienti", cS.elencaClienti());
    		
    		m.addAttribute("dipendente", dipendente);
    		
    		return "elencoClienti";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    }
 
    @PostMapping("/effettuaModificaCliente")
    public String effettuaModificaCliente(@ModelAttribute Cliente c, @RequestParam Optional<Boolean> abbonamento, HttpSession session, Model m)
    {
    	String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("elencoClienti", cS.elencaClienti());
		
		m.addAttribute("dipendente", dipendente);
		
		c.setAbbonato(c.getAbbonato());
		
    	cS.modificaCliente(c);
    	
    	return "redirect:elencoClienti";
    }
    
    @PostMapping("/effettuaCancellazioneCliente")
    public String effettuaCancellaCliente(@RequestParam String cfDaCancellare, HttpSession session, Model m)
    {
    	String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("elencoClienti", cS.elencaClienti());
		
		m.addAttribute("dipendente", dipendente);
    	
		cS.cancellaCliente(cfDaCancellare);
		
    	return "redirect:elencoClienti";
    }
    
    //RICERCACLIENTE
    @GetMapping("/ricercaCliente")
    public String vaiRicercaCliente(Model m, HttpSession session)
    {
    	if(session.getAttribute("sessioneSegretaria")!=null || session.getAttribute("sessioneAmministratore")!=null)
    	{
    		String dipendente="";
    		
    		if(session.getAttribute("sessioneSegretaria")!=null)
    		{
    			dipendente="Segretaria";
    		}
    		
    		if(session.getAttribute("sessioneAmministratore")!=null)
    		{
    			dipendente="Amministratore";
    		}
    		
    		m.addAttribute("elencoClienti", cS.elencaClienti());
    		
    		m.addAttribute("dipendente", dipendente);
    		
    		return "ricercaCliente";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    }
    
    @PostMapping("/effettuaRicercaCliente")
    public String effettuaRicercaCliente(@RequestParam String cfDaCercare, HttpSession session, Model m)
    {
    	String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("elencoClienti", cS.elencaClienti());
		
		m.addAttribute("dipendente", dipendente);
		
		m.addAttribute("clienteTrovato", cS.cercaCliente(cfDaCercare));
		
		return "ricercaCliente";
    }
    
    
    //REGISTRAZIONE DIPENDENTE
    @GetMapping("/registrazioneDipendente")
    public String vaiRegistrazioneDipendente(Model m, HttpSession session)
    {
    	if(session.getAttribute("sessioneAmministratore")!=null)
    	{
    		List<CategoriaDipendente> elencoCatDip = catDipS.elencaCatDip();
        	
        	m.addAttribute("elencoCategorieDipendente", elencoCatDip);
        	
        	m.addAttribute("dipendente", "Amministratore");
        	
        	return "registrazioneDipendente";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
    }

    @PostMapping("/salvaDipendente")
	public String salvaDipendente(@ModelAttribute Dipendente d, Model m)
	{
		String messaggio;
		
		List<CategoriaDipendente> elencoCatDip = catDipS.elencaCatDip();
    	
    	m.addAttribute("elencoCategorieDipendente", elencoCatDip);
		
		System.out.println(d.getCatDipDisp());
		
		if(dS.inserimentoDipendente(d))
		{
			messaggio="Registrazione Effettuata.";
		}
		else
		{
			messaggio="Errore nella Registrazione: Codice Fiscale o Username già presente.";
		}

		m.addAttribute("esitoInserimento", messaggio);
		
		return "registrazioneDipendente";
	}
    
    
    
    
	
	//ACCESSOTORNELLO
	@GetMapping("/accessoTornello")
	public String vaiAccessoTornello(HttpSession session, Model m)	
	{
		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		return "accessoTornello";
	}
	
	@PostMapping("/verificaAbbonamentoEntrata")
	public String verificaAbbonamentoEntrata(@RequestParam Integer idAbb, Model m, HttpSession session)
	{

		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		String esitoVerifica="";
		
		//PRIMA DELL'INIZIO
		if(LocalDate.now().isBefore(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb()))
		{
			esitoVerifica="Abbonamento non ancora iniziato.";
		}
		
		//DOPO LA FINE
		if(LocalDate.now().isAfter(aS.cercaAbbonamento(idAbb).get().getDataFineAbb()))
		{
			esitoVerifica="Abbonamento Scaduto.";
			
			//SCADE ABBONAMENTO
			
			aS.scadeAbbonamento(aS.cercaAbbonamento(idAbb).get());
		}
		
		//SE OGGI è TRA L'INIZIO E LA FINE DELL'ABBONAMENTO
		if((LocalDate.now().isAfter(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb())
			|| LocalDate.now().isEqual(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb()))
			&&
			(LocalDate.now().isBefore(aS.cercaAbbonamento(idAbb).get().getDataFineAbb()) 
			|| LocalDate.now().isEqual(aS.cercaAbbonamento(idAbb).get().getDataFineAbb())))
		{
			//CLIENTE TROVATO DALL'ID ABBONAMENTO
			
			Cliente clienteAccesso = acqS.cercaAcq(aS.cercaAbbonamento(idAbb).get()).getAcqCliente();
			
			//ULTIMO ACCESSO DEL CLIENTE TROVATO
			
			if(accS.accessoCliente(accS.ultimoAccessoDaCliente(clienteAccesso).getIdAccesso())!=null)
			{
				Accesso ultimoAccessoCliente = (Accesso) accS.accessoCliente(accS.ultimoAccessoDaCliente(clienteAccesso).getIdAccesso());
				
				//SE ULTIMO ACCESSO TERMINATO, CREO NUOVO ACCESSO
				
				if(ultimoAccessoCliente.getStatoAccAss().getIdStatoAccesso().intValue()==3)
				{
					esitoVerifica="Nuovo Accesso Effettuato.";
					
					accS.creaAccesso(new Accesso("Nessuna", LocalDate.now(), acqS.cercaAcq(aS.cercaAbbonamento(idAbb).get()).getAcqCliente(),
							dS.dipendenteCatDip(catDipS.cercaCatDip(2)).get(0), cBarS.cercaContoBar(1), statoAccS.cercaStatoAccesso(2)));
				}

				//SE ULTIMO ACCESSO IN CORSO, SETTO A TERMINATO
				
				if(ultimoAccessoCliente.getStatoAccAss().getIdStatoAccesso().intValue()==2)
				{
					esitoVerifica="Tornello Sbagliato!";
				}
				
			}
			else
			{
				esitoVerifica="Nuovo Accesso Effettuato.";
				
				accS.creaAccesso(new Accesso("Nessuna", LocalDate.now(), acqS.cercaAcq(aS.cercaAbbonamento(idAbb).get()).getAcqCliente(),
						dS.dipendenteCatDip(catDipS.cercaCatDip(2)).get(0), cBarS.cercaContoBar(1), statoAccS.cercaStatoAccesso(2)));
			}
		}
			
		m.addAttribute("esitoVerifica", esitoVerifica);
		
		return "accessoTornello";
	}
	
	//USCITATORNELLO
	@GetMapping("/uscitaTornello")
	public String uscitaTornello(HttpSession session, Model m)
	{
		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		return "uscitaTornello";
	}
	
	@PostMapping("/verificaAbbonamentoUscita")
	public String verificaAbbonamentoUscita(@RequestParam Integer idAbb, Model m, HttpSession session)
	{
		String esitoVerifica="";
		
		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		//SE OGGI è PRIMA DELL'INIZIO DELL'ABBONAMENTO
		if(LocalDate.now().isBefore(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb()))
		{
			esitoVerifica="Abbonamento non ancora iniziato.";
		}
		
		//SE OGGI è DOPO LA FINE DELL'ABBONAMENTO
		if(LocalDate.now().isAfter(aS.cercaAbbonamento(idAbb).get().getDataFineAbb()))
		{
			esitoVerifica="Abbonamento Scaduto.";
			
			//SCADE ABBONAMENTO
			
			aS.scadeAbbonamento(aS.cercaAbbonamento(idAbb).get());
		}
		
		//SE OGGI è TRA L'INIZIO E LA FINE DELL'ABBONAMENTO
		if((LocalDate.now().isAfter(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb())
			|| LocalDate.now().isEqual(aS.cercaAbbonamento(idAbb).get().getDataInizioAbb()))
			&&
			(LocalDate.now().isBefore(aS.cercaAbbonamento(idAbb).get().getDataFineAbb()) 
			|| LocalDate.now().isEqual(aS.cercaAbbonamento(idAbb).get().getDataFineAbb())))
		{
			//CLIENTE TROVATO DALL'ID ABBONAMENTO
			
			Cliente clienteAccesso = acqS.cercaAcq(aS.cercaAbbonamento(idAbb).get()).getAcqCliente();
			
			//ULTIMO ACCESSO DEL CLIENTE TROVATO
			
			if(accS.accessoCliente(accS.ultimoAccessoDaCliente(clienteAccesso).getIdAccesso())!=null)
			{
				Accesso ultimoAccessoCliente = (Accesso) accS.accessoCliente(accS.ultimoAccessoDaCliente(clienteAccesso).getIdAccesso());
				
				//SE ULTIMO ACCESSO TERMINATO, CREO NUOVO ACCESSO
				
				if(ultimoAccessoCliente.getStatoAccAss().getIdStatoAccesso().intValue()==3)
				{
					esitoVerifica="Tornello Sbagliato!";
				}

				//SE ULTIMO ACCESSO IN CORSO, SETTO A TERMINATO
				
				if(ultimoAccessoCliente.getStatoAccAss().getIdStatoAccesso().intValue()==2)
				{
					esitoVerifica="Cliente uscito.";
					
					StatoAccesso statoTerminato = statoAccS.cercaStatoAccesso(3);
					
					accS.terminaAccesso(ultimoAccessoCliente, statoTerminato);
				}
				
			}
			else
			{
				esitoVerifica="Tornello Sbagliato! Dirigersi all'Entrata.";
			}
		}
		
		m.addAttribute("esitoVerifica", esitoVerifica);
		
		return "uscitaTornello";
	}

	//GESTIONESERVIZI
	@GetMapping("/gestioneServizi")
	public String vaiGestioneServizi(HttpSession session, Model m)
	{
		if(session.getAttribute("sessioneBagnino")!=null)
		{
			m.addAttribute("elAccessi", accS.accessiOggi());
			
			m.addAttribute("servizi", sS.elencoServ());
			
			return "gestioneServizi";
		}
		else
		{
			return "redirect:loginDipendente";
		}
	}
	
	@PostMapping("/riponi")
	public String riponiServizio(@RequestParam Integer idServizio, Model m)
	{
		sS.servDisp(sS.servizioById(idServizio));
		
		return "redirect:gestioneServizi";
	}
	
	//GESTIONEABBONAMENTI
	@GetMapping("/gestioneAbbonamento")
	public String gestioneAbbonamento(HttpSession session, Model m)
	{
		if(session.getAttribute("sessioneSegretaria")!=null || session.getAttribute("sessioneAmministratore")!=null)
    	{
    		String dipendente="";
    		
    		if(session.getAttribute("sessioneSegretaria")!=null)
    		{
    			dipendente="Segretaria";
    		}
    		
    		if(session.getAttribute("sessioneAmministratore")!=null)
    		{
    			dipendente="Amministratore";
    		}
    		
    		m.addAttribute("elencoAbbonamenti", aS.listaAbbonamenti());
    		
    		m.addAttribute("dipendente", dipendente);
    		
    		m.addAttribute("acquistaService", acqS);
    		
    		return "gestioneAbbonamento";
    	}
    	else
    	{
    		return "redirect:loginDipendente";
    	}
	}
	
	@PostMapping("/salda")
	public String salda(@RequestParam Double importoVersato, @RequestParam Integer idAbb, HttpSession session, Model m)
	{
		String dipendente="";
		
		if(session.getAttribute("sessioneSegretaria")!=null)
		{
			dipendente="Segretaria";
		}
		
		if(session.getAttribute("sessioneAmministratore")!=null)
		{
			dipendente="Amministratore";
		}
		
		m.addAttribute("dipendente", dipendente);
		
		m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
		
		m.addAttribute("elencoClienti", cS.elencaClienti());
		
		m.addAttribute("elencoServizi", sS.elencoServ());
		
		m.addAttribute("elencoFile", fS.elencoFila());
		
		Abbonamento a = aS.cercaAbbonamento(idAbb).get();
		
		Acquista acq = acqS.listaAcquista(a).get(0);
		
		Double totImporto=0.0;
		
		if(importoVersato>0)
		{
			acqS.inserimentoAcquista(new Acquista(acq.getDataAcquisto(), LocalDate.now(), acq.getAcqCliente(), acq.getAbbAcq(), importoVersato));
			
			List<Acquista> elAcquista = (List<Acquista>) acqS.listaAcquista(a);
			
			for(Acquista acquista : elAcquista)
			{
				totImporto += acquista.getImportoVersato();
			}
			
			if(totImporto.equals(a.getCostoAbb()) || totImporto>a.getCostoAbb())
			{
				aS.saldaAbbonamento(a);
			}
		}
		
		return "redirect:gestioneAbbonamento";
	}
	
	//MODIFICA DIPENDENTI (ELENCODIPENDENTI)
	@GetMapping("/modificaDipendente")
	public String vaiElencoDipendenti(HttpSession session, Model m) 
	{
	    if (session.getAttribute("sessioneAmministratore") != null) 
	    {
	        String dipendente = "Amministratore";
	        
	        m.addAttribute("dipendente", dipendente);
	        
	        m.addAttribute("elDipendenti", dS.elencaDipendenti());
	        
	        m.addAttribute("elencoCategorieDipendente", catDipS.elencaCatDip());
	        
	        return "modificaDipendente";
	    } 
	    else 
	    {
	        return "redirect:loginDipendente";
	    }
	}
	
	@PostMapping("/effettuaModificaDipendente")
	public String effettuaModficaDipendente(@ModelAttribute Dipendente d, @RequestParam Integer idCatDip, HttpSession session, Model m)
	{
		String dipendente="Amministratore";
		
		m.addAttribute("dipendente", dipendente);
		
		m.addAttribute("elDipendenti", dS.elencaDipendenti());
		
		dS.modificaElencoDip(d, idCatDip);
		
		return "redirect:modificaDipendente";
	}


    //PRENOTAZIONE CLIENTE (CLIENTE)
	@GetMapping("/prenotazioneCliente")
	public String vaiPrenotazioneCliente(HttpSession session, Model m) {
	    if (session.getAttribute("sessioneCliente") != null) {
	        String user = (String) session.getAttribute("sessioneCliente");
	        Cliente c = cS.clienteUsername(user);
	        List<AbbonamentoDTO> idAbb = aS.listaAbbonamentiDaCliente(c.getCfCliente());
	        List<Abbonamento> elAbb = new ArrayList<>();
	        Boolean presente = false;

	        for (AbbonamentoDTO abb : idAbb) {
	            Optional<Abbonamento> a = aS.cercaAbbonamento(abb.getIdAbb());
	            if (a.isPresent()) {
	                elAbb.add(a.get());
	                if (!a.get().getAbbScaduto()) {
	                    presente = true;
	                }
	            }
	        }

	        if (!presente) {
	            m.addAttribute("possibile", "Puoi effettuare Prenotazione");
	            m.addAttribute("elencoServizi", sS.elencoServ());
	            m.addAttribute("elencoFile", fS.elencoFila());
	            m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
	            session.setAttribute("cliente", c);

	            Integer nTessera = aS.ultimoId().isEmpty() ? 1 : aS.ultimoId().get().getIdAbb() + 1;
	            m.addAttribute("numeroTessera", nTessera);
	        } else {
	            m.addAttribute("nonPossibile", "Prenotazione già in corso, non puoi effettuarne un'altra.<br>Per ulteriori richieste chiamare la struttura al numero +39");
	            if (!elAbb.isEmpty()) {
	                m.addAttribute("prenotazioneInCorso", elAbb.get(0));
	            }
	        }

	        return "prenotazioneCliente";
	    } else {
	        return "redirect:loginCliente";
	    }
	}

	
	
    //VERIFICA SERVIZI DISPONIBILI TRAMITE DATA (PRENOTAZIONE CLIENTE)
    @PostMapping("/verificaGiornaliero")
    public String verificaGiornaliero(@RequestParam LocalDate data, HttpSession session, Model m)
    {
    	m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
		
		Integer nTessera;
		
		if(aS.ultimoId().isEmpty())
		{
			nTessera=1;
		}
		else
		{
			nTessera=aS.ultimoId().get().getIdAbb()+1;
		}
		
		Cliente c = (Cliente) session.getAttribute("cliente");
		
		m.addAttribute("cliente", c);
		
		m.addAttribute("numeroTessera", nTessera);
		
		LocalDate dataInizio = data;
		LocalDate dataFine = data;
		
		if(prevS.serviziDisponibiliDate(dataInizio, dataFine)!=null)
		{
			List<PrevedeDTO> elPrevDTO = prevS.serviziDisponibiliDate(dataInizio, dataFine);
			
			m.addAttribute("elencoServiziDisponibili", elPrevDTO);
			
			session.setAttribute("dataInizio", dataInizio);
			
			session.setAttribute("dataFine", dataFine);
		}
		else
		{
			m.addAttribute("elencoVuoto", "Nessun servizio disponibile nelle date selezionate.");
		}
    	
    	return "redirect:verificaGiornaliero";
    }
    
    @GetMapping("/verificaGiornaliero")
    public String vaiVerificaGiornaliero(HttpSession session, Model m)
    {
    	m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
		
		Integer nTessera;
		
		if(aS.ultimoId().isEmpty())
		{
			nTessera=1;
		}
		else
		{
			nTessera=aS.ultimoId().get().getIdAbb()+1;
		}
		
		Cliente c = (Cliente) session.getAttribute("cliente");
		
		m.addAttribute("cliente", c);
		
		m.addAttribute("numeroTessera", nTessera);
		
		LocalDate dataInizio = (LocalDate) session.getAttribute("dataInizio");
		
		LocalDate dataFine = (LocalDate) session.getAttribute("dataFine");
		
		if(prevS.serviziDisponibiliDate(dataInizio, dataFine)!=null)
		{
			List<PrevedeDTO> elPrevDTO = prevS.serviziDisponibiliDate(dataInizio, dataFine);
			
			m.addAttribute("elencoServiziDisponibili", elPrevDTO);
			
			m.addAttribute("dataInizio", dataInizio);
			
			m.addAttribute("dataFine", dataFine);
		}
		else
		{
			m.addAttribute("elencoVuoto", "Nessun servizio disponibile nelle date selezionate.");
		}
    	
    	return "verificaGiornaliero";
    }
    
    @PostMapping("/effettuaPrenotazione")
    public String effettuaPrenotazione(@ModelAttribute Abbonamento a, @RequestParam Double importoVersato, @RequestParam LocalDate dataPrenotazione,
    		@RequestParam String cfCliente, @RequestParam List<Integer> idServiziSelezionati, HttpSession session, Model m)
    {
    	Integer nTessera;
		Boolean abbScaduto=false;
		int i;
		List<Servizio> elServizio = new ArrayList<>();
		LocalDate dataAcquisto = LocalDate.now();
		LocalDate dataPagamento = LocalDate.now();
		
		String userCliente = (String) session.getAttribute("sessioneCliente");
		
		Cliente c = (Cliente) cS.clienteUsername(userCliente);
		
		m.addAttribute("cliente", c);
		
		Boolean saldo=false;
		
		if(aS.ultimoId().isEmpty())
		{
			nTessera=1;
		}
		else
		{
			nTessera=aS.ultimoId().get().getIdAbb()+1;
		}
		
		if(importoVersato.equals(a.getCostoAbb()))
		{
			saldo=true;
		}
		else
		{
			dataPagamento=null;
		}
		
		m.addAttribute("numeroTessera", nTessera);
		
		m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
		
		if(idServiziSelezionati!=null)
		{
			for(i=0;i<idServiziSelezionati.size();i++)
			{	
				elServizio.add(sS.servizioById(idServiziSelezionati.get(i)));
			}
		
		}
		
		m.addAttribute("elencoClienti", cS.elencaClienti());
		
		m.addAttribute("elencoServizi", sS.elencoServ());
		
		m.addAttribute("elencoFile", fS.elencoFila());
		
		a.setAbbScaduto(abbScaduto);
		
		a.setSaldato(saldo);
		
		a.setDataFineAbb(dataPrenotazione);
		
		a.setDataInizioAbb(dataPrenotazione);
		
		aS.creaAbbonamento(a);
		
		c.setAbbonato(true);
		
		cS.modificaCliente(c);
		
		acqS.inserimentoAcquista(new Acquista(dataAcquisto, dataPagamento, c, a, importoVersato));
		
		prevS.inserisciPrevede(a, elServizio);
		
		
		//INSERITO DOPO, CONTROLLA
		cS.cercaCliente(cfCliente).get().setAbbonato(true);
		
		cS.modificaCliente(cS.cercaCliente(cfCliente).get());
		//INSERITO DOPO, CONTROLLA
    	
    	return "redirect:prenotazioneCliente";
    }
    
    //CREAABBONAMENTOCLIENTE (DIPENDENTI)
  	@GetMapping("/creaAbbonamentoCliente")
  	public String vaiCreaAbbonamentoCliente(HttpSession session, Model m)
  	{
  		if(session.getAttribute("sessioneSegretaria")!=null || session.getAttribute("sessioneAmministratore")!=null)
  		{
  			m.addAttribute("elencoClienti", cS.elencaClienti());
  			
  			m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
  			
  			Integer nTessera;
  			
  			if(aS.ultimoId().isEmpty())
  			{
  				nTessera=1;
  			}
  			else
  			{
  				nTessera=aS.ultimoId().get().getIdAbb()+1;
  			}
  			
  			m.addAttribute("numeroTessera", nTessera);
  			
  			String dipendente="";
      		
      		if(session.getAttribute("sessioneSegretaria")!=null)
      		{
      			dipendente="Segretaria";
      		}
      		
      		if(session.getAttribute("sessioneAmministratore")!=null)
      		{
      			dipendente="Amministratore";
      		}
      		
      		m.addAttribute("dipendente", dipendente);
      		
      		m.addAttribute("dataInizio", session.getAttribute("dataInizio"));
      		
      		m.addAttribute("dataFine", session.getAttribute("dataFine"));
  			
  			return "creaAbbonamentoCliente";
  		}
  		else
  		{
  			return "redirect:loginDipendente";
  		}
  		
  	}
  	
  	@PostMapping("/verificaDate")
  	public String verificaDate(@RequestParam LocalDate dataInizio, @RequestParam LocalDate dataFine, HttpSession session, Model m)
  	{
  		String dipendente="";
  		
  		if(session.getAttribute("sessioneSegretaria")!=null)
  		{
  			dipendente="Segretaria";
  		}
  		
  		if(session.getAttribute("sessioneAmministratore")!=null)
  		{
  			dipendente="Amministratore";
  		}
  		
  		m.addAttribute("dipendente", dipendente);
  		
  		m.addAttribute("elencoClienti", cS.elencaClienti());
  		
  		m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
  		
  		Integer nTessera;
  		
  		if(aS.ultimoId().isEmpty())
  		{
  			nTessera=1;
  		}
  		else
  		{
  			nTessera=aS.ultimoId().get().getIdAbb()+1;
  		}
  		
  		m.addAttribute("numeroTessera", nTessera);
  		
  		if(prevS.serviziDisponibiliDate(dataInizio, dataFine)!=null)
  		{
  			List<PrevedeDTO> elPrevDTO = prevS.serviziDisponibiliDate(dataInizio, dataFine);
  			
  			m.addAttribute("elencoServiziDisponibili", elPrevDTO);
  			
  			session.setAttribute("dataInizio", dataInizio);
  			
  			session.setAttribute("dataFine", dataFine);
  		}
  		else
  		{
  			m.addAttribute("elencoVuoto", "Nessun servizio disponibile nelle date selezionate.");
  		}
  		
  		return "redirect:verificaDate";
  	}
  	
  	@GetMapping("/verificaDate")
    public String vaiVerificaDate(HttpSession session, Model m)
    {
  		String dipendente="";
  		
  		if(session.getAttribute("sessioneSegretaria")!=null)
  		{
  			dipendente="Segretaria";
  		}
  		
  		if(session.getAttribute("sessioneAmministratore")!=null)
  		{
  			dipendente="Amministratore";
  		}
  		
  		m.addAttribute("dipendente", dipendente);
  		
  		m.addAttribute("elencoClienti", cS.elencaClienti());
  		
  		m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
  		
  		Integer nTessera;
  		
  		if(aS.ultimoId().isEmpty())
  		{
  			nTessera=1;
  		}
  		else
  		{
  			nTessera=aS.ultimoId().get().getIdAbb()+1;
  		}
  		
  		m.addAttribute("numeroTessera", nTessera);
  		
  		LocalDate dataInizio = (LocalDate) session.getAttribute("dataInizio");
  		
  		LocalDate dataFine = (LocalDate) session.getAttribute("dataFine");
  		
  		if(prevS.serviziDisponibiliDate(dataInizio, dataFine)!=null)
  		{
  			List<PrevedeDTO> elPrevDTO = prevS.serviziDisponibiliDate(dataInizio, dataFine);
  			
  			m.addAttribute("elencoServiziDisponibili", elPrevDTO);
  			
  			m.addAttribute("dataInizio", dataInizio);
  			
  			m.addAttribute("dataFine", dataFine);
  		}
  		else
  		{
  			m.addAttribute("elencoVuoto", "Nessun servizio disponibile nelle date selezionate.");
  		}
      	
      	return "verificaDate";
    }
  	
  	@PostMapping("/generaAbb")
  	public String generaAbb(@ModelAttribute Abbonamento a, @RequestParam String cfCliente, @RequestParam List<Integer> idServiziSelezionati,
  			@RequestParam Optional<Boolean> saldato, @RequestParam Double importoVersato, Model m, HttpSession session)
  	{
  		String dipendente="";
  		
  		if(session.getAttribute("sessioneSegretaria")!=null)
  		{
  			dipendente="Segretaria";
  		}
  		
  		if(session.getAttribute("sessioneAmministratore")!=null)
  		{
  			dipendente="Amministratore";
  		}
  		
  		m.addAttribute("dipendente", dipendente);
  		
  		Integer nTessera;
  		Boolean abbScaduto=false;
  		int i;
  		List<Servizio> elServizio = new ArrayList<>();
  		LocalDate dataAcquisto = LocalDate.now();
  		LocalDate dataPagamento = LocalDate.now();
  		
  		Boolean saldo=false;
  		
  		if(aS.ultimoId().isEmpty())
  		{
  			nTessera=1;
  		}
  		else
  		{
  			nTessera=aS.ultimoId().get().getIdAbb()+1;
  		}
  		
  		if(importoVersato.equals(a.getCostoAbb()))
  		{
  			saldo=true;
  		}
  		else
  		{
  			dataPagamento=null;
  		}
  		
  		m.addAttribute("numeroTessera", nTessera);
  		
  		m.addAttribute("pacchettoGenerico", paccS.elencoPacchetti());
  		
  		for(i=0;i<idServiziSelezionati.size();i++)
  		{	
  			elServizio.add(sS.servizioById(idServiziSelezionati.get(i)));
  		}
  		
  		m.addAttribute("elencoClienti", cS.elencaClienti());
  		
  		m.addAttribute("elencoServizi", sS.elencoServ());
  		
  		m.addAttribute("elencoFile", fS.elencoFila());
  		
  		a.setAbbScaduto(abbScaduto);
  		
  		a.setSaldato(saldo);
  		
  		aS.creaAbbonamento(a);
  		
  		Cliente c = cS.cercaCliente(cfCliente).get();
  		
  		c.setAbbonato(true);
  		
  		cS.modificaCliente(c);
  		
  		acqS.inserimentoAcquista(new Acquista(dataAcquisto, dataPagamento, c, a, importoVersato));
  		
  		prevS.inserisciPrevede(a, elServizio);
  		
  		
  		//INSERITO DOPO, CONTROLLA
  		cS.cercaCliente(cfCliente).get().setAbbonato(true);
  		
  		cS.modificaCliente(cS.cercaCliente(cfCliente).get());
  		//INSERITO DOPO, CONTROLLA
  		
  		return "redirect:creaAbbonamentoCliente";
  	}
	
	 //HOME BAGNINO
  	@GetMapping("/homeBagnino")
  	public String vaiHomeBagnino(HttpSession session, Model m)
  	{
  		if(session.getAttribute("sessioneBagnino")!=null)
  		{
  			m.addAttribute("elAccessi", accS.accessiOggi());
  			
  			return "homeBagnino";
  		}
  		else
  		{
  			return "redirect:loginDipendente";
  		}
  	}
  	
	@PostMapping("/listaServiziCliente")
	public String listaServiziCliente(@RequestParam Integer idAccesso, HttpSession session, Model m)
	{
		session.getAttribute("sessioneBagnino");
		
		m.addAttribute("elAccessi", accS.accessiOggi());
		
		Accesso a = accS.accessoCliente(idAccesso);
		
		Dipendente d = dS.dipendenteUsername((String)session.getAttribute("sessioneBagnino"));
		
		a.setDipAcc(d);
		
		Cliente c = a.getClienteAcc();
		
		//ULTIMO CODICE ACQUISTO ABBONAMENTO DEL CLIENTE
		AcquistaDTO acqDTO = acqS.acqDaCliente(c);
		
		Acquista acq = acqS.cercaAcq(acqDTO.getCodAcquisto());
		
		Abbonamento abb = acq.getAbbAcq();
		
		m.addAttribute("abbonamento", abb);
		
		List<Prevede> elPrevede = prevS.listaPrevede(abb);
		
		List<Servizio> elServPrev = new ArrayList<>();
		
		for(Prevede p : elPrevede)
		{
			elServPrev.add(p.getServPrev());
		}
		
		m.addAttribute("elencoServiziPrevisti", elServPrev);
		
		return "homeBagnino";
	}
	
	@PostMapping("/nonDisponibile")
	public String nonDisponibile(@RequestParam Integer idServizio, Model m)
	{
		sS.servNonDisp(sS.servizioById(idServizio));
		return "redirect:homeBagnino";
	}
	
	@GetMapping("/gestioneProdotti")
	public String vaiGestioneProdotti(HttpSession session, Model m) 
	{
	    if (session.getAttribute("sessioneBarista") == null) 
	    {
	        return "redirect:loginDipendente";
	    } 
	    else 
	    {
	        List<Prodotto> elencoProdotti = prdServ.elencoProdotti();
	        m.addAttribute("elencoProdotti", elencoProdotti);
	        return "gestioneProdotti";
	    }
	}
	
	//INSERISCI NUOVO PRODOTTO
	@GetMapping("/InserisciNuovoProdotto")
	public String vaiInserisciNuovoProdotto() 
	{
		return "gestioneProdotti";
	}
	
	@PostMapping("/InserisciNuovoProdotto")
	public String InserisciNuovoProdotto(@ModelAttribute Prodotto prd, Model m) {
		
		if(this.prdServ.InserisciProdotto(prd))
		{
			m.addAttribute("esitoInserimento", "Inserimento avvenuto");
		}
		else
		{
			m.addAttribute("esitoInserimento", "Prodotto già presente in magazzino");
		}
		return "redirect:gestioneProdotti";
	}
	
	//RICERCA PRODOTTO PER MODIFICA
	@GetMapping("/RicercaProdotto")
	public String vaiRicercaProdotto(HttpSession session, Model m) 
	{
		if (session.getAttribute("sessioneBarista") == null) 
	    {
	        return "redirect:loginDipendente";
	    } 
	    else 
	    {
	        List<Prodotto> elencoProdotti = prdServ.elencoProdotti();
	        m.addAttribute("elencoProdotti", elencoProdotti);
	        return "gestioneProdotti";
	    }
	}
	
	@PostMapping("/RicercaProdotto")
	public String RicercaProdotto(String nomeProdottoDaRic, Model m) 
	{
	    Optional<Prodotto> optionalProdotto = this.prdServ.ricercaProdotto(nomeProdottoDaRic);
	    
	    if (optionalProdotto.isPresent()) 
	    {
	        Prodotto prd = optionalProdotto.get();
	        
	        m.addAttribute("Prodotto", prd);
	    } 
	    else 
	    {
	        m.addAttribute("esitoRicerca", "Nessun prodotto trovato");
	    }
	    return "gestioneProdotti";
	}
	
	@GetMapping("/ModificaGiacenzaProdotto")
	public String vaiModificaGiacenzaProdotto() 
	{
		return "gestioneProdotti";
	}
	
	@PostMapping("/ModificaGiacenzaProdotto")
	public String ModificaGiacenzaProdotto(String nomeProdottoDaMod, Double prezzoProdottoDaMod, Integer giacenzaProdottoDaMod, Model m) 
	{
		Prodotto prd = this.prdServ.trovaProdotto(nomeProdottoDaMod);
		prd.setQntGiacenza(giacenzaProdottoDaMod);
		this.prdServ.modificaProdotto(prd);
		m.addAttribute("esitoModifica", "Modifica avvenuta");
		return "gestioneProdotti";
	}
	
	
	//ORDINABAR
	@GetMapping("/OrdinaBar")
	public String vaiOrdinaBar(HttpSession session, Model m)
	{
		if(session.getAttribute("sessioneCliente")!=null)
		{
			if(session.getAttribute("contoAperto")!=null)
			{
				m.addAttribute("contoAperto", "aperto");
				session.setAttribute("ultimoConto", cBarS.ultimoConto().get());
			}
			
			String username = (String) session.getAttribute("sessioneCliente");
			
			Cliente c = cS.clienteUsername(username);
			
			if(accS.ultimoAccessoDaCliente(c)!=null 
					&& 
			accS.accessoCliente(accS.ultimoAccessoDaCliente(c).getIdAccesso()).getStatoAccAss().getNomeStatoAccesso().equals("InCorso"))
			{
					m.addAttribute("ElencoProdotti", prdServ.elencoProdotti());
					
					if(session.getAttribute("ultimoConto")!=null)
					{
						ContoBar ultimoConto = (ContoBar) session.getAttribute("ultimoConto");
						
						List<ComprendeProdotto> elComprende = cmprServ.elencoComprendeProdotti();
						
						List<ComprendeProdotto> lista = new ArrayList<>();
						
						for(ComprendeProdotto cp : elComprende)
						{
							if(cp.getRifAcqConto().equals(ultimoConto))
							{
								lista.add(cp);
							}
						}
						
						m.addAttribute("ElencoComprende", lista);
					}
					
					return "OrdinaBar";
			}
			else
			{
				return "redirect:homeCliente";
			}
			
		}
		else
		{
			return "redirect:loginCliente";
		}
	}
	
	@PostMapping("/apriConto")
	public String apriConto(HttpSession session, Model m)
	{
		session.setAttribute("contoAperto", "aperto");
		
		m.addAttribute("contoAperto", "aperto");
		
		cBarS.inserisciContoBar(new ContoBar(0.0));
		
		return "redirect:OrdinaBar";
	}
	
	@PostMapping("/aggiungiProdotto")
	public String OrdinaBar(@RequestParam String nomeProdotto, Double prezzoProdotto, HttpSession session,Model m) 
	{
		ContoBar conto = (ContoBar) session.getAttribute("ultimoConto");
		
		Prodotto p = (Prodotto) prdServ.ricercaProdotto(nomeProdotto).get();
		
		Boolean pagato=false;
		
		cmprServ.inserisciComprendeProdotto(new ComprendeProdotto(pagato, conto, p));
		
		if(conto.getTotContoBar()==0.0)
		{
			conto.setTotContoBar(prezzoProdotto);
		}
		else
		{
			Double totConto = conto.getTotContoBar();
			
			conto.setTotContoBar(totConto+prezzoProdotto);
		}
		
		cBarS.inserisciContoBar(conto);
		
		Integer giacenza = p.getQntGiacenza();
		
		p.setQntGiacenza(giacenza-1);
		
		prdServ.InserisciProdotto(p);
		
		return "redirect:OrdinaBar";
	}
	
	@PostMapping("/rimuoviProdotto")
	public String rimuoviProdotto(@RequestParam Integer idProdotto, String nomeProdotto, Double prezzoProdotto, HttpSession session, Model m)
	{
		ContoBar conto = (ContoBar) session.getAttribute("ultimoConto");
		
		conto.setTotContoBar(conto.getTotContoBar()-prezzoProdotto);
		
		cBarS.inserisciContoBar(conto);
		
		Prodotto p = (Prodotto) prdServ.ricercaProdotto(nomeProdotto).get();
		
		p.setQntGiacenza(p.getQntGiacenza()+1);
		
		prdServ.InserisciProdotto(p);
		
		List<ComprendeProdotto> compProd = cmprServ.cercaIdProdotto(prdServ.ricercaProdotto(nomeProdotto).get());
		
		cmprServ.cancella(compProd.get(0));
		
		return "redirect:OrdinaBar";
	}
	
	@PostMapping("/chiudiConto")
	public String chiudiConto(HttpSession session, Model m)
	{
		String userCliente = (String) session.getAttribute("sessioneCliente");
		
		Accesso accCliente = accS.accessoCliente(accS.ultimoAccessoDaCliente(cS.clienteUsername(userCliente)).getIdAccesso());
		
		ContoBar conto = (ContoBar) session.getAttribute("ultimoConto");
		
		accCliente.setContoBarAss(conto);
		
		accS.creaAccesso(accCliente);
		
		m.addAttribute("contoAperto", "chiuso");
		
		return "redirect:homeCliente";
	}
	
	
	///////////////////////////////////////////////////////////////
	//REGISTRAZIONI, LOGIN E LOGOUT
	@GetMapping("/loginAdmin")
	public String vaiLoginAdmin(HttpSession session)
	{
		if(session.getAttribute("sessioneAmministratore")!=null)
    	{
    		return "redirect:homeAmministratore";
    	}
		else
		{
			return "loginAdmin";
		}
	}	
	
	@PostMapping("/effettuaLoginAdmin")
	public String effettuaLogin(@RequestParam String userDipendente, String pswDipendente, HttpSession session, Model m)
	{
		String esitoLogin="Credenziali errate";
		
		if(dS.loginDipendente(userDipendente, pswDipendente).equals(true))
		{
			esitoLogin = "Login Effettuato";
			m.addAttribute("login", esitoLogin);
			
			if(dS.dipendenteUsername(userDipendente).getCatDipDisp().getNomeCategoriaDipendente().equals("Amministratore"))
			{
				session.setAttribute("sessioneAmministratore", userDipendente);
				
				return "redirect:homeAmministratore";
			}
		}
		m.addAttribute("login", esitoLogin);
		return "loginAdmin";
	}
	
	@PostMapping("/logoutAdmin")
	public String logoutAdmin(HttpSession session)
	{
		session.invalidate();
		
		return "redirect:loginAdmin";
	}
	
	
	@GetMapping("/loginDipendente")
    public String vaiLoginDipendente(HttpSession session)
    {
    	if(session.getAttribute("sessioneSegretaria")!=null)
    	{
    		return "redirect:homeSegretaria";
    	}
    	else if(session.getAttribute("sessioneBagnino")!=null)
    	{
    		return "redirect:homeBagnino";
    	}
    	else if(session.getAttribute("sessioneBarista")!=null)
    	{
    		return "redirect:homeBarista";
    	}
    	else
    	{
    		return "loginDipendente";
    	}
    	
    	
    	
    	
    	
    }
	
	@PostMapping("/effettuaLoginDipendente")
	public String effettuaLoginDipendente(@RequestParam String userDipendente, String pswDipendente, HttpSession session, Model m)
	{
		String esitoLogin="Credenziali errate";
		
		if(dS.loginDipendente(userDipendente, pswDipendente).equals(true))
		{
			
			m.addAttribute("login", esitoLogin);
			
			if(dS.dipendenteUsername(userDipendente).getCatDipDisp().getNomeCategoriaDipendente().equals("Segretaria"))
			{
				esitoLogin="Login effettuato";

				session.setAttribute("sessioneSegretaria", userDipendente);
				
				return "redirect:homeSegretaria";
			}
			if(dS.dipendenteUsername(userDipendente).getCatDipDisp().getNomeCategoriaDipendente().equals("Bagnino"))
			{
				esitoLogin="Login effettuato";

				session.setAttribute("sessioneBagnino", userDipendente);
				
				return "redirect:homeBagnino";
			}
			if(dS.dipendenteUsername(userDipendente).getCatDipDisp().getNomeCategoriaDipendente().equals("Barista"))
			{
				esitoLogin="Login effettuato";

				session.setAttribute("sessioneBarista", userDipendente);
				
				return "redirect:homeBarista";
			}
			
			return "loginDipendente";
		}
		else
		{
			m.addAttribute("login", esitoLogin);
			return "loginDipendente";
		}
		
	}
	
	@PostMapping("/logoutDipendente")
	public String logoutDipendente(HttpSession session)
	{
		session.invalidate();
		
		return "redirect:loginDipendente";
	}
	
	@GetMapping("/loginCliente")
    public String vaiLoginCliente()
    {
    	return "loginCliente";
    }
    
    @PostMapping("/effettuaLoginCliente")
	public String effettuaLoginCliente(@RequestParam String usernameCliente, String passwordCliente, HttpSession session, Model m)
	{
		String esitoLogin="Credenziali errate";
		
		if(cS.loginCliente(usernameCliente, passwordCliente).equals(true))
		{
			esitoLogin="Login effettuato";
			session.setAttribute("sessioneCliente", usernameCliente);
			m.addAttribute("login", esitoLogin);
			return "redirect:homeCliente";
		}
		else
		{
			m.addAttribute("login", esitoLogin);
			return "loginCliente";
		}
		
	}
	
	@PostMapping("/logoutCliente")
	public String logoutCliente(HttpSession session)
	{
		session.invalidate();
		
		return "redirect:loginCliente";
	}
	
	@GetMapping("/registratiCliente")
    public String vaiRegistratiCliente(HttpSession session, Model m)
    {
		return "registratiCliente";
    }
	
	@PostMapping("/registrati")
    public String registrati(@ModelAttribute Cliente c, HttpSession session, Model m)
    {
    	String messaggio;
		
		c.setAbbonato(false);
		
		if(cS.inserimentoCliente(c))
		{
			messaggio="Inserimento avvenuto";
		}
		else
		{
			messaggio="Codice Fiscale già presente. Inserimento non avvenuto.";
		}

		m.addAttribute("esitoInserimento", messaggio);
    	
    	return "registratiCliente";
    }
	
}

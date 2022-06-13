package it.prova.myebay.dto;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;

public class UtenteEdit extends UtenteDTO {
	private Ruolo ruolo;
	private StatoUtente stato;
	
	public UtenteEdit() {}
	public UtenteEdit(String nome, String cognome, String username, StatoUtente stato) {
		super.nome = nome;
		super.cognome = cognome;
		super.username = username;
		
		this.stato = stato;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public StatoUtente getStato() {
		return stato;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "UtenteEdit [ruolo=" + ruolo + ", stato=" + stato + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + "]";
	}
	
	
	public Utente toModel() {
		Utente model = new Utente();
			model.setNome(this.nome);
			model.setCognome(this.cognome);
			model.setUsername(this.username);
			model.setRuolo(this.ruolo);
			model.setStato(this.stato);
		
		return model;
	}
	
	
}

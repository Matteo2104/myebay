package it.prova.myebay.dto;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public class UtenteInsert extends UtenteDTO {
	private String password;
	private Ruolo ruolo;

	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "UtenteInsert [password=" + password + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + "]";
	}
	
	
	public Utente toModel() {
		Utente model = new Utente();
			model.setNome(this.nome);
			model.setCognome(this.cognome);
			model.setUsername(this.username);
			model.setPassword(this.password);
			model.setRuolo(this.ruolo);
		
		return model;
	}
	
}

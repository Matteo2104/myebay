package it.prova.myebay.dto;

import java.util.ArrayList;
import java.util.List;
import it.prova.myebay.model.Ruolo;

public abstract class UtenteDTO {
	protected String nome;
	protected String cognome;
	protected String username;
	protected List<Ruolo> ruoli = new ArrayList<>();
	
	protected UtenteDTO() {
		
	}
	protected UtenteDTO(String nome, String cognome, String username) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	@Override
	public String toString() {
		return "UtenteDTO [nome=" + nome + ", cognome=" + cognome + ", username=" + username + "]";
	}
	
	
}

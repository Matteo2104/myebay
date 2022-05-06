package it.prova.myebay.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public abstract class UtenteDTO {
	protected String nome;
	protected String cognome;
	protected String username;
	protected Date dateCreated;
	protected List<Ruolo> ruoli = new ArrayList<>();
	
	public UtenteDTO() {
		
	}
	public UtenteDTO(String nome, String cognome, String username) {
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	@Override
	public String toString() {
		return "UtenteDTO [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", dateCreated="
				+ dateCreated + "]";
	}
	
	/*
	public Utente createUtenteModelFromDTO(UtenteDTO utenteDTO) {
		Utente utente = new Utente();
		
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setDateCreated(utenteDTO.getDateCreated());
		utente.setStato(utenteDTO.getStato());
		utente.setRuolo(utenteDTO.getRuolo());
		utente.setUsername();
		utente.setCreditoResiduo();
		//utente.setAnnunci();
		//utente.setAcquisti();
	}
	*/
	
	
}

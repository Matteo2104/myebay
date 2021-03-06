package it.prova.myebay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "codice")
	private String codice;
	
	public Categoria() {}
	public Categoria(Long id) {
		this.id = id;
	}
	public Categoria(String descrizione, String codice) {
		this.descrizione = descrizione;
		this.codice = codice;
	}
	
	
	public Long getId() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getCodice() {
		return codice;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descrizione=" + descrizione + ", codice=" + codice + "]";
	}
	
}

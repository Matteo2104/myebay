package it.prova.myebay.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annuncio")
public class Annuncio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "testoAnnuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private int prezzo;
	@Column(name = "aperto")
	private boolean aperto;
	@Column(name = "data")
	private Date data;

	// un annuncio è mappato da un solo utente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id",nullable = false)
	private Utente utenteInserimento;
	
	// un annuncio ha più categorie, e una categoria è mappata da più annunci
	@ManyToMany
	@JoinTable(name = "annuncio_categoria", joinColumns = @JoinColumn(name = "annuncio_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
	private Set<Categoria> categorie = new HashSet<>();

	public Annuncio() {}
	public Annuncio(String titolo, String testoAnnuncio) {
		this.titolo = titolo;
		this.testoAnnuncio = testoAnnuncio;
	}
	

	public Long getId() {
		return id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public boolean isAperto() {
		return aperto;
	}

	public Date getData() {
		return data;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	
	@Override
	public String toString() {
		return "Annuncio [id=" + id + ", titolo=" + titolo + ", testoAnnuncio=" + testoAnnuncio + ", prezzo=" + prezzo
				+ ", aperto=" + aperto + ", data=" + data + ", utenteInserimento=" + utenteInserimento + "]";
	}
	
}

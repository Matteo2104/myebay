package it.prova.myebay.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "utente")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "dateCreated")
	private Date dateCreated;
	@Column(name = "creditoResiduo")
	private int creditoResiduo;

	
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;

	// un utente ha più ruoli
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	// un utente ha più annunci
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utenteInserimento")
	private Set<Annuncio> annunci = new HashSet<Annuncio>(0);
	
	// un utente può fare più acquisti
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utenteAcquirente")
	private Set<Acquisto> acquisti = new HashSet<Acquisto>(0);
	
	public Utente() {
	}
	public Utente(String nome, String cognome, String username) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
	}
	public Utente(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Utente(String username, String password, String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public int getCreditoResiduo() {
		return creditoResiduo;
	}
	public StatoUtente getStato() {
		return stato;
	}
	public Set<Ruolo> getRuoli() {
		return ruoli;
	}
	public Set<Annuncio> getAnnunci() {
		return annunci;
	}
	public Set<Acquisto> getAcquisti() {
		return acquisti;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void setCreditoResiduo(int creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	public void setAnnunci(Set<Annuncio> annunci) {
		this.annunci = annunci;
	}
	public void setAcquisti(Set<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", dateCreated=" + dateCreated + ", creditoResiduo=" + creditoResiduo
				+ ", stato=" + stato + "]";
	}
}

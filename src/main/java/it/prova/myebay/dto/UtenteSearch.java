package it.prova.myebay.dto;

import java.util.Date;

public class UtenteSearch extends UtenteDTO {
	private Date dateCreated;
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}

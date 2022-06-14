package it.prova.myebay.exception.service;

public class AnnuncioServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AnnuncioServiceException(String message) {
		super(message);
	}
}

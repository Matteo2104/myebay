package it.prova.myebay.exception;

public class InsufficientCreditException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InsufficientCreditException(String message) {
		super(message);
	}
}

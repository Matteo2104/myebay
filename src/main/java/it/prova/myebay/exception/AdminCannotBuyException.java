package it.prova.myebay.exception;

public class AdminCannotBuyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AdminCannotBuyException(String message) {
		super(message);
	}
}

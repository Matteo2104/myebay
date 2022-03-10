package it.prova.myebay.exception;

public class MissedLoginException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MissedLoginException(String message) {
		super(message);
	}
}

package it.prova.myebay.exception;

public class UserRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserRegisteredException(String message) {
		super(message);
	}
}

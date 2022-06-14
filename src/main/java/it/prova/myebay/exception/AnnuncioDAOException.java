package it.prova.myebay.exception;

public class AnnuncioDAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AnnuncioDAOException(String message) {
		super(message);
	}
}

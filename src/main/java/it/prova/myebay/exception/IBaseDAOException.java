package it.prova.myebay.exception;

public class IBaseDAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IBaseDAOException(String message) {
		super(message);
	}
}

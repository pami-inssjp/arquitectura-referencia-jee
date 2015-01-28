package ar.gob.pami.jboss.jee.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public abstract class PamiException extends RuntimeException {

	private static final long serialVersionUID = 2344758711862261782L;
	private String statusCode;

	public abstract String getIdentifier();

	public PamiException(String statusCode, String message) {
		super(message);
		this.setStatusCode(statusCode);
	}

	public PamiException(String statusCode, String message, Throwable t) {
		super(message, t);
		this.setStatusCode(statusCode);
	}

	public String getStatusCode() {
		return this.getIdentifier() + this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}

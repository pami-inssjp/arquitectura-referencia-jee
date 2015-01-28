package ar.gob.pami.jboss.jee.exceptions;

public class TokenInvalidoException extends PamiException {

	public TokenInvalidoException(String statusCode, String message, Throwable t) {
		super(statusCode, message, t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6380796624324027483L;

	@Override
	public String getIdentifier() {
		return "T";
	}

}

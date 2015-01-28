package ar.gob.pami.jboss.jee.exceptions;

public class PamiConcreteException extends PamiException {

	private static final long serialVersionUID = 4327374595858647591L;

	public PamiConcreteException(String statusCode, String message) {
		super(statusCode, message);
	}

	@Override
	public String getIdentifier() {
		return "P1";
	}

}

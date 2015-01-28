package ar.gob.pami.jboss.jee.rest.handlers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import ar.gob.pami.jboss.jee.exceptions.PamiException;
import ar.gob.pami.jboss.jee.exceptions.TokenInvalidoException;
import ar.gob.pami.jboss.jee.model.GenericResponse;
import ar.gob.pami.jboss.jee.util.FileReader;

@Provider
@ServerInterceptor
public class RestSecurityInterceptor implements PreProcessInterceptor {

	@Inject
	private Logger logger;

	@Inject
	private FileReader reader;

	@Inject
	private JWTValidator validator;

	@Override
	public ServerResponse preProcess(HttpRequest httpRequest,
			ResourceMethod resourceMethod) throws Failure,
			WebApplicationException {

		logger.info("PreProcess");

		byte[] secret = this.getSecret();
		String token = this.getToken(httpRequest);

		try {
			validator.getPayload(token, secret);
		} catch (TokenInvalidoException e) {
			return createResponseTokenInvalido(e);
		}

		return null;

	}

	private ServerResponse createResponseTokenInvalido(PamiException e) {
		GenericResponse<StackTraceElement[]> genericResponse = new GenericResponse<StackTraceElement[]>();
		genericResponse.setData(e.getStackTrace());
		genericResponse.setDescription(e.getMessage());
		genericResponse.setStatusCode(e.getStatusCode());
		return (ServerResponse) ServerResponse.ok(genericResponse).build();
	}

	private byte[] getSecret() {
		return reader.readFileAsBytes("internal-public.der");
	}

	private String getToken(HttpRequest httpRequest) {
		HttpHeaders headers = httpRequest.getHttpHeaders();
		List<String> authorization = headers.getRequestHeader("Authorization");
		return authorization.get(0);
	}
}

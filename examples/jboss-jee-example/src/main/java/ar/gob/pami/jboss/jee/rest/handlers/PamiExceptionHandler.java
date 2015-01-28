package ar.gob.pami.jboss.jee.rest.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ar.gob.pami.jboss.jee.exceptions.PamiException;
import ar.gob.pami.jboss.jee.model.GenericResponse;

@Provider
public class PamiExceptionHandler implements ExceptionMapper<PamiException> {

	@Override
	public Response toResponse(PamiException pamiException) {
		GenericResponse<StackTraceElement[]> exceptionObject = new GenericResponse<StackTraceElement[]>();
		exceptionObject.setStatusCode(pamiException.getStatusCode());
		exceptionObject.setDescription((pamiException.getLocalizedMessage()));
		exceptionObject.setData(pamiException.getStackTrace());
		return Response.ok(exceptionObject).build();
	}
}

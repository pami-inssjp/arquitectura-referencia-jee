package ar.gob.pami.jboss.jee.rest.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ar.gob.pami.jboss.jee.model.GenericResponse;

@Provider
public class UnknownExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception arg0) {
		GenericResponse<StackTraceElement[]> exceptionObject = new GenericResponse<StackTraceElement[]>();
		exceptionObject.setStatusCode("9999");
		exceptionObject.setDescription(arg0.getLocalizedMessage());
		exceptionObject.setData(arg0.getStackTrace());
		return Response.ok(exceptionObject).build();
	}

}

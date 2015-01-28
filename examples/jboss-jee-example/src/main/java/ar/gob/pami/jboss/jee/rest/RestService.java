package ar.gob.pami.jboss.jee.rest;

import javax.ws.rs.core.Response;

import ar.gob.pami.jboss.jee.model.GenericResponse;

public class RestService {

	private static final String STATUS_OK = "0";

	public <T> Response ok(T entity) {
		GenericResponse<T> genericResponse = new GenericResponse<T>();
		genericResponse.setStatusCode(STATUS_OK);
		genericResponse.setDescription("Ok");
		genericResponse.setData(entity);
		return Response.ok(genericResponse).build();
	}

}

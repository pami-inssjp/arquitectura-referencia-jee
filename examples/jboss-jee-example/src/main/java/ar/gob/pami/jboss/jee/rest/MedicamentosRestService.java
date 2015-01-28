package ar.gob.pami.jboss.jee.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.gob.pami.jboss.jee.exceptions.PamiConcreteException;
import ar.gob.pami.jboss.jee.repositories.MedicamentoRepository;

@Stateless
@Path("medicamentos")
public class MedicamentosRestService extends RestService {

	@Inject
	private MedicamentoRepository home;

	@Path("/")
	@GET
	@Produces("application/json")
	public Response all() {
		return ok(home.all());
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		return ok(home.findById(id));
	}

	@Path("/ue")
	@GET
	@Produces("application/json")
	public Response unknownException() {
		throw new RuntimeException();
	}

	@Path("/e")
	@GET
	@Produces("application/json")
	public Response knownException() {
		throw new PamiConcreteException("1000", "Excepcion conocida de Pami");
	}
}

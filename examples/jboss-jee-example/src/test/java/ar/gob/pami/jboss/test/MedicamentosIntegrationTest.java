package ar.gob.pami.jboss.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.gob.pami.jboss.jee.model.GenericResponse;
import ar.gob.pami.jboss.jee.model.Medicamento;
import ar.gob.pami.jboss.jee.repositories.MedicamentoRepository;
import ar.gob.pami.jboss.jee.rest.MedicamentosRestService;

@RunWith(Arquillian.class)
public class MedicamentosIntegrationTest {

	@Deployment(testable = true)
	public static WebArchive createDeployment() {

		File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml")
				.importCompileAndRuntimeDependencies().resolve()
				.withTransitivity().asFile();

		return ShrinkWrap
				.create(WebArchive.class, "medicamentos-integration-test.war")
				.addPackages(true, "ar.gob.pami.jboss.jee")
				.addPackages(true, "io.jsonwebtoken")
				.addPackages(true, " org.jboss")
				.addAsLibraries(dependencies)
				.addAsResource("rsapublic1.pem")
				.addAsResource("rsapublic2.pem")
				.addAsResource("internal-public.der")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml");
	}

	@Inject
	private MedicamentoRepository repository;

	@Inject
	private MedicamentosRestService service;

	@Before
	public void setUp() {
		Medicamento medicamento = new Medicamento();
		medicamento.setLaboratorio("Sarasa");

		repository.save(medicamento);
	}

	@Test
	public void test() throws Exception {
		Response response = service.all();
		GenericResponse<List<Medicamento>> genericResponse = (GenericResponse<List<Medicamento>>) response
				.getEntity();

		assertEquals(1, genericResponse.getData().size());
	}
}

package ar.gob.pami.jboss.test;

import java.io.File;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.gob.pami.jboss.jee.model.Medicamento;

@RunWith(Arquillian.class)
public class MedicamentosRestIntegrationTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {

		File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml")
				.importCompileAndRuntimeDependencies().resolve()
				.withTransitivity().asFile();

		return ShrinkWrap
				.create(WebArchive.class, "test.war")
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

	@ArquillianResource
	URL deploymentUrl;

	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJjbGllbnRfbmFtZSI6IkFQUE5BTUVUT0NPTkVDVCIsInByb2ZpbGVzIjpbeyJwIjoidmFkZW1lY3VtIiwiaWQiOjQ1MjEzNX0seyJwIjoidHJhemEiLCJpZCI6NDU2MjN9LHsicCI6Im90cm9zIiwiaWQiOiJ3aGF0ZXZlciJ9XSwic2NvcGVzIjpbImxlY3R1cmEiLCJlc2NyaXR1cmEiLCJjb21wYXJ0ZWRhdGEiLCJjb250YWN0b3BvcnNtcyJdLCJpYXQiOjE0MjE3NzMwMDcsImV4cCI6MTQ1MzMwOTAwNywiYXVkIjoidmFkZW1lY3VtIiwiaXNzIjoiYXBpbWFuYWdlciIsInN1YiI6IjYyYzM2MDkyLTgyYTEtM2EwMC05M2QxLTQ2MTk2ZWU3NzIwNCJ9.GJurURvsLpyvGm4kaDlnSxkIM198wqhlYLq4bprMwmPCmt4k33e7jHoOGT6ccBemkFbtOoKR6e2wYg1Ik7ZG8xSEBNSqmWuu-kvcmU5BAI-VitqVd9qdaWsK86V3UHEGEzZ-AmZeE63I6q9ZbS4562rkictaNa5iloJYyTZ2N9e30VjaJDU-PycAgV8IkqrDASq9_1GsVf4Nw8yp5-Z6Z1IotmRF63J-SasmFcH0NPz6H-4e4c_yNjqC_4dU-F-Q7S9jDeDw-kXwD9GMGDe2W7z7pTxwnM8z7oofPgVmN0gGK4vzg5lMCN5inpWkOfTXqY4KfDNz2R_CT2fiIK3NPg";

	@Before
	public void setUp() throws Exception {
		Medicamento medicamento = new Medicamento();
		medicamento.setLaboratorio("Sarasa");
		this.saveMedicamento(medicamento);
	}

	public void saveMedicamento(Medicamento medicamento) throws Exception {
		ClientRequest request = new ClientRequest(deploymentUrl.toString()
				+ "pami/" + "medicamentos");
		request.header("Accept", MediaType.APPLICATION_JSON);
		request.header("Authorization", token);
		request.body(MediaType.APPLICATION_JSON, medicamento);

		ClientResponse<String> responseObj = request.post(String.class);
	}

	@Test
	public void obtengoTodosMedicamentosYTengoUno() throws Exception {
		System.out.println(deploymentUrl.toString());
		ClientRequest request = new ClientRequest(deploymentUrl.toString()
				+ "pami/" + "medicamentos");
		request.header("Accept", MediaType.APPLICATION_JSON);
		request.header("Authorization", token);

		ClientResponse<String> responseObj = request.get(String.class);

		System.out.println("GET /customer/1 HTTP/1.1\n\n"
				+ responseObj.getEntity());
		Assert.assertEquals(200, responseObj.getStatus());

	}

	@Test
	public void excepcionDesconocida() throws Exception {
		System.out.println(deploymentUrl.toString());
		ClientRequest request = new ClientRequest(deploymentUrl.toString()
				+ "pami/" + "medicamentos/ue");
		request.header("Accept", MediaType.APPLICATION_JSON);
		request.header("Authorization", token);

		ClientResponse<String> responseObj = request.get(String.class);

		System.out.println("GET /customer/1 HTTP/1.1\n\n"
				+ responseObj.getEntity());
		Assert.assertEquals(200, responseObj.getStatus());

	}

	@Test
	public void excepcionConocida() throws Exception {
		System.out.println(deploymentUrl.toString());
		ClientRequest request = new ClientRequest(deploymentUrl.toString()
				+ "pami/" + "medicamentos/e");
		request.header("Accept", MediaType.APPLICATION_JSON);
		request.header("Authorization", token);

		ClientResponse<String> responseObj = request.get(String.class);

		System.out.println("GET /customer/1 HTTP/1.1\n\n"
				+ responseObj.getEntity());
		Assert.assertEquals(200, responseObj.getStatus());

	}
}

package co.cmamo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cmamo.ejb.*;

@RunWith(Arquillian.class)
public class TestAdminEJB {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void crearEmpleadoTest() {
		Empleado e = new Empleado();
		e.setApellido("Quiceno Laurente");
		e.setClave("123");
		e.setCorreo("ccquicenol@uqvirtual.edu.co");
		e.setEstado(EstadoActividad.ACTIVO);
		e.setId("22");
		e.setPeticiones(new ArrayList<Peticion>());
		e.setSalario(123.0);
		
		assertTrue(adminEJB.crearEmpleado(e));
	}
	
}

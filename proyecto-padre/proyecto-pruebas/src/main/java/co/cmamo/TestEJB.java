package co.cmamo;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cmamo.ejb.AdminEJB;


@RunWith(Arquillian.class)
public class TestEJB {

	@EJB
	private AdminEJB adminEJB;
	
	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	public void crearEmpleadoTest() {
		Empleado e = new Empleado();
		e.setApellido("Quiceno");
		e.setClave("123");
		e.setCorreo("correo@mail.com");
		e.setEstado(EstadoActividad.ACTIVO);
		e.setId("123");
		e.setNombre("Marcela");
		e.setPeticiones(null);
		e.setSalario(0);
		
		assertTrue(adminEJB.crearEmpleado(e));
	}

}

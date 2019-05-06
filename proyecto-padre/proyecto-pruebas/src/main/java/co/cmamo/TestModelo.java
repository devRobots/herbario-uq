package co.cmamo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author EinerZG
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestModelo {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {
		
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.yml"})
	public void findTest() {
		Planta p = entityManager.find(Planta.class, "cosa1");
		Assert.assertEquals("John", p.getFamilia());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.yml"})
	public void persistTest() {
		Empleado e = new Empleado();
		e.setApellido("Cardenas");
		e.setNombre("Juliana");
		e.setId("1234");
		e.setClave("12345");
		e.setCorreo("jc@mail.com");
		e.setEstado(EstadoActividad.ACTIVO);
		e.setSalario(50007000);
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}

}

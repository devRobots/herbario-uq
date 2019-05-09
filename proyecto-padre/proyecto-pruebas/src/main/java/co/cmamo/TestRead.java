package co.cmamo;

import static org.junit.Assert.*;

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

@RunWith(Arquillian.class)
public class TestRead {
	
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
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void findFamiliaTest() {
		Familia f = entityManager.find(Familia.class, "1234");
		Assert.assertEquals("Rose", f.getNombre());
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void findGeneroTest() {
		Genero g = entityManager.find(Genero.class, "1234");
		Assert.assertEquals("Roselin", g.getNombre());
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void findPersonaTest() {
		Empleado p = entityManager.find(Empleado.class, "9765");
		Assert.assertEquals("Fernanda", p.getNombre());
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void findPlantaTest() {
		Planta p = entityManager.find(Planta.class, "4321");
		Assert.assertEquals("cosa1", p.getEspecie());
	}
	/**
	 * 
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"registro.json"})
	public void findRegistroTest() {
		Registro p = entityManager.find(Registro.class, "4321");
		Assert.assertEquals("cosa1", p.getEspecie());
	}
	*/
}

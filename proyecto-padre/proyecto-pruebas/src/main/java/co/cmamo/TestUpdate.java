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
public class TestUpdate {
	
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
	@UsingDataSet({"persona.json"})
	public void updatePersonaTest() {
		Empleado p = entityManager.find(Empleado.class, "9765");
		Assert.assertEquals("Fernanda", p.getNombre());

		Persona e = entityManager.find(Persona.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Persona.class, "1234"));
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void updatePlantaTest() {
		Empleado p = entityManager.find(Empleado.class, "9765");
		Assert.assertEquals("Fernanda", p.getNombre());

		Persona e = entityManager.find(Persona.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Persona.class, "1234"));
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void updateFamiliaTest() {
		Empleado p = entityManager.find(Empleado.class, "9765");
		Assert.assertEquals("Fernanda", p.getNombre());

		Persona e = entityManager.find(Persona.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Persona.class, "1234"));
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void updateGeneroTest() {
		Empleado p = entityManager.find(Empleado.class, "9765");
		Assert.assertEquals("Fernanda", p.getNombre());

		Persona e = entityManager.find(Persona.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Persona.class, "1234"));
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}
	/**
	 * 
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"peticion.json"})
	public void updatePeticionTest() {
		Peticion p = entityManager.find(Peticion.class, "9765");
		Assert.assertEquals("Fernanda", p.getId());

		Persona e = entityManager.find(Persona.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Persona.class, "1234"));
		
		entityManager.persist(e);
		
		Empleado registrado = entityManager.find(Empleado.class, e.getId());
		Assert.assertEquals(e, registrado);
	}

}

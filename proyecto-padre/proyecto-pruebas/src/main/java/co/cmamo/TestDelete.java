package co.cmamo;


import static org.junit.Assert.assertEquals;

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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestDelete {
	
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
	 * Prueba eliminar familia
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void eliminarFamiliaTest() {
		Familia e = entityManager.find(Familia.class, "1534");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Familia.class, "1534"));
	}
	/**
	 * Prueba eliminar Genero
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void eliminarGeneroTest() {
		Genero e = entityManager.find(Genero.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Genero.class, "1234"));
	}
	/**
	 * Prueba eliminar persona
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void eliminarPersonaTest() {
		Administrador e = entityManager.find(Administrador.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Administrador.class, "1234"));
	}
	/**
	 * Prueba eliminar planta
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void eliminarPlantaTest() {
		Planta e = entityManager.find(Planta.class, "4321");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Planta.class, "4321"));
	}
	/**
	 * Prueba eliminar Peticion
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"peticion.json"})
	public void eliminarPeticionTest() {
		Peticion e = entityManager.find(Peticion.class, "1234");
		entityManager.remove(e);
		
		assertEquals("No se elimino",null,entityManager.find(Peticion.class, "1234"));
	}
}

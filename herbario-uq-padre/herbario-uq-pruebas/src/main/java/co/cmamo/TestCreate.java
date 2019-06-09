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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestCreate {
	
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
	 *  Prueba crear familia
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void persistFamiliaTest() {
		Familia e = new Familia();
		e.setId("78");
		e.setNombre("Joselin");
		
		entityManager.persist(e);
		
		Familia familia = entityManager.find(Familia.class, e.getId());
		assertEquals(e, familia);
	}
	/**
	 *  Prueba crear Genero
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json", "familia.json"})
	public void persistGeneroTest() {
		Genero e = new Genero();
		e.setId("738");
		e.setNombre("Joselin");
		e.setFamilia(entityManager.find(Familia.class, "1534"));
		
		entityManager.persist(e);
		
		Genero genero = entityManager.find(Genero.class, e.getId());
		assertEquals(e, genero);
	}
	
	/**
	 *  Prueba crear Planta
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json","genero.json"})
	public void persistPlantaTest(){
		Planta e = new Planta();
		e.setId("738");
		e.setEspecie("");
		e.setGenero(entityManager.find(Genero.class, "1230"));
		
		entityManager.persist(e);
		
		Planta genero = entityManager.find(Planta.class, e.getId());
		assertEquals(e, genero);
	}
	
	/**
	 *  Test Crear Persona
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void persistPersonaTest() {
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
		assertEquals(e, registrado);
	}

}

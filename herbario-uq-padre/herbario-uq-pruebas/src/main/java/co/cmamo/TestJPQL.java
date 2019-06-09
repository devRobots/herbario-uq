package co.cmamo;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
public class TestJPQL {

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
	 * listar familias test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void listarFamiliasTest() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_TODOS, Familia.class);
		
		List<Familia> familias = query.getResultList();

		assertEquals(20, familias.size());
	}

	/**
	 * listar Generos test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "genero.json" })
	public void listarGenerosTest() {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_TODOS, Genero.class);
		List<Genero> generos = query.getResultList();

		assertEquals(40, generos.size());
	}

	/**
	 * listar plantas test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json" })
	public void listarPlantasTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_TODOS, Planta.class);

		List<Planta> plantas = query.getResultList();
		
		assertEquals(80, plantas.size());
	}

	/**
	 * listar personas test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonasTest() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);
		List<Persona> personas = query.getResultList();

		assertEquals(20, personas.size());
	}

	/**
	 * lista de peticiones test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "peticion.json" })
	public void listarPeticionTest() {
		TypedQuery<Peticion> query = entityManager.createNamedQuery(Peticion.LISTAR_TODOS, Peticion.class);
		List<Peticion> peticiones = query.getResultList();

		assertEquals(40, peticiones.size());
	}

	/**
	 * LogInTest
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void logInTest() {
		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.INICIAR_SESION, Persona.class);
			query.setParameter("correo", "montes.nascetur.ridiculus@lectussitamet.ca");
			query.setParameter("clave", "9281");
			Persona p = query.getSingleResult();
			assertNotNull(p);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Listar Planta por Genero Test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json" })
	public void listarPlantasPorGeneroTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_POR_GENERO, Planta.class);
		query.setParameter("genero", "34");
		List<Planta> plantas = query.getResultList();

		assertEquals(2, plantas.size());
	}

	/**
	 * 
	 * Listar Planta por Familia Test
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json", "familia.json" })
	public void listarPlantasPorFamiliaTest() {
		Familia f = entityManager.find(Familia.class, "10");
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_POR_FAMILIA, Planta.class);
		query.setParameter("familia", f);
		
		List<Planta> plantas = query.getResultList();

		assertEquals(13, plantas.size());
	}

}

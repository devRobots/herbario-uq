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

import co.cmamo.dto.ConsultaDTO;

@RunWith(Arquillian.class)
public class TestTaller {
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
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void testContarFamilias() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Familia.CONTAR_TODOS, Long.class);
		long cantFamilias = query.getSingleResult();

		assertEquals(4, cantFamilias);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "peticion.json" })
	public void testContarPersonas() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Peticion.CONTAR_PERSONAS_ACEPTADAS, Long.class);
		query.setParameter("estado", EstadoPeticion.APROBADO);

		long cantPersonasAceptadas = query.getSingleResult();

		assertEquals(2, cantPersonasAceptadas);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void testPersonasSinRegistros() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_SIN_PETICIONES, Persona.class);

		List<Persona> personasSinPeticiones = query.getResultList();

		assertEquals(3, personasSinPeticiones.size());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void testListarCantRegistrosPorPersona() {
		TypedQuery<ConsultaDTO> query = entityManager.createNamedQuery(Persona.CONSULTA_DTO, ConsultaDTO.class);

		List<ConsultaDTO> personasSinPeticiones = query.getResultList();

		assertEquals(3, personasSinPeticiones.size());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json", "genero.json", "planta.json" })
	public void testFamiliaConMasEspecies() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Familia.FAMILIA_MAS_ESPECIES, Object[].class);

		List<Object[]> plantasPorFamilia = query.getResultList();
		
		Familia f = (Familia) plantasPorFamilia.get(0)[0];
		
		System.out.println((Long) plantasPorFamilia.get(0)[1]);

		assertEquals("Rose", f.getNombre());
	}
}

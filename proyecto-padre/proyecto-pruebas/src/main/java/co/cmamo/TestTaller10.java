package co.cmamo;

import static org.junit.Assert.assertEquals;

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
public class TestTaller10 {
	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * genera el archivo de despliegue de pruebas
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
	 * 1. Prueba que permite contar determinar el número de familias que se han registrado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void testContarFamilias() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Familia.CONTAR_TODOS, Long.class);
		
		long cantFamilias = query.getSingleResult();

		assertEquals(20, cantFamilias);
	}
	
	/**
	 * 2. Test que permita contar el número de personas a 
	 * las que le han aceptado un registro (envio) por día
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "peticion.json" })
	public void testContarPersonas() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Peticion.CONTAR_PERSONAS_ACEPTADAS, Long.class);
		query.setParameter("estado", EstadoPeticion.APROBADO);

		List<Long> cantPersonasAceptadas = query.getResultList();
		
		assertEquals(8, cantPersonasAceptadas.size());
	}

	/**
	 * 3. Test que permita determinar que personas no han realizado registros
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void testPersonasSinRegistros() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_SIN_PETICIONES, Persona.class);

		List<Persona> personasSinPeticiones = query.getResultList();

		assertEquals(20, personasSinPeticiones.size());
	}
	

	/**
	 * 4. Test que permita determinar cuantos registros ha realizado cada empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void testListarCantRegistrosPorPersona() {
		TypedQuery<ConsultaDTO> query = entityManager.createNamedQuery(Persona.CONSULTA_DTO, ConsultaDTO.class);
		
		List<ConsultaDTO> personasSinPeticiones = query.getResultList();
		
		assertEquals(20, personasSinPeticiones.size());
	}
	

	/**
	 * 5. Test que permita determinar cual es la familia 
	 * que más especies tiene registradas. 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "planta.json", "genero.json", "familia.json" })
	public void testFamiliaConMasEspecies() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Familia.FAMILIA_MAS_ESPECIES, Object[].class);

		List<Object[]> plantasPorFamilia = query.getResultList();
		
		Familia f = (Familia) plantasPorFamilia.get(0)[0];

		// TODO: :v
		assertEquals("Abel", f.getNombre());
	}
	
	/**
	 * 6. Lo mismo de la consulta anterior en solo una consulta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "planta.json", "genero.json", "familia.json" })
	public void testFamiliasConMasEspecies2() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Familia.FAMILIA_MAS_ESPECIES_2, Object[].class);

		List<Object[]> plantasPorFamilia = query.getResultList();
		
		Familia f = (Familia) plantasPorFamilia.get(0)[0];

		assertEquals("Abel", f.getNombre());
	}
}

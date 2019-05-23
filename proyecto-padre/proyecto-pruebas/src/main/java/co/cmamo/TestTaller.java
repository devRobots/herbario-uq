package co.cmamo;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import co.cmamo.dto.RegistroDTO;

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

		assertEquals(20, cantFamilias);
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

		assertEquals(20, personasSinPeticiones.size());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void testListarCantRegistrosPorPersona() {
		TypedQuery<ConsultaDTO> query = entityManager.createNamedQuery(Persona.CONSULTA_DTO, ConsultaDTO.class);
		
		List<ConsultaDTO> personasSinPeticiones = query.getResultList();
		
		assertEquals(20, personasSinPeticiones.size());
	}

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
	 * Taller 9
	 */
	
	/**
	 * 4
	 * Test named query que dado el ID de un especie vegetal permita 
	 * obtener la  familia relacionada
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json", "familia.json" })
	public void testFamiliaPorIdPlanta() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Planta.OBTENER_FAMILIA, Familia.class);

		query.setParameter("id", "17");
		List<Familia> familiaResultados = query.getResultList();

		assertEquals("Wang", familiaResultados.get(0).getNombre());
	}
	
	/**
	 * 5
	 * Test named query que dado el id de un genero permita obtener todas sus especies
	 *  m�todo Test que permita probar la consulta.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json"})
	public void testEspeciesPorIdGenero() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Genero.OBTENER_TODAS_LAS_PLANTAS, Planta.class);
		
		query.setParameter("id", "14");
		List<Planta> plantaResultados = query.getResultList();
		
		assertEquals(5, plantaResultados.size());
	}
	
	/**
	 * 6
	 * named query que dada la cedula de una persona se obtenga el listado de 
	 * registros (env�os) que ha realizado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json","planta.json"})
	public void testObtenerListadoPeticionPorIdPersona() {
		TypedQuery<Peticion> query = entityManager.createNamedQuery(Persona.OBTENER_LISTADO_PETICIONES, Peticion.class);
		
		query.setParameter("id", "1234");
		List<Peticion> listaResultados = query.getResultList();

		assertEquals(0, listaResultados.size()); 
	}
	
	/**
	 * 7
	 * named query que permita obtener un listado con la cedula de las 
	 * personas y cada uno de sus registros asociados
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json","planta.json"})
	public void testObtenerListPeticionPorIdPersonaLeftJoin() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Persona.OBTENER_PETICIONES_ASOCIADAS, Object[].class);
		
		List<Object[]> listaResultados = query.getResultList();
		
		System.out.println("PUNTO 7 GUIA 9 ENVIOS ------------");
		
		for (Object[] dupla : listaResultados) {
			String idTmp = String.valueOf(dupla[0]);
			Peticion pTmp = (Peticion) dupla[1];
			
			System.out.println(String.format(idTmp, pTmp));
		}
		
		System.out.println("PUNTO 7 GUIA 9 FIN ------------  \n");

		assertEquals(20, listaResultados.size());
	}
	/**
	 * 8
	 *  named query que permita obtener un listado de los recolectores
	 *  que han realizado registros (no se deben obtener clientes repetidos)
	 */
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "peticion.json","persona.json"})
	public void testObtenerListRecolectoresConPeticion() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Peticion.OBTENER_LISTADO_RECOLECTORES, Persona.class);
		
		List<Persona> listaResultados = query.getResultList();
		//REPARAR TODO DESPUES, REEPLANTAR
		assertEquals(17, listaResultados.size());
	}
	
	/**
	 * 9
	 * named query que dada una fecha de un registro se permita obtener un listado con el Id
	 * del registro, el nombre de los g�neros y plantas asociados, la c�dula y el email de la
	 *  persona que hizo la solicitud. De igual forma debe crear un m�todo Test que le permita
	 *  probar el correcto funcionamiento de la consulta. 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "peticion.json","planta.json","genero.json","persona.json"})
	public void testObtenerAllPeticionPorFecha() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Peticion.OBTENER_POR_FECHA, Object[].class);
		
		try {			
			String entrada = "2018-05-09 11:58:18";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fecha = format.parse(entrada);
			
			query.setParameter("fecha", fecha);
			
			List<Object[]> listaResultados = query.getResultList();
			assertEquals(1, listaResultados.size());
		} catch (ParseException e) {
			assertSame(e.getMessage(), null, e);
		}
	}
	
	/**
	 * 10
	 * 
	 */
}

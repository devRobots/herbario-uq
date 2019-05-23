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
	 * Modificacion de la persona
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void updatePersonaTest() {
		//Obtener Administrador a modificar
		Administrador a = entityManager.find(Administrador.class, "6");
		//Edicion de la clave del Administrador
		a.setClave("12345678");
		//Modificacion del Administrador
		entityManager.merge(a);
		//Obtener administrador ya modificado
		Administrador actualizado = entityManager.find(Administrador.class, "6");
		//Verificar de que el administrador cambio su clave
		
		assertEquals("12345678", actualizado.getClave());
	}
	/**
	 * Modificacion de la planta
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void updatePlantaTest() {
		//Obtener planta a modificar
		Planta p = entityManager.find(Planta.class, "1");
		//Edicion de la id de la Planta
		p.setEspecie("Jeiser");
		//Modificacion de la planta
		entityManager.merge(p);
		//Obtener planta ya modificado
		Planta actualizado = entityManager.find(Planta.class, "1");
		//Verificar de que la planta cambio su especie
		
		assertEquals("Jeiser", actualizado.getEspecie());
	}
	/**
	 *  Modificacion de la Familia
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void updateFamiliaTest() {
		//Obtener familia a modificar
		Familia f = entityManager.find(Familia.class, "14");
		//Edicion del nombre a modificar
		f.setNombre("holi");
		//Modificacion de la familia
		entityManager.merge(f);
		//Obtener familia ya modificado
		Familia actualizado = entityManager.find(Familia.class, "14");
		//Verificar de que la familia cambio su nombre
			
		assertEquals("holi", actualizado.getNombre());
	}
	/**
	 * Modificacion del genero
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void updateGeneroTest() {
		//Obtener Genero a modificar
		Genero g = entityManager.find(Genero.class, "25");
		//Edicion del nombre a modificar
		g.setNombre("hello");
		//Modificacion del Genero
		entityManager.merge(g);
		//Obtener Genero ya modificado
		Genero actualizado = entityManager.find(Genero.class, "25");
		//Verificar de que el Genero cambio su nombre
			
		assertEquals("hello", actualizado.getNombre());
	}
	/**
	 * Modificacion de la peticion
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"peticion.json"})
	public void updatePeticionTest() {
		//Obtener Peticion a modificar
		Peticion p = entityManager.find(Peticion.class, "31");
		//Edicion del nombre a modificar
		p.setRespuesta("la solicitud fue recibida");
		//Modificacion del Genero
		entityManager.merge(p);
		//Obtener Genero ya modificado
		Peticion actualizado = entityManager.find(Peticion.class, "31");
		//Verificar de que el Genero cambio su nombre
			
		assertEquals("la solicitud fue recibida", actualizado.getRespuesta());
	}

}

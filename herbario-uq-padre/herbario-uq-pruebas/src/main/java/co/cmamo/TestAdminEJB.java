package co.cmamo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.cmamo.ejbs.*;

@RunWith(Arquillian.class)
public class TestAdminEJB {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void crearEmpleadoTest() {
		Empleado e = new Empleado();
		e.setNombre("Cristian Camilo");
		e.setApellido("Quiceno Laurente");
		e.setClave("123");
		e.setCorreo("ccquicenol@uqvirtual.edu.co");
		e.setEstado(EstadoActividad.ACTIVO);
		e.setId("22");
		e.setPeticiones(new ArrayList<Peticion>());
		e.setSalario(123.0);
		
		assertTrue(adminEJB.crearEmpleado(e));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void modificarEmpleadoTest() {
		Empleado e = adminEJB.buscarEmpleado("8");
		
		e.setApellido("Nieto Mora");
		e.setNombre("Tatiana Jaima");
		e.setClave("12345");

		assertTrue(adminEJB.modificarEmpleado(e));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void buscarEmpleadoTest() {
		Empleado e = adminEJB.buscarEmpleado("8");

		assertEquals("Aidan", e.getNombre());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void listarEmpleadosTest() {
		List<Empleado> empleados = adminEJB.listarEmpleados();

		assertEquals(4, empleados.size());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void invalidarEmpleadoTest() {
		adminEJB.invalidarEmpleado("8");

		Empleado e = adminEJB.buscarEmpleado("8");
		
		assertEquals(EstadoActividad.INACTIVO, e.getEstado());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void crearRecolectorTest() {
		Recolector r = new Recolector();
		r.setApellido("Quiceno Laurente");
		r.setClave("123");
		r.setCorreo("ccquicenol@uqvirtual.edu.co");
		r.setEstado(EstadoActividad.ACTIVO);
		r.setId("22");
		r.setPeticiones(new ArrayList<Peticion>());
		
		assertTrue(adminEJB.crearRecolector(r));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void modificarRecolectorTest() {
		Recolector r = adminEJB.buscarRecolector("11");
		
		r.setApellido("Nieto Mora");
		r.setNombre("Tatiana Jaima");
		r.setClave("12345");

		assertTrue(adminEJB.modificarRecolector(r));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void buscarRecolectorTest() {
		Recolector r = adminEJB.buscarRecolector("11");

		assertEquals("Hakeem", r.getNombre());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void listarRecolectorTest() {
		List<Recolector> recolectores = adminEJB.listarRecolectores();

		assertEquals(11, recolectores.size());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void invalidarRecolectorTest() {
		adminEJB.invalidarRecolector("11");

		Recolector r = adminEJB.buscarRecolector("11");
		
		assertEquals(EstadoActividad.INACTIVO, r.getEstado());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void crearFamiliaTest() {
		Familia f = new Familia();
		f.setId("224");
		f.setNombre("Asereje");
		
		assertTrue(adminEJB.crearFamilia(f));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void modificarFamiliaTest() {
		Familia f = adminEJB.buscarFamilia("11");
		
		f.setNombre("Tatiana Jaima");

		assertTrue(adminEJB.modificarFamilia(f));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void buscarFamiliaTest() {
		Familia f = adminEJB.buscarFamilia("11");

		assertEquals("Byron", f.getNombre());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void listarFamiliaTest() {
		List<Familia> familias = adminEJB.listarFamilias();

		assertEquals(20, familias.size());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"familia.json"})
	public void eliminarFamiliaTest() {
		adminEJB.eliminarFamilia("11");

		Familia f = adminEJB.buscarFamilia("11");
		
		assertNull(f);
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void crearGeneroTest() {
		Genero g = new Genero();
		g.setId("224");
		g.setNombre("Asereje");
		
		assertTrue(adminEJB.crearGenero(g));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void modificarGeneroTest() {
		Genero g = adminEJB.buscarGenero("11");
		
		g.setNombre("Tatiana Jaima");

		assertTrue(adminEJB.modificarGenero(g));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void buscarGeneroTest() {
		Genero g = adminEJB.buscarGenero("11");

		assertEquals("Keane-Lael", g.getNombre());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void listarGeneroTest() {
		List<Genero> generos = adminEJB.listarGeneros();

		assertEquals(40, generos.size());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void eliminarGeneroTest() {
		adminEJB.eliminarGenero("11");

		Genero g = adminEJB.buscarGenero("11");
		
		assertNull(g);
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json", "genero.json"})
	public void crearPlantaTest() {
		Planta p = new Planta();
		p.setId("224");
		p.setEspecie("Asereje");
		p.setGenero(adminEJB.buscarGenero("12"));
		
		assertTrue(adminEJB.crearPlanta(p));
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void buscarPlantaTest() {
		Planta p = adminEJB.buscarPlanta("11");

		assertEquals("Renee Norman Winters", p.getEspecie());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"planta.json"})
	public void listarPlantaTest() {
		List<Planta> plantas = adminEJB.listarPlantas();

		assertEquals(80, plantas.size());
	}
}

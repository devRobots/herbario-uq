package co.cmamo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.util.Util;
import co.cmamo.Departamento;
import co.cmamo.EstadoPeticion;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Persona;
import co.cmamo.Peticion;
import co.cmamo.Planta;

/**
 * Permite realizar una todas las operaciones para gestionar una genero
 * 
 * @author EinerZG
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "departamentoBean")
@ApplicationScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Departamento departamento;
	/**
	 * lista de generos existentes
	 */
	private List<Planta> especies;
	/**
	 * Genero seleccionado en la lista
	 */
	private Planta especie;

	/**
	 * Conexión con la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la lista de familias
	 */
	@PostConstruct
	private void inicializar() {
		this.departamento = Departamento.AMAZONAS;
		especies = adminEJB.listarPlantas(departamento);
		
		Util.mostrarMensaje("Especies", especies.toString());
	} 

	/**
	 * @return the generos
	 */
	public List<Planta> getEspecies() {
		especies = adminEJB.listarPlantas(departamento);
		return especies;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setEspecies(List<Planta> especies) {
		this.especies = especies;
	}
	
	/**
	 * @return the especie
	 */
	public Planta getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Planta especie) {
		this.especie = especie;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setDepartamento(int i) {
		Random rand = new Random();
		int dept = rand.nextInt(40);
		
		this.departamento = Departamento.values()[dept];
	}
}

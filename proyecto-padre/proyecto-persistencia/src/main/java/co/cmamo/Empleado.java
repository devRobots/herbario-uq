package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity

public class Empleado extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private double salario;
	
	public Empleado() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}
   
}

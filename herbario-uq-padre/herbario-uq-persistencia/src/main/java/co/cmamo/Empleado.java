package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Empleado.LISTAR_EMPLEADOS, query = "select e from Empleado e"),
	@NamedQuery(name = Empleado.EMPLEADO_POR_EMAIL, query = "select e from Empleado e where e.correo = :correo")
})
public class Empleado extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String EMPLEADO_POR_EMAIL = "Empleado_EmpleadoPorEmail";
	public static final String LISTAR_EMPLEADOS = "Empleado_ListarTodos";
	
	/**
	 * Salario del Empleado
	 */
	private double salario;
	/**
	 * constructor del Empleado
	 */
	public Empleado() {
		super();
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
   
	@Override
	public Empleado clone() {
		Empleado e = (Empleado) super.clone();
		e.setSalario(salario);
		
		return e;
	}
}

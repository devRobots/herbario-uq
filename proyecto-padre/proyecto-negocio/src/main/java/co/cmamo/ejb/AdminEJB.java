package co.cmamo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.cmamo.Empleado;
import co.cmamo.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class AdminEJB
 */
/**
 * @author devRo
 *
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public AdminEJB() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see co.cmamo.ejb.AdminEJBRemote#insertarEmpleado(co.cmamo.Empleado)
     */
    public Empleado registrarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion {
    	if (entityManager.find(Empleado.class, empleado.getId()) != null) {
			throw new ElementoRepetidoExcepcion("El empleado con esta cedula ya esta registrado");
		} 
    	else if (buscarPorCorreo(empleado.getCorreo()) != null) {
			throw new ElementoRepetidoExcepcion("El empleado con este correo ya esta registrado");
		}
    	
    	try {
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * Permite buscar un empleado por correo
     * @param correo Correo del empleado
     * @return Empleado correspondiente al correo o Null
     */
    private Empleado buscarPorCorreo(String correo) {
    	try {
    		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.EMPLEADO_POR_EMAIL, Empleado.class);
        	query.setParameter("correo", correo);
        	return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
    }
}

package co.cmamo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.cmamo.Empleado;
import co.cmamo.EstadoActividad;
import co.cmamo.Recolector;
import co.cmamo.excepciones.ElementoInexistenteExcepcion;
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

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see co.cmamo.ejb.AdminEJBRemote#insertarEmpleado(co.cmamo.Empleado)
	 */
	public boolean crearEmpleado(Empleado empleado) {
		try {
			if (entityManager.find(Empleado.class, empleado.getId()) != null) {
				throw new ElementoRepetidoExcepcion("El empleado con esta cedula ya esta registrado");
			} else if (buscarPorCorreo(empleado.getCorreo()) != null) {
				throw new ElementoRepetidoExcepcion("El empleado con este correo ya esta registrado");
			}

			entityManager.persist(empleado);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean modificarEmpleado(Empleado anterior, Empleado nuevo) {
		try {
			if (entityManager.find(Empleado.class, anterior.getId()) == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se busca reemplazar no existe");
			}
			
			entityManager.merge(nuevo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Empleado buscarEmpleado(String cosa) {
		return null;
	}

	@Override
	public List<Empleado> listarEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see co.cmamo.ejb.AdminEJBRemote#eliminarEmpleado(co.cmamo.Empleado)
	 */
	public boolean invalidarEmpleado(Empleado empleado) {
		try {
			if (entityManager.find(Empleado.class, empleado.getId()) == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar no existe");
			}
			else if (empleado.getEstado() == EstadoActividad.INACTIVO) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar ya esta inactivo");
			}
			
			try {
				empleado.setEstado(EstadoActividad.INACTIVO);
				entityManager.merge(empleado);
				
				if (entityManager.find(Empleado.class, empleado.getId()).getEstado() == EstadoActividad.INACTIVO) {
					return true;
				}
				else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.cmamo.ejb.AdminEJBRemote#crearRecolector(co.cmamo.Recolector)
	 */
	public boolean crearRecolector(Recolector recolector) {
		try {
			if (entityManager.find(Recolector.class, recolector.getId()) != null) {
				throw new ElementoRepetidoExcepcion("El recolector con esta cedula ya esta registrado");
			} else if (buscarPorCorreo(recolector.getCorreo()) != null) {
				throw new ElementoRepetidoExcepcion("El recolector con este correo ya esta registrado");
			}

			try {
				entityManager.persist(recolector);
				return true;
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarRecolector(Recolector anterior, Recolector nuevo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recolector buscarRecolector(String cosa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recolector> listarRecolectores() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see co.cmamo.ejb.AdminEJBRemote#invalidarRecolector(co.cmamo.Recolector)
	 */
	public boolean invalidarRecolector(Recolector recolector) {
		try {
			if (entityManager.find(Recolector.class, recolector.getId()) == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar no existe");
			}
			else if (recolector.getEstado() == EstadoActividad.INACTIVO) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar ya esta inactivo");
			}
			
			try {
				recolector.setEstado(EstadoActividad.INACTIVO);
				entityManager.merge(recolector);
				
				if (entityManager.find(Empleado.class, recolector.getId()).getEstado() == EstadoActividad.INACTIVO) {
					return true;
				}
				else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Permite buscar un empleado por correo
	 * 
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

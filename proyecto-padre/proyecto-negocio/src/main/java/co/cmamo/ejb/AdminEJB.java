package co.cmamo.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.cmamo.Empleado;
import co.cmamo.EstadoActividad;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Planta;
import co.cmamo.Recolector;
import co.cmamo.excepciones.ElementoInexistenteExcepcion;
import co.cmamo.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class AdminEJB
 * @author Yesid Shair Rosas Toro
 * @author Cristian Camilo Quiceno Laurente
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
	 * 
	 * @see co.cmamo.ejb.AdminEJBRemote#crearEmpleado(co.cmamo.Empleado)
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

	/*
	 * @see co.cmamo.ejb.AdminEJBRemote#modificarEmpleado(co.cmamo.Empleado)
	 */
	public boolean modificarEmpleado(Empleado mod) {
		try {
			Empleado e = entityManager.find(Empleado.class, mod.getId());
			if (e == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se busca modificar no existe");
			}
			if (!e.getCorreo().equals(mod.getCorreo())) {
				if (buscarPorCorreo(mod.getCorreo()) != null) {
					throw new ElementoRepetidoExcepcion("El correo que se quiere actualizar no esta disponible");
				}
			}

			entityManager.merge(mod);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Empleado buscarEmpleado(String id) {
		try {
			Empleado empleado = entityManager.find(Empleado.class, id);

			if (empleado == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se busca no existe");
			}

			return empleado;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Empleado> listarEmpleados() {
		try {
			TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);

			List<Empleado> listado = query.getResultList();
			
			if (listado == null) {
				listado = new ArrayList<Empleado>();
			}

			return listado;
		} catch (Exception e) {
			return new ArrayList<Empleado>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.cmamo.ejb.AdminEJBRemote#eliminarEmpleado(co.cmamo.Empleado)
	 */
	public boolean invalidarEmpleado(String id) {
		try {
			Empleado empleado = entityManager.find(Empleado.class, id);

			if (empleado == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar no existe");
			} else if (empleado.getEstado() == EstadoActividad.INACTIVO) {
				throw new ElementoInexistenteExcepcion("El empleado que se quiere eliminar ya esta inactivo");
			}

			empleado.setEstado(EstadoActividad.INACTIVO);
			entityManager.merge(empleado);

			if (entityManager.find(Empleado.class, empleado.getId()).getEstado() == EstadoActividad.ACTIVO) {
				throw new Exception("No se pudo invalidar el empleado");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.cmamo.ejb.AdminEJBRemote#crearRecolector(co.cmamo.Recolector)
	 */
	public boolean crearRecolector(Recolector recolector) {
		try {
			if (entityManager.find(Recolector.class, recolector.getId()) != null) {
				throw new ElementoRepetidoExcepcion("El recolector con esta cedula ya esta registrado");
			} else if (buscarPorCorreo(recolector.getCorreo()) != null) {
				throw new ElementoRepetidoExcepcion("El recolector con este correo ya esta registrado");
			}

			entityManager.persist(recolector);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarRecolector(Recolector mod) {
		try {
			Recolector r = entityManager.find(Recolector.class, mod.getId());
			if (r == null) {
				throw new ElementoInexistenteExcepcion("El recolector que se busca modificar no existe");
			}
			if (!r.getCorreo().equals(mod.getCorreo())) {
				if (buscarPorCorreo(mod.getCorreo()) != null) {
					throw new ElementoRepetidoExcepcion("El correo que se quiere actualizar no esta disponible");
				}
			}

			entityManager.merge(mod);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Recolector buscarRecolector(String id) {
		try {
			Recolector recolector = entityManager.find(Recolector.class, id);

			if (recolector == null) {
				throw new ElementoInexistenteExcepcion("El empleado que se busca no existe");
			}

			return recolector;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Recolector> listarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTOR, Recolector.class);

		List<Recolector> listado = query.getResultList();

		return listado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.cmamo.ejb.AdminEJBRemote#invalidarRecolector(co.cmamo.Recolector)
	 */
	public boolean invalidarRecolector(String id) {
		try {
			Recolector recolector = entityManager.find(Recolector.class, id);

			if (recolector == null) {
				throw new ElementoInexistenteExcepcion("El recolector que se quiere eliminar no existe");
			} else if (recolector.getEstado() == EstadoActividad.INACTIVO) {
				throw new ElementoInexistenteExcepcion("El recolector que se quiere eliminar ya esta inactivo");
			}

			recolector.setEstado(EstadoActividad.INACTIVO);
			entityManager.merge(recolector);

			if (entityManager.find(Recolector.class, recolector.getId()).getEstado() == EstadoActividad.ACTIVO) {
				throw new Exception("No se pudo invalidar el recolector");
			}
			return true;
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

	@Override
	public boolean crearFamilia(Familia familia) {
		try {
			if (entityManager.find(Familia.class, familia.getId()) != null) {
				throw new ElementoRepetidoExcepcion("La familia con este id ya esta registrada");
			}

			entityManager.persist(familia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarFamilia(Familia mod) {
		try {
			Familia f = entityManager.find(Familia.class, mod.getId());
			if (f == null) {
				throw new ElementoInexistenteExcepcion("La familia que se busca modificar no existe");
			}

			entityManager.merge(mod);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Familia buscarFamilia(String id) {
		try {
			Familia familia = entityManager.find(Familia.class, id);

			if (familia == null) {
				throw new ElementoInexistenteExcepcion("La familia que se busca no existe");
			}

			return familia;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Familia> listarFamilias() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_TODOS, Familia.class);

		List<Familia> listado = query.getResultList();

		return listado;
	}

	@Override
	public boolean eliminarFamilia(String id) {
		try {
			Familia familia = entityManager.find(Familia.class, id);

			if (familia == null) {
				throw new ElementoInexistenteExcepcion("La familia que se quiere eliminar no existe");
			}

			entityManager.remove(familia);

			if (entityManager.find(Familia.class, familia.getId()) == null) {
				throw new Exception("No se pudo eliminar la familia");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean crearGenero(Genero genero) {
		try {
			if (entityManager.find(Genero.class, genero.getId()) != null) {
				throw new ElementoRepetidoExcepcion("El genero con este id ya esta registrado");
			}

			entityManager.persist(genero);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarGenero(Genero mod) {
		try {
			Genero g = entityManager.find(Genero.class, mod.getId());
			if (g == null) {
				throw new ElementoInexistenteExcepcion("El genero que se busca modificar no existe");
			}

			entityManager.merge(mod);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Genero buscarGenero(String id) {
		try {
			Genero genero = entityManager.find(Genero.class, id);

			if (genero == null) {
				throw new ElementoInexistenteExcepcion("El genero que se busca no existe");
			}

			return genero;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Genero> listarGeneros() {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_TODOS, Genero.class);

		List<Genero> listado = query.getResultList();

		return listado;
	}

	@Override
	public boolean eliminarGenero(String id) {
		try {
			Genero genero = entityManager.find(Genero.class, id);

			if (genero == null) {
				throw new ElementoInexistenteExcepcion("El genero que se quiere eliminar no existe");
			}

			entityManager.remove(genero);

			if (entityManager.find(Genero.class, genero.getId()) == null) {
				throw new Exception("No se pudo eliminar el genero");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean crearPlanta(Planta planta) {
		try {
			if (entityManager.find(Planta.class, planta.getId()) != null) {
				throw new ElementoRepetidoExcepcion("La planta con este id ya esta registrada");
			}

			entityManager.persist(planta);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Planta buscarPlanta(String id) {
		try {
			Planta planta = entityManager.find(Planta.class, id);

			if (planta == null) {
				throw new ElementoInexistenteExcepcion("La planta que se busca no existe");
			}

			return planta;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Planta> listarPlantas() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_TODOS, Planta.class);

		List<Planta> listado = query.getResultList();

		return listado;
	}
}

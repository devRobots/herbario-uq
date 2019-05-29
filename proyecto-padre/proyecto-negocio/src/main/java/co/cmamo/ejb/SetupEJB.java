package co.cmamo.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.cmamo.Administrador;

/**
 * Controla las configuraciones iniciales por defecto
 * de la capa de negocio
 * 
 * @version 1.0
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public SetupEJB() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    private void init() {
    	
    	TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.CONTAR_ADMINS, Long.class);
    	long contarAdmins = query.getSingleResult();
    	
    	if (contarAdmins == 0) {
			Administrador a = new Administrador();
			// TODO: Agregar campos
		}
    	
    }
}

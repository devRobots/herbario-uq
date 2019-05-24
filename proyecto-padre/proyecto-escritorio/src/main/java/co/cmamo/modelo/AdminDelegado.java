package co.cmamo.modelo;

import javax.naming.InitialContext;

import co.cmamo.ejb.AdminEJBRemote;

public class AdminDelegado {
	
	private AdminEJBRemote adminEJB;
	
	public static AdminDelegado adminDelegado = instancia();
	
	private AdminDelegado() {
		try {
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AdminDelegado instancia() {
		if (adminDelegado == null) {
			adminDelegado = new AdminDelegado();
		}
		return adminDelegado;
	}
	
}

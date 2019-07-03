package co.cmamo;

import java.io.IOException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Es un oyente practicamente esta atento de todas las SOLICITUDES de la pagina
 * @author joshu
 *
 */
@WebFilter("/seguro/*")
public class SeguridadFilter implements Filter {
	
	/**
	 * Permite cargar los bean que estan en memoria en ese momento
	 */
	@Inject
	private BeanManager beanManager;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.getSession(false);
		String loginURL = request.getContextPath() + "/index.xhtml";
		Bean<?> bean = beanManager.getBeans("seguridadBean").iterator().next();
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		SeguridadBean seguridadBean = (SeguridadBean) beanManager.getReference(bean,
		bean.getBeanClass(), ctx);
		boolean autenticado = seguridadBean != null && seguridadBean.isAutenticado();
		if (autenticado) {
		chain.doFilter(request, response);
		} else {
		response.sendRedirect(loginURL);
		}
		
	}


}

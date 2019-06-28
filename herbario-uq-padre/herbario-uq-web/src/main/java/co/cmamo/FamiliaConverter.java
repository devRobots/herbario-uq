package co.cmamo;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named("familiaConverter")
@ApplicationScoped
public class FamiliaConverter implements Converter<Familia> {

	@EJB
	private AdminEJB adminEJB;
	
	@Override
	public Familia getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			Long idFamilia = Long.parseLong(value);
			// TODO: cambiar parametro a long
			return adminEJB.buscarFamilia(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Familia value) {
		return (value != null) ? String.format("%s",  value.getId()) : "";
	}
	

	private List<?> getItemsObjects(UIComponent component) {
		List<?> objects = Collections.emptyList();
		for (UIComponent child : component.getChildren()) {
			if (child.getClass() == UISelectItems.class) {
				objects = (List<?>) ((UISelectItems) child).getValue();
			}
		}
		return objects;
	}

}

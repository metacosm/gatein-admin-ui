package org.gatein.admin.beans.footer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.StateAwareResponse;
import javax.xml.namespace.QName;

import org.gatein.admin.event.MenuSelectedEvent;

@ManagedBean(name = "menu")
@SessionScoped
public class MenuController {

	public void menuClick(String option) {
		Object response = FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if (response instanceof StateAwareResponse) {
			StateAwareResponse state = (StateAwareResponse) response;
			state.setEvent(new QName("urn:gatein:admin:ui:menu:event", "MenuItemSelected"), new MenuSelectedEvent(option));
		}
	}
}

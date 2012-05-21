package org.gatein.admin.event.option1;

import javax.faces.context.FacesContext;
import javax.portlet.Event;
import javax.portlet.StateAwareResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.portlet.faces.BridgeEventHandler;
import javax.portlet.faces.event.EventNavigationResult;

import org.gatein.admin.event.MenuSelectedEvent;

public class MenuSelectedEventHandler implements BridgeEventHandler {

	@Override
	public EventNavigationResult handleEvent(FacesContext context, Event event) {
		Object response = context.getExternalContext().getResponse();
		if (response instanceof StateAwareResponse) {
			StateAwareResponse state = (StateAwareResponse) response;
			MenuSelectedEvent val = (MenuSelectedEvent)event.getValue();
			if ("option1".equals(val.getPortlet())) {
				try {
					state.setWindowState(WindowState.NORMAL);
				} catch (WindowStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					state.setWindowState(WindowState.MINIMIZED);
				} catch (WindowStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}

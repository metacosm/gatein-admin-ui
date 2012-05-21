package org.gatein.admin.event;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuSelectedEvent implements Serializable {

	public MenuSelectedEvent(String portlet) {
		this.portlet = portlet;
	}

	private static final long serialVersionUID = 6811330034740817673L;

	private String portlet;

	public String getPortlet() {
		return portlet;
	}

	public void setPortlet(String portlet) {
		this.portlet = portlet;
	}

}

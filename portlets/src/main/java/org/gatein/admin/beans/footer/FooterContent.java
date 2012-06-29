package org.gatein.admin.beans.footer;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.resources.ResourceBundleService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

/** @author <a href="mailto:ken@kenfinnigan.me">Ken Finnigan</a> */
@ManagedBean
@ApplicationScoped
public class FooterContent implements Serializable
{

   private static final long serialVersionUID = 6546250147709352187L;

   private String footer;

   public FooterContent()
   {
      FacesContext faces = FacesContext.getCurrentInstance();
      PortalContainer portalContainer = PortalContainer.getInstance();
      ResourceBundleService service = (ResourceBundleService)portalContainer.getComponentInstanceOfType(ResourceBundleService.class);
      ResourceBundle res = service.getResourceBundle(service.getSharedResourceBundleNames(), faces.getExternalContext().getRequestLocale());
      footer = res.getString("UIPortalToolPanel.label.copyrightText") + res.getString("UIPortalToolPanel.label.companyTitleText");
   }

   public String getFooter()
   {
      return footer;
   }

   public void setFooter(String footer)
   {
      this.footer = footer;
   }
}

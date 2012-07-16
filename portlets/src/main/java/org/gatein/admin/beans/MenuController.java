package org.gatein.admin.beans;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.mop.SiteKey;
import org.exoplatform.portal.mop.SiteType;
import org.exoplatform.web.application.RequestContext;
import org.exoplatform.web.url.navigation.NavigationResource;
import org.exoplatform.web.url.navigation.NodeURL;
import org.gatein.admin.API;
import org.gatein.api.GateIn;
import org.gatein.api.commons.Filter;
import org.gatein.api.portal.Site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "menu")
@SessionScoped
public class MenuController
{
   private String selectedSite;

   public String getNodeURL(String name)
   {
      RequestContext context = RequestContext.getCurrentInstance();
      NodeURL url = context.createURL(NodeURL.TYPE);
      url.setResource(new NavigationResource(new SiteKey(SiteType.PORTAL, "admin"), name));
      return url.toString();
   }

   public String select(String siteId)
   {
      selectedSite = siteId;
      return "success";
   }

   public String getSelectedSite()
   {
      return selectedSite;
   }

   public void setSelectedSite(String selectedSite)
   {
      this.selectedSite = selectedSite;
   }
}

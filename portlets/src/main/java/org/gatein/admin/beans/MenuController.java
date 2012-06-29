package org.gatein.admin.beans;

import org.exoplatform.portal.mop.SiteKey;
import org.exoplatform.portal.mop.SiteType;
import org.exoplatform.web.application.RequestContext;
import org.exoplatform.web.url.navigation.NavigationResource;
import org.exoplatform.web.url.navigation.NodeURL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "menu")
@SessionScoped
public class MenuController
{

   public String getNodeURL(String name)
   {
      RequestContext context = RequestContext.getCurrentInstance();
      NodeURL url = context.createURL(NodeURL.TYPE);
      url.setResource(new NavigationResource(new SiteKey(SiteType.PORTAL, "admin"), name));
      return url.toString();
   }
}

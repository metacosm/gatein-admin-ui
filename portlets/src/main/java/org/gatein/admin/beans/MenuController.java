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

   public Iterable<Site> getSites()
   {
      final List<Site> sites = API.getGateIn().getSites(new Filter<Site>()
      {
         @Override
         public boolean accept(Site site)
         {
            final Site.Type type = site.getType();
            return Site.Type.SITE == type || Site.Type.SPACE == type;
         }
      });
      Collections.sort(sites, new Comparator<Site>()
      {
         /**
          * Order sites by type first (so that spaces show up after "regular" sites) and then by name.
          * @param site1
          * @param site2
          * @return
          */
         @Override
         public int compare(Site site1, Site site2)
         {
            final Site.Type type1 = site1.getType();
            final Site.Type type2 = site2.getType();
            if (type1 != type2)
            {
               return type1.compareTo(type2);
            }
            else
            {
               return site1.getName().compareTo(site2.getName());
            }
         }
      });
      return sites;
   }

   public Iterable<Site> getOnlySites()
   {
      return API.getGateIn().getSites(Site.Type.SITE);
   }

   public Iterable<Site> getOnlySpaces()
   {
      return API.getGateIn().getSites(Site.Type.SPACE);
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

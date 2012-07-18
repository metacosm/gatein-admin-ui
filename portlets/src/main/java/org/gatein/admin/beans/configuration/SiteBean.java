/*
* JBoss, a division of Red Hat
* Copyright 2008, Red Hat Middleware, LLC, and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.gatein.admin.beans.configuration;

import org.gatein.admin.API;
import org.gatein.api.Properties;
import org.gatein.api.portal.Site;
import org.gatein.api.security.SecurityRestriction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/** @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a> */
@ManagedBean(name = "site")
@RequestScoped
public class SiteBean
{
   @ManagedProperty(value = "#{menu.selectedSite}")
   private String selectedSiteId;
   private Site site;
   private String selectedLocale;
   private String selectedSkin;

   @PostConstruct
   public void init()
   {
      if (selectedSiteId == null)
      {
         site = API.getGateIn().getDefaultSite();
      }
      else
      {
         site = API.getGateIn().getSite(Site.Id.fromBase64String(selectedSiteId));
      }

   }

   /**
    * Needed for the managed property.
    *
    * @return
    */
   public String getSelectedSiteId()
   {
      return selectedSiteId;
   }

   /**
    * Needed for the managed property.
    *
    * @return
    */
   public void setSelectedSiteId(String selectedSiteId)
   {
      this.selectedSiteId = selectedSiteId;
   }

   public void setSelectedLocale(String selectedLocale)
   {
      site.setLocale(new Locale(selectedLocale));
   }

   public String getSelectedLocale()
   {
      return site.getLocale().toString();
   }

   public void setSelectedSkin(String selectedSkin)
   {
      site.setSkin(selectedSkin);
   }

   public String getSelectedSkin()
   {
      return site.getSkin();
   }

   public String getName()
   {
      return site.getName();
   }

   public List<SecurityRestriction.Entry> getAccessPermissions()
   {
      /*final SecurityRestriction securityRestriction;
      try
      {
         securityRestriction = site.getSecurityRestriction(SecurityRestriction.Type.ACCESS);
      }
      catch (Exception e)
      {
         return Collections.emptyList();
      }
      if (securityRestriction != null)
      {
         return securityRestriction.getEntries();
      }
      else
      {
         return Collections.emptyList();
      }*/
      final SecurityRestriction.Entry[] members = {SecurityRestriction.Entry.create("member", "/customers")};
      return Arrays.asList(members);
   }

   public List<SecurityRestriction.Entry> getEditPermissions()
   {
      /*final SecurityRestriction securityRestriction;
      try
      {
         securityRestriction = site.getSecurityRestriction(SecurityRestriction.Type.ACCESS);
      }
      catch (Exception e)
      {
         return Collections.emptyList();
      }
      if (securityRestriction != null)
      {
         return securityRestriction.getEntries();
      }
      else
      {
         return Collections.emptyList();
      }*/
      final SecurityRestriction.Entry[] members = {SecurityRestriction.Entry.create("member", "/customers")};
      return Arrays.asList(members);
   }

   public void setSession(String session)
   {
      site.setProperty(Properties.SESSION_BEHAVIOR, session);
   }

   public String getSession()
   {
      return site.getProperty(Properties.SESSION_BEHAVIOR);
   }

   public void setShowInfoBar(boolean showInfoBar)
   {
      site.setProperty(Properties.SHOW_PORTLET_INFO_BAR, showInfoBar);
   }

   public boolean getShowInfoBar()
   {
      return site.getProperty(Properties.SHOW_PORTLET_INFO_BAR);
   }
}

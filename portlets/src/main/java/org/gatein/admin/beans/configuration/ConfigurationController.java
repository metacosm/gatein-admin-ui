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
import org.gatein.api.portal.Site;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/** @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a> */
@ManagedBean(name = "configuration")
@RequestScoped
public class ConfigurationController
{
   @ManagedProperty(value = "#{menu.selectedSite}")
   private String selectedSiteId;
   private Site site;
   private String selectedLocale;

   @PostConstruct
   public void init()
   {
      site = API.getGateIn().getSite(Site.Id.fromString(selectedSiteId));
   }

   public String getSelectedSiteId()
   {
      return selectedSiteId;
   }

   public void setSelectedSiteId(String selectedSiteId)
   {
      this.selectedSiteId = selectedSiteId;
   }

   public Site getSelectedSite()
   {
      return site;
   }

   public void setSelectedLocale(String selectedLocale)
   {
      this.selectedLocale = selectedLocale;
   }

   public String getSelectedLocale()
   {
      return selectedLocale;
   }
}

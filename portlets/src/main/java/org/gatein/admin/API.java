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

package org.gatein.admin;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.resource.SkinService;
import org.exoplatform.services.resources.LocaleConfig;
import org.exoplatform.services.resources.LocaleConfigService;
import org.gatein.api.GateIn;
import org.gatein.api.Properties;
import org.gatein.api.portal.Site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/** @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a> */
@ManagedBean(name = "api")
@SessionScoped
public class API
{
   private static GateIn GATE_IN;

   public static GateIn getGateIn()
   {
      if (GATE_IN == null)
      {
         GATE_IN = getService(GateIn.class);
      }
      return GATE_IN;
   }

   private static <T> T getService(Class<T> serviceType)
   {
      final Object service = ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(serviceType);
      return serviceType.cast(service);
   }

   public Iterable<Site> getOnlySites()
   {
      return getGateIn().getSites(Site.Type.SITE);
   }

   public Iterable<Site> getOnlySpaces()
   {
      return getGateIn().getSites(Site.Type.SPACE);
   }

   public Iterable<Locale> getSupportedLocales()
   {
      final LocaleConfigService localeService = (LocaleConfigService)ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(LocaleConfigService.class);
      final Iterator<LocaleConfig> configIterator = localeService.getLocalConfigs().iterator();
      return new Iterable<Locale>()
      {
         @Override
         public Iterator<Locale> iterator()
         {
            return new Iterator<Locale>()
            {
               @Override
               public boolean hasNext()
               {
                  return configIterator.hasNext();
               }

               @Override
               public Locale next()
               {
                  return configIterator.next().getLocale();
               }

               @Override
               public void remove()
               {
                  throw new UnsupportedOperationException();
               }
            };
         }
      };
   }

   public Iterable<String> getAvailableSkins()
   {
      final SkinService skinService = getService(SkinService.class);
      return new Iterable<String>()
      {
         @Override
         public Iterator<String> iterator()
         {
            return skinService.getAvailableSkinNames().iterator();
         }
      };
   }

   public List<String> getSupportedSessionStates()
   {
      final Properties knownProperties = getGateIn().getKnownProperties();
      return knownProperties.getSessionBehaviorValues();
   }
}

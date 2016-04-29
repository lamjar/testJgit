package com.linedata.ekip.std.services.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations =
{ "classpath:spring/import.spring.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringConfigTest extends TestCase
{
   private final Log  log = LogFactory.getLog(SpringConfigTest.class);

   @Autowired
   ApplicationContext applicationContext;

   @BeforeClass
   public static void initContext()
   {
   }

   @Test
   public void loadSpringConfig()
   {
      String[] beanList = applicationContext.getBeanDefinitionNames();
      for (String string : beanList)
      {
         try
         {
            applicationContext.getBean(string);
            log.info(string + ": created");

         }
         catch (BeanIsAbstractException e)
         {
            // nothing to do
         }
         catch (BeanCreationException e)
         {
            log.error(e.getMessage());
            Assert.fail();
         }
      }
      log.info("Bean Count : " + beanList.length);
   }

}

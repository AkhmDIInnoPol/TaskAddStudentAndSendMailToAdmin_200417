package main.controllers.listeners;

import main.services.SendMailService;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Di on 20.04.2017.
 */
public class AppStartListener implements ServletContextListener
{

    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        PropertyConfigurator.configure("log4j.properties");

        sendMail(servletContextEvent);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }



    private void sendMail(ServletContextEvent sce)
    {
        String myParam = sce.getServletContext().getInitParameter("ADMIN_MAIL");
        SendMailService.sendMailTLL("Application started", myParam);
    }
}

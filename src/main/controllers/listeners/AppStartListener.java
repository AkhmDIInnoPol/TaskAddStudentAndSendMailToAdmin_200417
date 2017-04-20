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

    static {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader()
        .getResource("log4j.properties"));
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        sendMail(servletContextEvent);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }



    private void sendMail(ServletContextEvent sce)
    {
//        String myParam = sce.getServletContext().getInitParameter("ADMIN_MAIL");
//        SendMailService.sendMailTLL("Application started", myParam);
    }
}

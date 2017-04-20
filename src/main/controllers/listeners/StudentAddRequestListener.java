package main.controllers.listeners;

import main.services.SendMailService;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Di on 20.04.2017.
 */
public class StudentAddRequestListener implements ServletRequestListener
{


    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String method = request.getMethod();
        String path = request.getServletPath();
        if (method.equals("POST"));
        {
            if (path.equals("/addEditStudent"))
            {
                sendMail(servletRequestEvent);
            }
        }
    }




    private void sendMail(ServletRequestEvent servletRequestEvent)
    {
        String myParam = servletRequestEvent.getServletContext().getInitParameter("ADMIN_MAIL");
        SendMailService.sendMailTLL("Student has added", myParam);
    }
}

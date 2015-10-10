package meetme.web.email;

import meetme.email.EmailInviteReader;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by bryukhaa on 10/7/15.
 */
public class EmailContextListener implements ServletContextListener {


    private EmailInviteReader emailInviteReader;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        if (context == null) {
            return;
        }
        final String userName = context.getInitParameter("inviteAccount");
        final String password = context.getInitParameter("invitePassword");

        if ((emailInviteReader == null) || (!emailInviteReader.isAlive())) {
            emailInviteReader = new EmailInviteReader(userName, password);
            emailInviteReader.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
//            emailInviteReader.doShutdown();
            emailInviteReader.interrupt();
        } catch (Exception ex) {
        }
    }
}

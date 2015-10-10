package meetme.email;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by bryukhaa on 10/7/15.
 */
public class EmailContextListener implements ServletContextListener {


    private EmailInviteReader emailInviteReader;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if ((emailInviteReader == null) || (!emailInviteReader.isAlive())) {
            emailInviteReader = new EmailInviteReader();
            //emailManager.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            //emailManager.doShutdown();
            //emailManager.interrupt();
        } catch (Exception ex) {
        }
    }
}

package meetme.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bryukhaa on 9/29/15.
 */
public class OrganizerHomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in
        String guid = request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/') + 1);
        System.out.println("GUID: [" + guid + "]");
        if (guid.length() != 36) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getRequestURI());
            dispatcher.forward(request,response);
        } //not a real GUID

        // Check if organizer is "logged in"
        HttpSession session = request.getSession();
        Long organizerId = (Long) session.getAttribute("organizerId");
        if (organizerId == null) {
            // Redirect to login
            System.out.println("Redirecting to login!");
            String nextJSP = "/login.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
        } else {
            // Show "Home"
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            dispatcher.forward(request,response);
        }
    }
}

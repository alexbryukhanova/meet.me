package meetme.web;

import meetme.dao.PersonDao;
import meetme.meeting.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bryukhaa on 9/29/15.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String organizerEmail = request.getParameter("email");

        PersonDao dao = new PersonDao();
        Person organizer = dao.getParticipantByEmail(organizerEmail);
        System.out.println("Person [" + organizerEmail + "] is: " + (organizer == null ? "not found" : organizer.getOrganizerGuid()));
        if (organizer != null) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("organizerId", organizer.getParticipantId());
            response.sendRedirect("/at/" + organizer.getOrganizerGuid());
        } else {
            // Invalid email, return error
            request.setAttribute("success", "false");
            request.setAttribute("message", "Couldn't find your email [" + organizerEmail + "] in our records, mate! Try again maybe?");
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getRequestURI());
//            dispatcher.redi(request, response);
        }

    }
}

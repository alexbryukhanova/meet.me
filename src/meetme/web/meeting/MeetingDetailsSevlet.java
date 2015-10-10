package meetme.web.meeting;

import meetme.dao.MeetingDao;
import meetme.meeting.Meeting;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bryukhaa on 10/8/15.
 */
public class MeetingDetailsSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("meetingId");
        Object oId = request.getSession(false).getAttribute("organizerId");

        if (id != null && oId != null) {
            long meetingId = Long.parseLong(id);
            MeetingDao dao = new MeetingDao();
            Meeting meeting = dao.getMeetingById((long) oId, meetingId);

            if (meeting != null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                JSONObject.writeJSONString(meeting.toJsonMap(), out);
                out.flush();
            }
        }

        // Invalid meeting id
        return;
    }
}

package meetme.web.meeting;

import meetme.dao.MeetingDao;
import meetme.meeting.Meeting;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by bryukhaa on 10/5/15.
 */
public class MeetingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object organizerId = request.getSession(false).getAttribute("organizerId");
        if (organizerId == null) {
            super.doGet(request, response);
        }

        MeetingDao meetingDao = new MeetingDao();
        List<Meeting> meetings = meetingDao.getMeetings((long) organizerId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONArray meetingsJson = new JSONArray();
        for (int i = 0; i < meetings.size(); i++) {
            meetingsJson.add(meetings.get(i).toJsonMap());
        }
        meetingsJson.writeJSONString(out);
        out.flush();

        System.out.println(meetingsJson.toJSONString());
    }
}

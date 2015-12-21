package meetme.web.reports;

import meetme.dao.MeetingDao;
import meetme.dao.ReportsDao;
import meetme.meeting.Meeting;
import meetme.reports.CostReport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by bryukhaa on 10/12/15.
 */
public class ReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object organizerId = request.getSession(false).getAttribute("organizerId");
        if (organizerId == null) {
            super.doGet(request, response);
        }

        long oid = (long) organizerId;
        ReportsDao dao = new ReportsDao();

        // Calculate meeting costs
        List<CostReport> meetingCosts = dao.getMeetingCostReport(oid);
        long totalCost = 0;
        for (int i = 0; i < meetingCosts.size(); i++) {
            totalCost += meetingCosts.get(i).calculateMeetingCost(50);
        }

        // Empty agenda meetings
        long agendaLessCount = dao.getNoAgendaMeetings(oid);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject reportsJson = new JSONObject();
        reportsJson.put("totalCost", totalCost);
        reportsJson.put("agendaless", agendaLessCount);

        reportsJson.writeJSONString(out);
        out.flush();

    }
}

package meetme.dao;

import meetme.reports.CostReport;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by bryukhaa on 10/12/15.
 */
public class ReportsDao extends Dao {

    public List<CostReport> getMeetingCostReport(long organizerId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            String hql = "SELECT new meetme.reports.CostReport(meeting.meetingId, meeting.startDateTime, meeting.endDateTime, COUNT(offspr))" +
                    " FROM Meeting meeting left outer join meeting.attendees as offspr" +
                    " WHERE meeting.organizer.participantId = :oid" +
                    " group by meeting.meetingId";

            List<CostReport> meetingStats = session.createQuery(hql)
                                                     .setLong("oid", organizerId)
                                                     .list();
            return meetingStats;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public long getNoAgendaMeetings(long organizerId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            String hql = "SELECT COUNT(meeting)" +
                    " FROM Meeting meeting" +        //will be a RIGHT OUTER JOIN with Agenda table
                    " WHERE meeting.organizer.participantId = :oid";

            long meetingStats = (long) session.createQuery(hql)
                                              .setLong("oid", organizerId)
                                              .uniqueResult();
            return meetingStats;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

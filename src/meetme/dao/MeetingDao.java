package meetme.dao;

import meetme.meeting.Meeting;
import meetme.meeting.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by bryukhaa on 9/7/15.
 */
public class MeetingDao extends Dao {
    private SessionFactory factory;

    public void persistMeeting(Meeting meeting) {
        Session session = null;
        Transaction tx = null;
        try {
            PersonDao personDao = new PersonDao();
            // Update or save the attendees
            for (int i = 0; i < meeting.getAttendees().size(); i++) {
                Person attendee = meeting.getAttendees().get(i);

                Person existing;
                if (attendee.getEmail().equals(meeting.getOrganizer().getEmail())) {
                    // Found the organizer, remove dupe
                    existing = meeting.getOrganizer();
                } else {
                    existing = personDao.getParticipantByEmail(attendee.getEmail());
                }

                // Replace with an attendee from the database when already exists
                if (existing != null) {
                    meeting.getAttendees().remove(i);
                    meeting.getAttendees().add(i, existing);
                }
            }

            // Save new meeting
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(meeting);
            tx.commit();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public List<Meeting> getMeetings(long organizerId) {
        Session session = null;
        List<Meeting> meetings = null;
        try {
            session = sessionFactory.openSession();
            String hql = "select meeting from Meeting meeting " +
                    //"inner join meeting.organizer" +
                    "where meeting.organizer.participantId = :oid";
            meetings = session.createQuery(hql)
                    .setLong("oid", organizerId)
                    .list();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return meetings;
    }

    public Meeting getMeetingById(long organizerId, long meetingId) {
        Session session = null;
        Meeting meeting = null;
        try {
            session = sessionFactory.openSession();
            String hql = "select meeting from Meeting meeting " +
                    "where meeting.organizer.participantId = :oid " +
                    "and meeting.meetingId = :mid";
            meeting = (Meeting) session.createQuery(hql)
                    .setLong("oid", organizerId)
                    .setLong("mid", meetingId)
                    .uniqueResult();
            return meeting;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}

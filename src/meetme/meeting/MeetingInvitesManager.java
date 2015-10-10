package meetme.meeting;

import meetme.dao.MeetingDao;
import meetme.dao.PersonDao;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import java.io.IOException;
import java.io.StringReader;

/**
 * Manages creation of invites and changes to existing invites.
 */
public class MeetingInvitesManager {

    private MeetingDao dao;

    public MeetingInvitesManager(MeetingDao meetingDao) {
        this.dao = meetingDao;
    }

    public Meeting createInvite(String inviteString) {
        Meeting meeting = null;

        CalendarBuilder builder = new CalendarBuilder();
        try {
            // Parse meeting invite
            Calendar calendar = builder.build(new StringReader(inviteString));
            VEvent event = (VEvent)calendar.getComponent(Component.VEVENT);
            meeting = new Meeting(event);

            // Update or save organizer
            PersonDao personDao = new PersonDao();
            Person existing = personDao.getParticipantByEmail(meeting.getOrganizer().getEmail());
            if (existing == null) {
                existing = personDao.persistOrganizer(meeting.getOrganizer());
            }
            meeting.setOrganizer(existing);

            dao.persistMeeting(meeting); //save!
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }


        return meeting;
    }
}

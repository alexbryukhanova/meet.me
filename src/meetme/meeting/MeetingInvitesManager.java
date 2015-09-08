package meetme.meeting;

import meetme.dao.MeetingDao;
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

    public IMeeting createInvite(String inviteString) {
        IMeeting meeting = null;

        CalendarBuilder builder = new CalendarBuilder();
        try {
            // Parse meeting invite
            Calendar calendar = builder.build(new StringReader(inviteString));
            VEvent event = (VEvent)calendar.getComponent(Component.VEVENT);

            meeting = new Meeting(event);

            // Persist invite
            dao.persistMeeting(meeting);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }


        return meeting;
    }
}

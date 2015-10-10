package meetme.dao;

import meetme.meeting.Meeting;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bryukhaa on 10/5/15.
 */
public class MeetingDaoTest {

    @Test
    public void save_Meeting_invite() throws Exception {
        // Given
        String inviteString = "BEGIN:VCALENDAR\n" +
                "PRODID:-//Google Inc//Google Calendar 70.9054//EN\n" +
                "VERSION:2.0\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "DTSTART:20150808T023000Z\n" +
                "DTEND:20150808T033000Z\n" +
                "DTSTAMP:20150808T014624Z\n" +
                "ORGANIZER;CN=\"ASDFSKY asdf\":mailto:asdf@asdf.com\n" +
                "UID:f8mjl1t766m0s5cvdgo45egie0@google.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE;CN=meetclever@yahoo.com;X-NUM-GUESTS=0:mailto:meetclever@yahoo.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE;CN=meetclever@yahoo.com;X-NUM-GUESTS=0:mailto:meetclever@yahoo.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED;RSVP=TRUE;CN=DELORD Nicolas;X-NUM-GUESTS=0:mailto:nicolas.delord@gmail.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED;RSVP=TRUE;CN=ASDFSKY asdf;X-NUM-GUESTS=0:mailto:asdf@asdf.com\n" +
                "CREATED:20150808T014624Z\n" +
                "DESCRIPTION:Affichez votre événement sur la page https://www.google.com/calendar/event?action=VIEW&eid=ZjhtamwxdDc2Nm0wczVjdmRnbzQ1ZWdpZTAgbWVldGNsZXZlckB5YWhvby5jb20&tok=MjQjbmljb2xhcy5kZWxvcmRAZ21haWwuY29tMmM0MWY5OTI0YjNlMWM0NmE5NDQ5ZjY3ZTQ1OGUxNjQ5NjYzOTQ2Mg&ctz=America/New_York&hl=fr.\n" +
                "LAST-MODIFIED:20150808T014624Z\n" +
                "LOCATION:\n" +
                "SEQUENCE:0\n" +
                "STATUS:CONFIRMED\n" +
                "SUMMARY:Sans Titre\n" +
                "TRANSP:OPAQUE\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(new StringReader(inviteString));
        VEvent event = (VEvent)calendar.getComponent(Component.VEVENT);

        // When
        MeetingDao dao = new MeetingDao();
        Meeting meeting = new Meeting(event);
        dao.persistMeeting(meeting);

        // Then
        assertTrue("New meeting must be saved.", meeting.getMeetingId() > 0);
    }

    @Test
    public void no_meetings_for_organizer() throws Exception {
        // Given
        long organizerId = -123;

        // When
        MeetingDao meetingDao = new MeetingDao();
        List<Meeting> meetings = meetingDao.getMeetings(organizerId);

        // Then
        assertEquals("There are no meetings for this organizer.", 0, meetings.size());
    }

    @Test
    public void load_meeting_by_id() {
        // Given
        long meetingId = 1;

        // When
        MeetingDao dao = new MeetingDao();
        Meeting meeting = dao.getMeetingById(9, 1);

        // Then
        assertNotNull("Meening must exist.", meeting);
        assertEquals("Meeting has 3 people attending.", 3, meeting.getAttendees().size());
    }
}
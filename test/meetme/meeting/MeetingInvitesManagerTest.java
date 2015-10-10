package meetme.meeting;

import meetme.dao.MeetingDao;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by bryukhaa on 8/19/15.
 */
public class MeetingInvitesManagerTest {

    @Test
    public void create_invite_based_on_email_invite_attachment() {
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
                "ORGANIZER;CN=\"DELORD Nicolas\":mailto:nicolas.delord@gmail.com\n" +
                "UID:f8mjl1t766m0s5cvdgo45egie0@google.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE;CN=meetclever@yahoo.com;X-NUM-GUESTS=0:mailto:meetclever@yahoo.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED;RSVP=TRUE;CN=DELORD Nicolas;X-NUM-GUESTS=0:mailto:nicolas.delord@gmail.com\n" +
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

        // When
        MeetingInvitesManager mngr = new MeetingInvitesManager(new MeetingDao());
        Meeting meeting = mngr.createInvite(inviteString);

        // Then
        //assertEquals("Google", meeting.getProdId());
//        assertEquals("REQUEST", meeting.getMethod());
        assertEquals("DELORD Nicolas", meeting.getOrganizer().getName());
        assertEquals("nicolas.delord@gmail.com", meeting.getOrganizer().getEmail());

//        GregorianCalendar start = new GregorianCalendar(2015, 7, 8, 2, 30);
//        start.setTimeZone(TimeZone.getTimeZone("UTC"));
//        assertTrue(start.getTime().equals(meeting.getStartDateTime()));
//
//        GregorianCalendar end = new GregorianCalendar(2015, 7, 8, 3, 30);
//        end.setTimeZone(TimeZone.getTimeZone("UTC"));
//        assertTrue(end.getTime().equals(meeting.getEndDateTime()));
//
//        assertEquals(2, meeting.getAttendees().size());
//        assertEquals("meetclever@yahoo.com", meeting.getAttendees().get(0).getName());
//        assertEquals("meetclever@yahoo.com", meeting.getAttendees().get(0).getEmail());
//        assertEquals("DELORD Nicolas", meeting.getAttendees().get(1).getName());
//        assertEquals("nicolas.delord@gmail.com", meeting.getAttendees().get(1).getEmail());
    }
}
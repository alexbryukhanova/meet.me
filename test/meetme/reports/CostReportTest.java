//package meetme.reports;
//
//import meetme.meeting.IMeeting;
//import meetme.meeting.Meeting;
//import net.fortuna.ical4j.model.DateTime;
//import net.fortuna.ical4j.model.component.VEvent;
//import net.fortuna.ical4j.model.parameter.Cn;
//import net.fortuna.ical4j.model.property.Attendee;
//import net.fortuna.ical4j.model.property.Organizer;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.util.GregorianCalendar;
//
//import static org.junit.Assert.*;
//
///**
// * Created by bryukhaa on 9/7/15.
// */
//public class CostReportTest {
//
//    @Test
//    public void calculate_meeting_cost_of_two_people_for_one_hour() throws URISyntaxException {
//        // Given
//        VEvent event = new VEvent(
//                new DateTime(new GregorianCalendar(2015, 1, 1, 1, 0).getTime()),
//                new DateTime(new GregorianCalendar(2015, 1, 1, 2, 0).getTime()),
//                "Test IMeeting");
//
//        // Person 1
//        String email = "alex.bryukhanova@adp.com";
//        Cn cn = new Cn("BRYUKHANOVA, Alex");
//        Organizer organizer = new Organizer(email);
//        organizer.getParameters().add(cn);
//        event.getProperties().add(organizer);
//
//        Attendee person = new Attendee(email);
//        person.getParameters().add(cn);
//        event.getProperties().add(person);
//
//        // Person 2
//        person = new Attendee("person@two.com");
//        person.getParameters().add(new Cn("TWO, Person"));
//        event.getProperties().add(person);
//
//        IMeeting meeting = new Meeting(event);
//
//        // When
//        // 100,000 / 49 / 40 = 51
//        CostReport report = new CostReport(meeting);
//        double cost = report.calculateMeetingCost(51);
//
//        // Then
//        assertEquals(102, cost, 0);
//    }
//}
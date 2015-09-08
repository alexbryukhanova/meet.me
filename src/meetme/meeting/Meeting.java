package meetme.meeting;

import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attendee;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bryukhaa on 8/16/15.
 */
public class Meeting implements IMeeting {
    private String prodId;
    private GregorianCalendar startDatetime;
    private GregorianCalendar endDateTime;
    private Person organizer;
    private List<Person> attendees;

    private static final Pattern email = Pattern.compile("mailto:([0-9A-z\\.\\+]+@[0-9A-z]+.[A-z]+)");

    public Meeting(VEvent event) {
        parseOrganizer(event);
        parseStartDatetime(event);
        parseEndDateTime(event);
        parseAttendees(event);
    }

    private void parseAttendees(VEvent event) {
        this.attendees = new ArrayList<Person>();

        PropertyList attendees = event.getProperties("ATTENDEE");
        for (Iterator<Attendee> iter = attendees.iterator(); iter.hasNext();) {
            Attendee person = iter.next();
            String attendeeEmail = getEmail(person.getValue());
            this.attendees.add(new Person(person.getParameter("CN").getValue(), attendeeEmail));
        }
    }

    private void parseOrganizer(VEvent event) {
        String emailAddress = getEmail(event.getOrganizer().getValue());
        this.organizer = new Person(event.getOrganizer().getParameter("CN").getValue(), emailAddress);
    }

    private String getEmail(String rawCN) {
        Matcher match = email.matcher(rawCN);
        String emailAddress = "";
        if(match.matches()) {
            emailAddress = match.group(1);
        }
        return emailAddress;
    }

    public void setProdId(String prodIdLine) {
        if(prodIdLine.contains("Google")) {
            this.prodId = "Google";
        }
    }

    public String getProdId() {
        return prodId;
    }

    @Override
    public String getMethod() {
        return "REQUEST";
    }

    @Override
    public Date getStartDateTime() {
        return startDatetime.getTime();
    }

    @Override
    public Date getEndDateTime() {
        return endDateTime.getTime();
    }

    @Override
    public Person getOrganizer() {
        return organizer;
    }

    @Override
    public List<Person> getAttendees() {
        return this.attendees;
    }

    private void parseStartDatetime(VEvent event) throws IllegalArgumentException {
        this.startDatetime = new GregorianCalendar();
        this.startDatetime.setTime(event.getStartDate().getDate());
    }

    private void parseEndDateTime(VEvent event) {
        this.endDateTime = new GregorianCalendar();
        this.endDateTime.setTime(event.getEndDate().getDate());
    }
}

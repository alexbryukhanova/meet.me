package meetme.meeting;

import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attendee;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONArray;

import javax.persistence.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bryukhaa on 8/16/15.
 */
@Entity
@Table( name = "Meetings" )
public class Meeting {
    private static final Pattern email = Pattern.compile("mailto:([0-9A-z\\.\\+]+@[0-9A-z]+.[A-z]+)");

    @Transient
    private String prodId;
    @Column(name = "startDate")
    private GregorianCalendar startDateTime;
    @Column(name = "endDate")
    private GregorianCalendar endDateTime;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizerId", nullable = false)
    private Person organizer;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "MeetingsAttendees",
    joinColumns = @JoinColumn(name = "meetingId"),
    inverseJoinColumns = @JoinColumn(name = "participantId"))
    private List<Person> attendees;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long meetingId;
    @Column
    private String meetingName;

    public Meeting() { }

    public Meeting(VEvent event) {
        parseMeetingName(event);
        parseOrganizer(event);
        parseStartDatetime(event);
        parseEndDateTime(event);
        parseAttendees(event);
    }

    private void parseMeetingName(VEvent event) {
        this.meetingName = event.getSummary().getValue();
    }

    private void parseAttendees(VEvent event) {
        Map<String, Person> peopleMap = new HashMap<>();

        PropertyList attendees = event.getProperties("ATTENDEE");
        for (Iterator<Attendee> iter = attendees.iterator(); iter.hasNext();) {
            Attendee person = iter.next();
            String attendeeEmail = getEmail(person.getValue());

            // Don't add person with same email twice
            if(!peopleMap.containsKey(attendeeEmail)) {
                peopleMap.put(attendeeEmail, new Person(person.getParameter("CN").getValue(), attendeeEmail));
            }
        }
        this.attendees = new ArrayList<Person>(peopleMap.values());
    }

    private void parseOrganizer(VEvent event) {
        String emailAddress = getEmail(event.getOrganizer().getValue());
        this.organizer = new Person(event.getOrganizer().getParameter("CN").getValue(), emailAddress, UUID.randomUUID().toString());
    }

    private String getEmail(String rawCN) {
        Matcher match = email.matcher(rawCN);
        String emailAddress = "";
        if(match.matches()) {
            emailAddress = match.group(1);
        }
        return emailAddress;
    }

//    public void setProdId(String prodIdLine) {
//        if(prodIdLine.contains("Google")) {
//            this.prodId = "Google";
//        }
//    }

    public long getMeetingId() {
        return meetingId;
    }

//    @Override
//    public String getMethod() {
//        return "REQUEST";
//    }
//
//    @Override
//    public Date getStartDateTime() {
//        return startDatetime.getTime();
//    }
//
//    @Override
//    public Date getEndDateTime() {
//        return endDateTime.getTime();
//    }

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

//    @Override
//    public List<Person> getAttendees() {
//        return this.attendees;
//    }

    private void parseStartDatetime(VEvent event) throws IllegalArgumentException {
        this.startDateTime = new GregorianCalendar();
        this.startDateTime.setTime(event.getStartDate().getDate());
    }

    private void parseEndDateTime(VEvent event) {
        this.endDateTime = new GregorianCalendar();
        this.endDateTime.setTime(event.getEndDate().getDate());
    }

    public Map toJsonMap() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", this.meetingName);
        jsonMap.put("meetingId", this.meetingId);
        jsonMap.put("startDate", (long) startDateTime.getTime().getTime());
        System.out.println("startDate: " + (long) startDateTime.getTime().getTime());
        jsonMap.put("endDate", (long) endDateTime.getTime().getTime());
        System.out.println("endDate: " + (long) startDateTime.getTime().getTime());
        jsonMap.put("organizer", organizer.toJsonMap());

        JSONArray attendeesJson = new JSONArray();
        for (int i = 0; i < this.attendees.size(); i++) {
            attendeesJson.add(this.attendees.get(i).toJsonMap());
        }
        jsonMap.put("attendees", attendeesJson);

        return jsonMap;
    }

    public List<Person> getAttendees() {
        return attendees;
    }
}

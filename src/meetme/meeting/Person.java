package meetme.meeting;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bryukhaa on 8/17/15.
 */
@Entity
@Table(name = "Participants")
public class Person {
//    private static Pattern personPattern = Pattern.compile("CN=([A-z0-9 ]+):mailto:([A-z0-9\\.]+@[A-z0-9]+\\.[A-z]+)");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participantId")
    private long participantId;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "guid")
    private String organizerGuid;

    @OneToOne(mappedBy = "organizer")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Meeting organizerMeeting;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected Person() {
        this.name = null;
        this.email = null;
    }

    public Person(String name, String email, String id) {
        this(name, email);
        this.organizerGuid = id;
    }

//    public static String[] parsePersonDetails(String line) throws InvalidArgumentException {
//        Matcher personRegex = personPattern.matcher(line);
//        if (personRegex.matches()) {
//            String name = personRegex.group(1);
//            String email = personRegex.group(2);
//            return new String[] { email, name };
//        } else {
//            throw new InvalidArgumentException(new String[]{"Line [" + line + "] does not contain Person data."});
//        }
//    }

    public String getOrganizerGuid() {
        return organizerGuid;
    }

//    public void setOrganizerGuid(String organizerGuid) {
//        this.organizerGuid = organizerGuid;
//    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getParticipantId() {
        return participantId;
    }

    public Map toJsonMap() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("id", this.participantId);
        jsonMap.put("name", this.name);
        jsonMap.put("email", this.email);
        return jsonMap;
    }

    public void setOrganizerGuid(String organizerGuid) {
        this.organizerGuid = organizerGuid;
    }
}

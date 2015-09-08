package meetme.meeting;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by bryukhaa on 8/16/15.
 */
public interface IMeeting {
    String getProdId();

    String getMethod();

    Date getStartDateTime();

    Date getEndDateTime();


    Person getOrganizer();

    List<Person> getAttendees();
}
package meetme.reports;

import org.junit.Test;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by bryukhaa on 9/7/15.
 */
public class CostReportTest {

    @Test
    public void calculate_meeting_cost_of_two_people_for_one_hour() throws URISyntaxException {
        // Given
        final long attendeeCount = 3;
        final long meetingId = -1;
        final Calendar start = new GregorianCalendar(2015, 1, 1, 1, 0);
        final Calendar end = new GregorianCalendar(2015, 1, 1, 2, 0);

        // When
        // 100,000 / 49 / 40 = 51
        CostReport report = new CostReport(meetingId, start, end, attendeeCount);
        double cost = report.calculateMeetingCost(51);

        // Then
        assertEquals(3 * 51 * 1, cost, 0);
    }
}
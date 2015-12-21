package meetme.reports;

import java.util.Calendar;

/**
 * Created by bryukhaa on 9/7/15.
 */
public class CostReport {
    private final long attendeeCount;
    private final long meetingId;
    private final Calendar start;
    private final Calendar end;

    public CostReport(long meetingId, Calendar start, Calendar end, long attendeeCount) {
        this.meetingId = meetingId;
        this.start = start;
        this.end = end;
        this.attendeeCount = attendeeCount;
    }

    /**
     * Calculates cost of a meeting by multiplying specified cost for each person per hour of each meeting.
     * @param personCostPerHour
     * Decided cost of one person's one hour of work time
     * @return
     * total cost of given meeting
     */
    public double calculateMeetingCost(double personCostPerHour) {
        double totalPeopleCostPerHour = this.attendeeCount * personCostPerHour;
        double totalCost = ((end.getTime().getTime() - start.getTime().getTime()) / 3600000.0) * totalPeopleCostPerHour;

        return totalCost;
    }

    public long getAttendeeCount() {
        return attendeeCount;
    }
}

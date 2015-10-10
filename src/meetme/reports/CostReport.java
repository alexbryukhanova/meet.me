package meetme.reports;

import meetme.meeting.Meeting;

/**
 * Created by bryukhaa on 9/7/15.
 */
public class CostReport {
    private Meeting meeting;

    public CostReport(Meeting meeting) {
        this.meeting = meeting;
    }

    /**
     * Calculates cost of a meeting by multiplying specified cost for each person per hour of each meeting.
     * @param personCostPerHour
     * Decided cost of one person's one hour of work time
     * @return
     * total cost of given meeting
     */
    public double calculateMeetingCost(double personCostPerHour) {
        double totalPeopleCostPerHour = this.meeting.getAttendees().size() * personCostPerHour;
//        double totalCost = ((meeting.getEndDateTime().getTime() - meeting.getStartDateTime().getTime()) / 3600000.0) * totalPeopleCostPerHour;

//        return totalCost;
        return 1;
    }
}

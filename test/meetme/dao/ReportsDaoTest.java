package meetme.dao;

import meetme.reports.CostReport;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bryukhaa on 10/12/15.
 */
public class ReportsDaoTest {

    @Test
    public void load_statistics_for_organizer(){
        // Given
        long organizerId = 9;

        // When
        ReportsDao dao = new ReportsDao();
        List<CostReport> stats = dao.getMeetingCostReport(organizerId);
        Dao.closeSessionFactory();

        // Then
        assertNotNull(stats);
        assertEquals("Found 1 meeting.", 1, stats.size());
        assertEquals("Meeting had 3 people", 3, stats.get(0).getAttendeeCount());
    }

    @Test
    public void count_all_agendaless_meetings() {
        // Given
        long organizerId = 37;

        // When
        ReportsDao dao = new ReportsDao();
        long stats = dao.getNoAgendaMeetings(organizerId);
        Dao.closeSessionFactory();

        // Then
        assertEquals("6 meetings have no agenda", 6, stats);
    }
}

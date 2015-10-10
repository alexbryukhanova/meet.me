package meetme.dao;

/**
 * Created by bryukhaa on 9/10/15.
 */
public class PersonDaoTest {
    public static void main(String[] args) {
        PersonDao dao = new PersonDao();
        System.out.println(dao.getParticipantByEmail("asdf@asdf.com").getOrganizerGuid());
//        dao.persistOrganizer(new Person("asdf", "asdf@asdf.com", null));
    }
}

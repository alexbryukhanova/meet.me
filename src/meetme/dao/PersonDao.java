package meetme.dao;

import meetme.meeting.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.UUID;

/**
 * Created by bryukhaa on 9/29/15.
 */
public class PersonDao extends Dao {
    public Person getParticipantByEmail(String personEmail) {
        Session session = null;
        Person existing = null;
        try {
            session = sessionFactory.openSession();
            String hql = "select person from Person person where person.email = :email";
            existing = (Person) session.createQuery(hql)
                    .setString("email", personEmail)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return existing;
    }

    /**
     * Persists organizer as a user if it doesn't exist.
     *
     * organizer
     *  organizer's details
     * returns
     *  true if given organizer is a new user; false otherwise
     */
    public Person persistOrganizer(Person organizer) {
        Transaction tx = null;
        Session session = null;

        try {
            Person existing = getParticipantByEmail(organizer.getEmail());
            if (existing == null) {
                session = sessionFactory.openSession();
                tx = session.beginTransaction();
                String id = UUID.randomUUID().toString();
                organizer.setOrganizerGuid(id);
                session.save(organizer);
                tx.commit();
            } else {
                // Get organizer's unique ID
                System.out.println("Organizer [" + existing.getName() + "] already exists with GUID [" + existing.getOrganizerGuid() + "]");
                organizer = existing;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return organizer;
    }
}

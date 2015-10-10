package meetme.email;

import meetme.dao.Dao;

/**
 * Created by bryukhaa on 9/11/15.
 */
public class MainEmail {
    public static void main(String[] args) {
        EmailInviteReader email = new EmailInviteReader("", "");
        email.getEmail();

        Dao.closeSessionFactory();
    }
}

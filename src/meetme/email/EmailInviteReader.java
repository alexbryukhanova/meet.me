package meetme.email;

import meetme.dao.MeetingDao;
import meetme.meeting.Meeting;
import meetme.meeting.MeetingInvitesManager;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by bryukhaa on 9/11/15.
 */
public class EmailInviteReader extends Thread {

    private boolean isOnline;
    private String account;
    private String password;

    public EmailInviteReader(String userName, String password) {
        this.account = userName;
        this.password = password;
    }

    public void getEmail() {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
            }
        });
        Store store = null;
        try {
            store = session.getStore();
            store.connect("imap.gmail.com", account, password);
            Folder inboxFolder = store.getFolder("INBOX");
            inboxFolder.open(Folder.READ_WRITE);
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen,false);
            Message messages[] = inboxFolder.search(unseenFlagTerm);

            for (int i = 0; i < messages.length; i++) {
                readEmail(messages[i]);
                System.out.println();
                System.out.println("-----------------------------------------" +
                        "-----------------------------------");

                messages[i].setFlag(Flags.Flag.DELETED, true);
            }
            inboxFolder.close(true);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void readEmail(Message msg) throws MessagingException {
        System.out.println("SENT DATE:" + msg.getSentDate());
        System.out.println("SUBJECT:" + msg.getSubject());

        // Want:
        // multipart/ALTERNATIVE
        // TEXT/CALENDAR
        // APPLICATION/ICS
        Multipart mp = null;
        try {
            mp = (Multipart) msg.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                System.out.println("CONTENT TYPE: " + bp.getContentType());
                System.out.println("DISPOSITION: " + bp .getDisposition());
                if("ATTACHMENT".equalsIgnoreCase(bp.getDisposition())){
                    readInvite(bp);
                    return;
                } else if("multipart/ALTERNATIVE".equalsIgnoreCase(bp.getContentType())) {
                    System.out.println("--- MULTIPART ------------------------>");
                    Multipart multipart = (Multipart) bp.getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        BodyPart part = multipart.getBodyPart(j);
                        System.out.println("CONTENT TYPE: " + part.getContentType());
                        System.out.println("DISPOSITION: " + part.getDisposition());

                        if(part.getContentType().startsWith("TEXT/CALENDAR")) {
                            readInvite(part);
                            return;
                        } else if(part.getContentType().startsWith("TEXT/PLAIN")) {
                            part.getContent();
                        }
                        System.out.println();
                    }
                } else {
                    // Open message to mark it as "read"
                    bp.getContent();

                    /* Disposition cases:
                     *      INLINE
                     */
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInvite(BodyPart bp) throws IOException, MessagingException {
        InputStream is = bp.getInputStream();
        InputStreamReader in = new InputStreamReader(is);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(in);

        // Read invite
        String read = br.readLine();
        while(read != null) {
            // Add line break or remove leading whitespace if necessary
            if (read.startsWith("PRODID")
                    || read.startsWith("VERSION")
                    || read.startsWith("CALSCALE")
                    || read.startsWith("METHOD")
                    || read.startsWith("BEGIN:VEVENT")
                    || read.startsWith("DTSTART")
                    || read.startsWith("DTEND")
                    || read.startsWith("DTSTAMP")
                    || read.startsWith("ORGANIZER")
                    || read.startsWith("UID")
                    || read.startsWith("ATTENDEE")
                    || read.startsWith("CREATED")
                    || read.startsWith("DESCRIPTION")
                    || read.startsWith("LAST-MODIFIED")
                    || read.startsWith("LOCATION")
                    || read.startsWith("SEQUENCE")
                    || read.startsWith("STATUS")
                    || read.startsWith("SUMMARY")
                    || read.startsWith("TRANSP")
                    || read.startsWith("END")) {
                sb.append('\n');
            } else {
                read = read.replaceAll("^\\s+", "");
            }
            sb.append(read);

            read = br.readLine();
        }

        // Save invite
        MeetingInvitesManager manager = new MeetingInvitesManager(new MeetingDao());
        Meeting meeting = manager.createInvite(sb.toString());
    }

    @Override
    public synchronized void start() {
        this.isOnline = true;
        super.start();
    }

    @Override
    public void run() {
        while(isOnline) {
            getEmail();
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        this.isOnline = false;
        super.interrupt();
    }
}

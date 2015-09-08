package meetme.meeting;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by bryukhaa on 8/14/15.
 */
public class Tokenizer {
    private Meeting invite;

    public Tokenizer(String invite) {
        String[] inviteLines = invite.split(System.getProperty("line.separator"));

//        this.invite = new Meeting(null);
//        parse(inviteLines);
    }

    private void parse(String[] lines) {
        for (int l = 0; l < lines.length; l++) {
            if("BEGIN:VCALENDAR".equals(lines[l])) {

            } else if (lines[l].startsWith("PRODID")) {
                invite.setProdId(lines[l]);
            } else if ("BEGIN:VEVENT".equals(lines[l])) {
                try {
                    l = parseEvent(lines, ++l);
                } catch (InvalidArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int parseEvent(String[] lines, int cursor) throws InvalidArgumentException {
        for (int l = cursor; l < lines.length; l++) {
            if("END:VEVENT".equals(lines[l])) {
                return l;
            }
        }
        throw new InvalidArgumentException(new String[]{"VEVENT didn't end"});
    }

    public IMeeting getInvite() {
        return invite;
    }
}

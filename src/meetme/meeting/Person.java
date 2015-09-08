package meetme.meeting;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bryukhaa on 8/17/15.
 */
public class Person {
    private static Pattern personPattern = Pattern.compile("CN=([A-z0-9 ]+):mailto:([A-z0-9\\.]+@[A-z0-9]+\\.[A-z]+)");

    private final String email;
    private final String name;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public static Person parsePerson(String line) throws InvalidArgumentException {
        Matcher personRegex = personPattern.matcher(line);
        if (personRegex.matches()) {
            String name = personRegex.group(1);
            String email = personRegex.group(2);
            Person newPerson = new Person(name, email);
            return newPerson;
        } else {
            throw new InvalidArgumentException(new String[]{"Line [" + line + "] does not contain Person data."});
        }
    }

    public String getEmail() {
        return email;
    }
}

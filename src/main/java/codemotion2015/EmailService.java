package codemotion2015;

public class EmailService {

    public void sendEmail(Person person, String message) {
        System.out.println("Sending email to " + person.getName() + "with content: " + message);
    }
}

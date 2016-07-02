package codemotion2015.java;

import codemotion2015.NotificationService;
import codemotion2015.Person;
import codemotion2015.RegisterUserService;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class E8_MockingJmock {

        @Rule public JUnitRuleMockery context = new JUnitRuleMockery();


        @Test public void should_send_a_notification_when_the_someone_registers() {
                //given:
                NotificationService mockedNotificationService = context.mock(NotificationService.class);
                RegisterUserService registerUserService = new RegisterUserService(mockedNotificationService);

                //then:
                context.checking(new Expectations() {{
                        oneOf (mockedNotificationService).sendNotification(with(any(Person.class)), with("User created"));
                }});

                //when:
                registerUserService.registerUser("Iván", "López");
        }

        @Test public void should_mock_an_implementation() {
                // Jmock does not allow mocking an implementation by default
                // http://www.jmock.org/mocking-classes.html
        }

        @Test public void should_check_constraints_on_interactions() {
                // Jmock does not allow mocking an implementation by default
                // http://www.jmock.org/mocking-classes.html
                // and we have to create a custom Matcher
                // http://www.jmock.org/custom-matchers.html
        }

        final Sequence sequence = context.sequence("sequence for the below test");
        @Test public void should_check_the_order() {
                //given:
                Person person = new Person();
                person.setName("Iván");
                person.setLastName("López");
                NotificationService mockedNotificationService = context.mock(NotificationService.class);

                context.checking(new Expectations() {{
                        //then:
                        oneOf(mockedNotificationService).sendNotification(with(person), with("msg1")); inSequence(sequence);
                        //then:
                        oneOf(mockedNotificationService).sendNotification(with(person), with("msg2")); inSequence(sequence);
                        //then:
                        oneOf(mockedNotificationService).sendNotification(with(person), with("msg3")); inSequence(sequence);
                }});

                //when:
                mockedNotificationService.sendNotification(person, "msg1");
                mockedNotificationService.sendNotification(person, "msg2");
                mockedNotificationService.sendNotification(person, "msg3");

        }
}

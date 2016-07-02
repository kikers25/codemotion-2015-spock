package codemotion2015.java;

import codemotion2015.EmailService;
import codemotion2015.NotificationService;
import codemotion2015.Person;
import codemotion2015.RegisterUserService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class E8_MockingMockito {

        @Test public void should_send_a_notification_when_the_someone_registers() {
                //given:
                NotificationService mockedNotificationService = mock(NotificationService.class);
                RegisterUserService registerUserService = new RegisterUserService(mockedNotificationService);

                //when:
                registerUserService.registerUser("Iván", "López");

                //then:
                verify(mockedNotificationService).sendNotification(anyObject(), eq("User created"));
        }

        @Test public void should_mock_an_implementation() {
                //given:
                Person person = new Person();
                person.setName("Iván");
                person.setLastName("López");
                EmailService mockedEmailService = mock(EmailService.class);

                //when:
                mockedEmailService.sendEmail(person, "How are you?");

                //then:
                verify(mockedEmailService).sendEmail(person, "How are you?");
        }

        @Test public void should_check_constraints_on_interactions() {
                //given:
                Person person = new Person();
                person.setName("Iván");
                person.setLastName("López");
                NotificationService mockedNotificationService = mock(NotificationService.class);

                //when:
                mockedNotificationService.sendNotification(person, "How are you?");

                //then:
                ArgumentCaptor<String> varArgs = ArgumentCaptor.forClass(String.class);
                verify(mockedNotificationService).sendNotification(anyObject(), varArgs.capture());
                String it = varArgs.getValue();
                assertThat(it, is(instanceOf(String.class)));
                assertThat(it.length(), greaterThan(5));
        }

        @Test public void should_check_the_order() {
                //given:
                Person person = new Person();
                person.setName("Iván");
                person.setLastName("López");
                NotificationService mockedNotificationService = mock(NotificationService.class);

                //when:
                mockedNotificationService.sendNotification(person, "msg1");
                mockedNotificationService.sendNotification(person, "msg2");
                mockedNotificationService.sendNotification(person, "msg3");

                //then:
                InOrder inOrder = inOrder(mockedNotificationService);
                inOrder.verify(mockedNotificationService).sendNotification(person, "msg1");
                inOrder.verify(mockedNotificationService).sendNotification(person, "msg2");
                inOrder.verify(mockedNotificationService).sendNotification(person, "msg3");
        }
}

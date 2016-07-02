package codemotion2015.java;

import codemotion2015.Person;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class E7_SharedJ {

        private Person person;

        @Before
        public void setUp() {
                person = new Person();
                person.setName("Iván");
                person.setLastName("López");
        }

        @Test public void should_get_name() {
                // given:
                System.out.println(person);

                // expect:
                assertThat(person.getName(), is("Iván"));
        }

        @Test public void should_get_lastName() {
                // given:
                System.out.println(person);

                // expect:
                assertThat(person.getLastName(), is("López"));
        }
}

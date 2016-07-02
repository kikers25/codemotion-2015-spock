package codemotion2015;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class E12_WithJava {

        @Test public void should_check_value_on_person() {
                //when:
                Person person = new Person("Iván", "López");

                //then:
                assertThat(person, allOf(
                    hasProperty("name", is("Iván")),
                    hasProperty("lastName", is("López"))
                ));
        }
}

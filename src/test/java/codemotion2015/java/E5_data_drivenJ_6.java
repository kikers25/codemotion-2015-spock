package codemotion2015.java;

import codemotion2015.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Parameterized.class)
public class E5_data_drivenJ_6 {

        @Parameterized.Parameter(value = 0)
        public String theName;

        @Parameterized.Parameter(value = 1)
        public String theLastName;


        // where:
        @Parameterized.Parameters
        public static Collection<String[]> data() {
                final Collection<String[]> parametersProvided = new ArrayList<>();

                try (Stream<String> stream = Files.lines(Paths.get("src/test/resources/users.csv"))) {
                        stream.forEach(line -> parametersProvided.add(line.split(",")));
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return parametersProvided;
        }

        @Test public void should_read_data_from_an_iterable() {
                // given:
                Person person = new Person();
                person.setName(theName);
                person.setLastName(theLastName);

                // expect:
                assertThat(person, isA(Person.class));
                assertThat(person.getName(), is(theName));
                assertThat(person.getLastName(), is(theLastName));
        }
}

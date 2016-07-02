package codemotion2015.java;

import codemotion2015.Person;
import codemotion2015.PersonRepository;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.spockframework.util.Assert.fail;

public class E9_StubbingMockito {

        @Test public void should_return_predefined_value() {
                //given:
                PersonRepository stubbedRepository = mock(PersonRepository.class);
                when(stubbedRepository.findById(anyObject()))
                    .thenReturn(new Person("John", "Doe"));

                //when:
                Person person = stubbedRepository.findById(1L);

                //then:
                assertThat(person.getName(), is("John"));
                assertThat(person.getLastName(), is("Doe"));
        }

        @Test public void should_return_different_values_in_every_call() {
                //given:
                PersonRepository stubbedRepository = mock(PersonRepository.class);
                when(stubbedRepository.findById(anyObject()))
                    .thenReturn(new Person("John", "Doe"))
                    .thenReturn(new Person("Jane", "Doe"));

                //when:
                Person person1 = stubbedRepository.findById(1L);
                Person person2 = stubbedRepository.findById(1L);
                Person person3 = stubbedRepository.findById(1L);

                //then:
                assertThat(person1.getName(), is("John"));
                assertThat(person2.getName(), is("Jane"));
                assertThat(person3.getName(), is("Jane"));
        }

        @Test public void should_return_values_depending_the_parameters() {
                //given:
                PersonRepository stubbedRepository = mock(PersonRepository.class);
                when(stubbedRepository.findById(20L))
                    .thenReturn(new Person("Jane", "Doe"));
                when(stubbedRepository.findById(11L))
                    .thenReturn(new Person("John", "Doe"));

                //when:
                Person person1 = stubbedRepository.findById(11L);
                Person person2 = stubbedRepository.findById(20L);

                //then:
                assertThat(person1.getName(), is("John"));
                assertThat(person2.getName(), is("Jane"));
        }

        @Test public void should_throw_an_exception() {
                //given:
                PersonRepository stubbedRepository = mock(PersonRepository.class);
                when(stubbedRepository.findById(anyObject()))
                    .thenThrow(new RuntimeException("Person does not exist"));

                //when:
                try {
                        stubbedRepository.findById(1L);
                        fail("Did not Trown exception");

                } catch(Exception e) {
                        assertThat(e, is(instanceOf(RuntimeException.class)));
                        assertThat(e.getMessage(), is("Person does not exist"));
                }
        }

        @Test public void should_stub_more_than_one_method() {
                //given:
                PersonRepository stubbedRepository = mock(PersonRepository.class);
                when(stubbedRepository.findById(anyObject()))
                    .thenReturn(new Person("Peter", "Smith"));
                when(stubbedRepository.findAllByLastName("Doe"))
                    .thenReturn(Arrays.asList(
                        new Person("John", "Doe"),
                        new Person("Jane", "Doe")
                    ));

                //expect:
                assertThat(stubbedRepository.findById(99L).getName(), is("Peter"));
                assertThat(stubbedRepository.findAllByLastName("Doe"), hasSize(2));
        }
}

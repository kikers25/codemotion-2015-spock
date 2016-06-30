package codemotion2015;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.hasSize;

public class E9_StubbingJmock {

        @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

        @Test public void should_return_predefined_value() {
                //given:
                PersonRepository stubbedRepository = context.mock(PersonRepository.class);
                context.checking(new Expectations() {{
                        oneOf(stubbedRepository).findById(with(any(Long.class)));
                        will(returnValue(new Person("John", "Doe")));
                }});

                //when:
                Person person = stubbedRepository.findById(1L);

                //then:
                assertThat(person.getName(), is("John"));
        }

        @Test public void should_return_different_values_in_every_call() {
                //given:
                PersonRepository stubbedRepository = context.mock(PersonRepository.class);
                context.checking(new Expectations() {{
                        oneOf(stubbedRepository).findById(with(any(Long.class)));
                        will(returnValue(new Person("John", "Doe")));

                        allowing(stubbedRepository).findById(with(any(Long.class)));
                        will(returnValue(new Person("Jane", "Doe")));
                }});

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
                PersonRepository stubbedRepository = context.mock(PersonRepository.class);
                context.checking(new Expectations() {{
                        oneOf(stubbedRepository).findById(20L);
                        will(returnValue(new Person("Jane", "Doe")));

                        allowing(stubbedRepository).findById(11L);
                        will(returnValue(new Person("John", "Doe")));
                }});

                //when:
                Person person1 = stubbedRepository.findById(11L);
                Person person2 = stubbedRepository.findById(20L);

                //then:
                assertThat(person1.getName(), is("John"));
                assertThat(person2.getName(), is("Jane"));
        }

        @Rule
        public final ExpectedException exception = ExpectedException.none();

        @Test public void should_throw_an_exception() {
                //given:
                PersonRepository stubbedRepository = context.mock(PersonRepository.class);
                context.checking(new Expectations() {{
                        oneOf(stubbedRepository).findById(with(any(Long.class)));
                        will(throwException(new RuntimeException("Person does not exist")));
                }});

                //then:
                exception.expect(RuntimeException.class);
                exception.expectMessage("Person does not exist");

                //when:
                stubbedRepository.findById(1L);
        }

        @Test public void should_stub_more_than_one_method() {
                //given:
                PersonRepository stubbedRepository = context.mock(PersonRepository.class);
                context.checking(new Expectations() {{
                        oneOf(stubbedRepository).findById(with(any(Long.class)));
                        will(returnValue(new Person("Peter", "Smith")));

                        oneOf(stubbedRepository).findAllByLastName("Doe");
                        will(returnValue(Arrays.asList(
                            new Person("John", "Doe"),
                            new Person("Jane", "Doe")
                        )));
                }});

                //expect:
                assertThat(stubbedRepository.findById(99L).getName(), is("Peter"));
                assertThat(stubbedRepository.findAllByLastName("Doe"), hasSize(2));
        }
}

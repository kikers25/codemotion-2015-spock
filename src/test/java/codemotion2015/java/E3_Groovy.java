package codemotion2015.java;

import codemotion2015.DataHelper;
import codemotion2015.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class E3_Groovy {

        @Test
        public void should_add_an_element_to_a_list() {
                List<Integer> numbers = DataHelper.someFibonacciNumbers();

                numbers.add(21);

                assertThat(numbers, hasItem(21));
        }

        @Test
        public void should_make_some_assertions_in_elements_in_a_list() {
                List<Person> persons = DataHelper.makePersonList();

                assertThat(persons, hasSize(4));
                assertThat(persons.stream()
                    .map(Person::getName)
                    .collect(Collectors.toList()), containsInAnyOrder("Sheldon", "Leonard", "Rajesh", "Howard"));
                assertThat(persons.stream()
                    .map(Person::getName)
                    .sorted()
                    .collect(Collectors.toList()), containsInAnyOrder("Howard", "Leonard", "Rajesh", "Sheldon"));
                assertThat(persons.stream()
                    .map(p -> p.getLastName().length())
                    .collect(Collectors.toList()), contains(6, 10, 12, 8));
                // persons.name.min { it.length() } == 'Rajesh'
        }

        @Test
        public void should_remove_an_element_from_a_map() {
                // given:
                Map<String, ?> map = DataHelper.makePersonMap();

                // when:
                map.remove("name");

                assertThat(map.size(), is(2));
                assertThat(map.keySet(), not(contains("name")));
                // map.lastName == 'López'
                assertThat(map.get("lastName"), is("López"));
                // map['lastName'] == 'López'
                assertThat(map.get("age"), is (35));
        }
}

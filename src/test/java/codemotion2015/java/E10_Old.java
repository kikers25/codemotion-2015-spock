package codemotion2015.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class E10_Old {

        @Test public void should_should_increase_the_size_of_the_list_when_adding_a_number() {
                //given:
                // Arrays.asList generates a fixed list and when a value is added it will throw an exception
                List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8, 13));
                int old = numbers.size();

                //when
                numbers.add(21);

                //then
                assertThat(numbers.size(), is(old + 1));
        }
}

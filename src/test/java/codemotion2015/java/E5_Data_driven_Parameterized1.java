package codemotion2015.java;

import org.hamcrest.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class E5_Data_driven_Parameterized1 {

        @Parameterized.Parameter
        public Integer number;

        @Parameterized.Parameters
        public static Collection<Integer> data() {
                return Arrays.asList(-10, 0, 2, 42, 1000);
        }

        @Test public void all_numbers_should_be_even() {
                assertThat(number, CoreMatchers.is(IsEvenNumber.evenNumber()));
        }
}
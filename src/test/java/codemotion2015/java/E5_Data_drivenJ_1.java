package codemotion2015.java;

import org.hamcrest.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class E5_Data_drivenJ_1 {

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

class IsEvenNumber extends TypeSafeMatcher<Integer> {

        @Override
        public boolean matchesSafely(Integer number) {
                if (number % 2 != 0) {
                        return false;
                }
                return true;
        }

        @Override
        public void describeTo(Description description) {
                description.appendText("even number");
        }

        @Factory
        public static <T> Matcher<Integer> evenNumber() {
                return new IsEvenNumber();
        }
}
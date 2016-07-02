package codemotion2015.java;

import org.hamcrest.*;

public class IsEvenNumber extends TypeSafeMatcher<Integer> {

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

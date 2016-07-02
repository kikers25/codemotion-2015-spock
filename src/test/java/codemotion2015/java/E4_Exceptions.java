package codemotion2015.java;

import org.junit.Test;

public class E4_Exceptions {

        @Test(expected = NumberFormatException.class)
        public void should_throw_an_exception() {
                Long.valueOf("foo");
        }
}

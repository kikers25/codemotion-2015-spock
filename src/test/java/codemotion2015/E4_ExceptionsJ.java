package codemotion2015;

import org.junit.Test;

public class E4_ExceptionsJ {

        @Test(expected = NumberFormatException.class)
        public void should_throw_an_exception() {
                Long.valueOf("foo");
        }
}

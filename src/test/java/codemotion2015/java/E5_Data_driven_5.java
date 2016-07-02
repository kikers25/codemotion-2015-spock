package codemotion2015.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class E5_Data_driven_5 {

        @Parameterized.Parameter(value = 0)
        public Integer x;

        @Parameterized.Parameter(value = 1)
        public Integer y;

        @Parameterized.Parameter(value = 2)
        public Integer result;

        @Parameterized.Parameters(name = "should check that minimum of {0} and {1} is {2}")
        public static Collection<Integer[]> data() {
                return Arrays.asList(
                    new Integer[]{1, 2 , 1},
                    new Integer[]{-1, -2, -2},
                    new Integer[]{0, 1, 0});
        }

        @Test public void should_check_that_minimum_of_x_and_y_is_result() {
                assertThat(Math.min(x, y), is(result));
        }

}

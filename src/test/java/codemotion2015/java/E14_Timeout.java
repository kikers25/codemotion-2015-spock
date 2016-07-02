package codemotion2015.java;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

@Ignore("Remove to check that the test fails")
public class E14_Timeout {

        @Test(timeout = 500) public void should_fail_after_500_milliseconds() throws InterruptedException {
                //given:
                Thread.sleep(500);

                //expect:
                assertTrue(true);
        }
}

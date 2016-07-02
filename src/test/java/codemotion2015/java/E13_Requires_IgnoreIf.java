package codemotion2015.java;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assume.assumeThat;

public class E13_Requires_IgnoreIf {

        public static final String OS = System.getProperty("os.name").toLowerCase();

        @Test public void should_only_run_on_Linux() {
                assumeThat(OS, anyOf(
                    containsString("nix"),
                    containsString("nux"),
                    containsString("aix")));
                assertTrue(true);
        }

        @Test public void should_only_run_on_Windows() {
                assumeThat(OS, containsString("windows"));
                assertTrue(true);
        }

        @Test public void should_be_ignore_in_Java8() {
                // Similar but not the same than the groovy version
                assumeThat(System.getProperty("java.version"), not(startsWith("1.8.")));
                assertTrue(false);
        }

        @Test public void should_be_ignore_in_Java9() {
                // Similar but not the same than the groovy version
                assumeThat(System.getProperty("java.version"), not(startsWith("1.9.")));
                assertTrue(true);
        }

        @Test public void should_run_for_Groovy_conference() {
                assumeThat(groovyConferences(), hasItem("Greach"));
                assertTrue(true);
        }

        private static List<String> groovyConferences() {
                return asList("Greach", "GR8Conf", "GGX");
        }

}

package codemotion2015.java;

import org.junit.*;

public class E6_LifeCycle {

        @BeforeClass
        public static void setupSpec() {
                System.out.println("> setupSpec");
        }

        @Before
        public void setUp() {
                System.out.println(">> setup");
        }

        @After
        public void cleanup() {
                System.out.println(">> cleanup");
        }

        @AfterClass static public void cleanupSpec() {
                System.out.println("> cleanSpec");
        }

        @Test public void test() {
                System.out.println(">>> test");
        }

        @Test public void test_2() {
                System.out.println(">>> test 2");
        }
}

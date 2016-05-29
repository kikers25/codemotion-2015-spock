package codemotion2015;

import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore("Comment this annotation to check the errors")
public class E2_PowerAsserts {

    @Test
    public void should_fail_with_string() {
        assertThat(reverse("Hello Codemotion!"), is("ointmodeoC olleH"));
    }

    @Test
    public void should_fail_with_maps_and_lists() {
        // given:
        Map<String, Object> child1 =Collections.unmodifiableMap(new HashMap<String, Object>(){{
            put("name", "Judith");
            put("age", 8);
        }});
        Map<String, Object> child2 =new HashMap<String, Object>(){{
            put("name", "Adriana");
            put("age", 5);
        }};
        Map<String, Object> data =new HashMap<String, Object>(){{
            put("name", "Iván");
            put("age", 35);
            put("childs", Arrays.asList(child1, child2));
        }};

        // expect:
        assertThat(((List<Map<String, Object>>) data.get("childs")).get(0).get("name"), is("Adriana"));
    }

    private String reverse(String myString) {
        StringBuilder myStringBuilder = new StringBuilder(myString);

        return myStringBuilder.reverse().toString();
    }

}

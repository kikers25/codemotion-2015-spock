import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class E1_Basic {

    @Test
    public void should_reverse_a_string() {
        //given: "a string"
        String myString = "Hello Codemotion!";

        //when: "reversing it"
        String reversed = reverse(myString);

        //then: "it is reversed"
        assertThat(reversed, is("!noitomedoC olleH"));
    }

    private String reverse(String myString) {
        StringBuilder myStringBuilder = new StringBuilder(myString);

        return myStringBuilder.reverse().toString();
    }

}

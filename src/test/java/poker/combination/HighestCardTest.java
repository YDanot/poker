package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HighestCardTest {

    @Test
    public void highestTest() {
        assertThat(highest("hK h10 d9 d8 d2")).isEqualTo("KING");
        assertThat(highest("hA h10 d9 d8 d2")).isEqualTo("ACE");
    }

    @Test
    public void compare() {
        assertThat(Glue.toCard("hK")).isGreaterThan(Glue.toCard("hQ"));
    }

    private String highest(String s) {
        return String.valueOf(CombinationType.HIGHEST.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

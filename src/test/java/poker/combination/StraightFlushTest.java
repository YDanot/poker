package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StraightFlushTest {

    @Test
    public void find_straight() {
        assertThat(straight("hK hQ h10 hJ h9")).isEqualTo("KING");
        assertThat(straight("h8 hQ h10 hJ h9")).isEqualTo("QUEEN");
        assertThat(straight("hK hQ hJ hA h10 h9 cA")).isEqualTo("ACE");
    }

    private String straight(String s) {
        return String.valueOf(CombinationType.STRAIGHT_FLUSH.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

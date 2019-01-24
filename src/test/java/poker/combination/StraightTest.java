package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StraightTest {

    @Test
    public void find_straight() {
        assertThat(straight("hK dQ s10 dJ d9")).isEqualTo("KING");
        assertThat(straight("h8 dQ s10 dJ d9")).isEqualTo("QUEEN");
    }

    private String straight(String s) {
        return String.valueOf(CombinationType.STRAIGHT.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

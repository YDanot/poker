package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PairTest {

    @Test
    public void find_a_pair() {
        assertThat(pair("hK dK d9 d8 d2")).isEqualTo("KING");
        assertThat(pair("h10 d9 d8 h2 d2")).isEqualTo("TWO");
    }

    private String pair(String s) {
        return String.valueOf(CombinationType.PAIR.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

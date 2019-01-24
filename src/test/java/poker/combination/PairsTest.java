package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PairsTest {

    @Test
    public void find_pairs() {
        assertThat(pairs("hK dK s8 d8 d2")).isEqualTo("KING");
        assertThat(pairs("h7 d7 s8 d8 d2")).isEqualTo("EIGHT");
    }

    private String pairs(String s) {
        return String.valueOf(CombinationType.PAIRS.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

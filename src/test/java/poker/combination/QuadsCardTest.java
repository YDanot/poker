package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuadsCardTest {

    @Test
    public void find_quads() {
        assertThat(quads("hK dK sK cK d2")).isEqualTo("KING");
        assertThat(quads("hQ dQ sQ cQ d2")).isEqualTo("QUEEN");
    }

    private String quads(String s) {
        return String.valueOf(CombinationType.QUADS.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

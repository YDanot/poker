package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlushTest {

    @Test
    public void find_flush() {
        assertThat(flush("hK hQ h10 hJ h7")).isEqualTo("KING");
        assertThat(flush("h3 hQ h10 hJ h7")).isEqualTo("QUEEN");
    }

    private String flush(String s) {
        return String.valueOf(CombinationType.FLUSH.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

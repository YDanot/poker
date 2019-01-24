package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TripsCardTest {

    @Test
    public void find_trips() {
        assertThat(trips("hK dK sK d8 d2")).isEqualTo("KING");
        assertThat(trips("hQ dQ sQ d8 d2")).isEqualTo("QUEEN");
    }

    private String trips(String s) {
        return String.valueOf(CombinationType.TRIPS.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

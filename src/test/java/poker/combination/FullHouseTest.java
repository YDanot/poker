package poker.combination;

import glue.Glue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FullHouseTest {

    @Test
    public void find_full_house() {
        assertThat(full_house("hK dK sK d8 d8")).isEqualTo("KING");
        assertThat(full_house("hQ dQ sQ d2 d2")).isEqualTo("QUEEN");
    }

    private String full_house(String s) {
        return String.valueOf(CombinationType.FULL_HOUSE.in(Glue.hand(s).cards()).orElse(Combination.NONE).comparisonValue());
    }
}

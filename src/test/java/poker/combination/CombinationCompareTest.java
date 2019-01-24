package poker.combination;

import glue.Glue;
import org.assertj.core.util.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static poker.combination.CombinationType.*;

public class CombinationCompareTest {

    @Test
    public void combinationType() {
        assertThat(STRAIGHT_FLUSH).isGreaterThan(QUADS);
        assertThat(QUADS).isGreaterThan(FULL_HOUSE);
        assertThat(FULL_HOUSE).isGreaterThan(FLUSH);
        assertThat(FLUSH).isGreaterThan(STRAIGHT);
        assertThat(STRAIGHT).isGreaterThan(TRIPS);
        assertThat(TRIPS).isGreaterThan(PAIRS);
        assertThat(PAIRS).isGreaterThan(PAIR);
        assertThat(PAIR).isGreaterThan(HIGHEST);
    }

    @Test
    public void combinationValue() {
        assertThat(Combination.of(TRIPS, Lists.newArrayList(Glue.toCard("cK"),Glue.toCard("sK"),Glue.toCard("hK"))))
                .isGreaterThan(
                        Combination.of(TRIPS, Lists.newArrayList(Glue.toCard("cQ"),Glue.toCard("sQ"),Glue.toCard("hQ")))
                );

    }
}

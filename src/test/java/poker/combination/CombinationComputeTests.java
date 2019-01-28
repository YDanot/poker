package poker.combination;

import one.util.streamex.StreamEx;
import org.junit.Test;
import poker.Hand;
import poker.card.Card;
import poker.card.Value;

import java.util.List;

import static glue.Glue.hand;
import static org.assertj.core.api.Assertions.assertThat;

public class CombinationComputeTests {

    @Test
    public void none() {
        assertThat(combinationsOf("hK dQ s2 d8 dJ")).isEqualTo(
                "KING - QUEEN - JACK - EIGHT - TWO");
    }

    @Test
    public void pair() {
        assertThat(combinationsOf("hK dK sJ d8 d2")).isEqualTo(
                "PAIR KING - JACK - EIGHT - TWO");
    }

    @Test
    public void pairs() {
        assertThat(combinationsOf("hK dK sJ dJ d2")).isEqualTo(
                "PAIRS KING JACK - TWO");
    }

    @Test
    public void trips() {
        assertThat(combinationsOf("hK dK sK d8 d2")).isEqualTo(
                "TRIPS KING - EIGHT - TWO");
    }

    @Test
    public void straight() {
        assertThat(combinationsOf("hK dQ sJ dA d10")).isEqualTo(
                "STRAIGHT ACE");
    }

    @Test
    public void flush() {
        assertThat(combinationsOf("hK hQ h10 hJ h7")).isEqualTo("FLUSH KING");
        assertThat(combinationsOf("h3 hQ h10 hJ h7")).isEqualTo("FLUSH QUEEN");
    }

    @Test
    public void full_house() {
        assertThat(combinationsOf("hK dK sK d8 c8")).isEqualTo("FULL_HOUSE KING over EIGHT");
        assertThat(combinationsOf("hK dK s8 d8 c8")).isEqualTo("FULL_HOUSE EIGHT over KING");
    }

    @Test
    public void quads() {
        assertThat(combinationsOf("hK dK sK cK d2")).isEqualTo(
                "QUADS KING - TWO");
    }

    @Test
    public void straight_flush() {
        assertThat(combinationsOf("hK hQ hJ hA h10")).isEqualTo(
                "STRAIGHT_FLUSH ACE");
    }

    @Test
    public void texas() {
        assertThat(combinationsOf("hK hQ hJ hA h10 h9 cA")).isEqualTo(
                "STRAIGHT_FLUSH ACE");

        assertThat(combinationsOf("hK cK sK dK h10 hA cA")).isEqualTo(
                "QUADS KING - ACE");

    }

    private String combinationsOf(String hand) {
        return render(hand(hand).combinationsDesc());
    }

    private String render(List<Combination> combinations) {
        StringBuilder s = new StringBuilder();
        combinations.forEach(combination ->
                s.append(" - ").append(render(combination)));
        return s.toString().substring(3);
    }

    private String render(Combination combination) {
        final Value comparisonValue = combination.comparisonValue();
        if (combination.type() == CombinationType.HIGHEST) {
            return String.valueOf(comparisonValue);
        }

        if (combination.type() == CombinationType.PAIRS) {
            final Card over = combination.cards().stream().filter(c -> c.value() != comparisonValue).findFirst().get();
            return combination.type() + " " + comparisonValue + " " + over.value();
        }

        if (combination.type() == CombinationType.FULL_HOUSE) {
            final Card over = combination.cards().stream().filter(c -> c.value() != comparisonValue).findFirst().get();
            return combination.type() + " " + comparisonValue + " over " + over.value();
        }

        return combination.type() + " " + comparisonValue;
    }
}

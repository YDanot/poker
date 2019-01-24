package poker;

import org.junit.Test;

import static glue.Glue.hand;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandTests {

    @Test
    public void higher_card_wins_when_no_combination_on_both_hands() {
        assertThat(hand("hK h10 d9 d8 d2").winsOn(hand("dQ d10 h9 h8 h2"))).isTrue();
    }

    @Test
    public void pair_wins_against_no_combination() {
        assertThat(hand("h10 d9 d8 h2 d2").winsOn(hand("dQ d10 h9 h8 h3"))).isTrue();
    }

    @Test
    public void highest_pair_wins() {
        assertThat(hand("h10 d10 d8 h3 d2").winsOn(hand("dQ dJ hJ h8 h3"))).isFalse();
        assertThat(hand("hK dK d8 h3 d2").winsOn(hand("dQ dJ hJ h8 h3"))).isTrue();
    }

    @Test
    public void pairs_wins_against_single_pair() {
        assertThat(hand("h10 d10 d8 h8 d2").winsOn(hand("dQ hQ h9 h8 h3"))).isTrue();
    }

    @Test
    public void pairs_with_the_highest_pair_wins() {
        assertThat(hand("h10 d10 d8 h8 d2").winsOn(hand("dQ hQ h9 h9 h3"))).isFalse();
        assertThat(hand("h10 d10 d8 h8 d4").winsOn(hand("c10 s10 c8 s8 c2"))).isTrue();
    }

    @Test
    public void highest_card_win_when_pairs_equals() {
        assertThat(hand("h10 d10 d8 h8 d4").winsOn(hand("c10 s10 c8 s8 c2"))).isTrue();
    }

    @Test
    public void trips_wins_against_pair() {
        assertThat(hand("hA dA s10 h9 d2").winsOn(hand("dQ hQ sQ h8 h3"))).isFalse();
        assertThat(hand("h10 d9 s2 h2 d2").winsOn(hand("dQ hQ h9 h8 h3"))).isTrue();
    }

    @Test
    public void highest_trips_wins() {
        assertThat(hand("h10 d10 s10 h3 d2").winsOn(hand("sJ dJ hJ h8 h3"))).isFalse();
        assertThat(hand("hK dK sK h3 d2").winsOn(hand("sJ dJ hJ h8 h3"))).isTrue();
    }

    @Test
    public void straight_wins_against_trips() {
        assertThat(hand("hA dK s10 hQ dJ").winsOn(hand("dQ hQ sQ h8 h3"))).isTrue();
        assertThat(hand("dQ hQ sQ h8 h3").winsOn(hand("hA dK s10 hQ dJ"))).isFalse();
    }

    @Test
    public void highest_straight_wins() {
        assertThat(hand("hA dK s10 hQ dJ").winsOn(hand("dK hQ sJ h9 h10"))).isTrue();
        assertThat(hand("dK hQ sJ h9 h10").winsOn(hand("hA dK s10 hQ dJ"))).isFalse();
    }

    @Test
    public void flush_wins_against_straight() {
        assertThat(hand("hK hQ h10 hJ h7").winsOn(hand("hA dK sQ hJ d10"))).isTrue();
        assertThat(hand("hA dK sQ hJ d10").winsOn(hand("hK hQ h10 hJ h7"))).isFalse();
    }

    @Test
    public void highest_flush_wins() {
        assertThat(hand("hK hQ h10 hJ h7").winsOn(hand("h2 hQ h10 hJ h7"))).isTrue();
        assertThat(hand("h2 hQ h10 hJ h7").winsOn(hand("hK hQ h10 hJ h7"))).isFalse();
    }

    @Test
    public void full_wins_against_flush() {
        assertThat(hand("hK dK sK d8 d8").winsOn(hand("hA hK hQ hJ h9"))).isTrue();
        assertThat(hand("hA hK hQ hJ h9").winsOn(hand("hK dK sK d8 d8"))).isFalse();
    }

    @Test
    public void highest_full_wins() {
        assertThat(hand("hK dK sK d8 d8").winsOn(hand("hQ dQ sQ dA dA"))).isTrue();
        assertThat(hand("hQ dQ sQ dA dA").winsOn(hand("hK dK sK d8 d8"))).isFalse();
    }

    @Test
    public void quads_wins_against_full_house() {
        assertThat(hand("dQ hQ sQ cQ h3").winsOn(hand("hA dA s10 h9 d2"))).isTrue();
        assertThat(hand("hA dA s10 h9 d2").winsOn(hand("dQ hQ sQ cQ h3"))).isFalse();
    }

    @Test
    public void highest_quads_wins() {
        assertThat(hand("h10 d10 s10 c10 d2").winsOn(hand("sJ dJ hJ cJ h3"))).isFalse();
        assertThat(hand("sJ dJ hJ cJ h3").winsOn(hand("h10 d10 s10 c10 d2"))).isTrue();
    }

    @Test
    public void straight_flush_wins_against_quads() {
        assertThat(hand("hA hK h10 hQ hJ").winsOn(hand("dQ hQ sQ cQ h3"))).isTrue();
        assertThat(hand("dQ hQ sQ cQ h3").winsOn(hand("hA hK h10 hQ hJ"))).isFalse();
    }

    @Test
    public void highest_straight_flush_wins() {
        assertThat(hand("hA hK h10 hQ hJ").winsOn(hand("cK cQ cJ c9 c10"))).isTrue();
        assertThat(hand("cK cQ cJ c9 c10").winsOn(hand("hA hK h10 hQ hJ"))).isFalse();
    }

    @Test
    public void draw() {
        final Hand ahand = hand("hA hK h10 hQ hJ");
        assertThat(ahand.winsOn(ahand)).isFalse();
    }
}

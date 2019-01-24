package poker;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static poker.card.Value.*;

public class ValueOrderTest {

    @Test
    public void test() {
        Assertions.assertThat(ACE).isGreaterThan(KING);
        Assertions.assertThat(KING).isGreaterThan(QUEEN);
        Assertions.assertThat(QUEEN).isGreaterThan(JACK);
        Assertions.assertThat(JACK).isGreaterThan(TEN);
        Assertions.assertThat(TEN).isGreaterThan(NINE);
        Assertions.assertThat(NINE).isGreaterThan(EIGHT);
        Assertions.assertThat(EIGHT).isGreaterThan(SEVEN);
        Assertions.assertThat(SEVEN).isGreaterThan(SIX);
        Assertions.assertThat(SIX).isGreaterThan(FIVE);
        Assertions.assertThat(FIVE).isGreaterThan(FOUR);
        Assertions.assertThat(FOUR).isGreaterThan(THREE);
        Assertions.assertThat(THREE).isGreaterThan(TWO);
    }
}

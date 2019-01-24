package glue;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;
import poker.card.Card;
import poker.card.Color;
import poker.Hand;
import poker.card.Value;

import java.util.ArrayList;
import java.util.List;

public class Glue {

    public static Hand hand(String hand) {
        return new Hand(toHand(hand));
    }

    @Test
    public void can_create_hand() {
        Assertions.assertThat(Glue.hand("hK h10 d9 d8 d2").cards()).isEqualTo(
                Lists.newArrayList(
                        Card.of(Color.HEART, Value.KING),
                        Card.of(Color.HEART, Value.TEN),
                        Card.of(Color.DIAMOND, Value.NINE),
                        Card.of(Color.DIAMOND, Value.EIGHT),
                        Card.of(Color.DIAMOND, Value.TWO)
                )
        );
    }

    private static List<Card> toHand(String hand) {
        final String[] splitHand = hand.split(" ");

        final ArrayList<Card> cards = new ArrayList<>();
        for (String card : splitHand) {
            cards.add(toCard(card));
        }
        return cards;
    }

    public static Card toCard(String card) {
        return Card.of(toColor(card.substring(0, 1)), toValue(card.substring(1)));
    }

    private static Value toValue(String value) {
        switch (value) {
            case "K":
                return Value.KING;
            case "Q":
                return Value.QUEEN;
            case "J":
                return Value.JACK;
            case "A":
                return Value.ACE;
            case "10":
                return Value.TEN;
            case "9":
                return Value.NINE;
            case "8":
                return Value.EIGHT;
            case "7":
                return Value.SEVEN;
            case "6":
                return Value.SIX;
            case "5":
                return Value.FIVE;
            case "4":
                return Value.FOUR;
            case "3":
                return Value.THREE;
            case "2":
                return Value.TWO;
        }
        throw new IllegalArgumentException();
    }

    private static Color toColor(String color) {
        switch (color) {
            case "h":
                return Color.HEART;
            case "d":
                return Color.DIAMOND;
            case "s":
                return Color.SPADES;
            case "c":
                return Color.CLUBS;
        }
        throw new IllegalArgumentException();
    }

}

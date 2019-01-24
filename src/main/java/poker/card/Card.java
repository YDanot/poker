package poker.card;

import java.util.Objects;

public class Card implements Comparable<Card> {

    public static final Card NONE = Card.of(Color.HEART, Value.NONE);

    public Color color() {
        return color;
    }

    private final Color color;
    private final Value value;

    private Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public static Card of(Color color, Value value) {
        return new Card(color, value);
    }

    public Value value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return color == card.color &&
                value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    public int compareTo(Card card) {
        return value.compareTo(card.value);
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", comparisonValue=" + value +
                '}';
    }
}

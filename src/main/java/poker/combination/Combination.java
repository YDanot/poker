package poker.combination;

import org.assertj.core.util.Lists;
import poker.card.Card;
import poker.card.Value;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Combination implements Comparable<Combination> {

    public static final Combination NONE = Combination.of(CombinationType.NONE, Lists.newArrayList(Card.NONE));

    private final CombinationType type;
    private final List<Card> cards;

    private Combination(CombinationType type, List<Card> cards) {
        this.type = type;
        this.cards = Lists.newArrayList(cards);
        this.cards.sort(Collections.reverseOrder());
    }

    public static Combination of(Card card) {
        return new Combination(CombinationType.HIGHEST, Lists.newArrayList(card));
    }

    public static Combination of(CombinationType type, List<Card> cards) {
        return new Combination(type, cards);
    }

    public Value comparisonValue() {
        if (type.equals(CombinationType.FULL_HOUSE)) {
            return cards.stream().collect(Collectors.groupingBy(Card::value,
                    Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 3).findFirst().get().getKey();

        }
        return cards.get(0).value();
    }

    public CombinationType type() {
        return type;
    }

    public List<Card> cards() {
        return cards;
    }

    @Override
    public int compareTo(Combination o) {
        return type.equals(o.type) ?
                comparisonValue().compareTo(o.comparisonValue()) : type.compareTo(o.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Combination)) return false;
        Combination that = (Combination) o;
        return type == that.type &&
                Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, cards);
    }


    @Override
    public String toString() {
        return type + " " + comparisonValue();
    }
}

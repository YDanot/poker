package poker;


import org.assertj.core.util.Lists;
import poker.card.Card;
import poker.combination.Combination;
import poker.combination.CombinationType;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = Lists.newArrayList(cards);
    }

    public boolean winsOn(Hand opponent) {
        final List<Combination> combinations = combinationsDesc();
        final List<Combination> opponentCombinations = opponent.combinationsDesc();

        for (int i = 0; i < combinations.size(); i++) {
            final int comparison = combinations.get(i).compareTo(opponentCombinations.get(i));
            if (comparison != 0)
                return comparison > 0;
        }
        return false;
    }

    public List<Combination> combinationsDesc() {
        List<Combination> combinations = new ArrayList<>();
        final List<Card> cardsCopy = cards();
        while (!cardsCopy.isEmpty()) {
            Combination combination = highestCombinationIn(cardsCopy);
            cardsCopy.removeAll(combination.cards());
            combinations.add(combination);
        }
        return combinations;
    }

    private Combination highestCombinationIn(List<Card> cards) {
        return CombinationType.desc().stream().map(type -> type.in(cards).orElse(Combination.NONE)).filter(c -> !c.equals(Combination.NONE)).findFirst().orElse(Combination.NONE);
    }

    public List<Card> cards() {
        return Lists.newArrayList(cards);
    }

}

package poker;

import one.util.streamex.StreamEx;
import org.assertj.core.util.Lists;
import poker.card.Card;
import poker.combination.Combination;
import poker.combination.CombinationType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    public static final int HAND_SIZE = 5;
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
        int nbCardCombined = 0;
        final List<Card> cardsCopy = cards();
        Combination combination = highestCombinationIn(cardsCopy);
        while (nbCardCombined < HAND_SIZE) {
            combinations.add(combination);
            nbCardCombined += combination.cards().size();
            cardsCopy.removeAll(combination.cards());

                if (classic()) {
                    combination = highestCombinationIn(cardsCopy);
                } else {
                    combination = highestCombinationOfNbCardIn(HAND_SIZE - nbCardCombined, cardsCopy);
                }

        }
        return combinations;
    }

    private boolean classic() {
        return cards.size() == HAND_SIZE;
    }

    private Combination highestCombinationOfNbCardIn(int nbCards, List<Card> cardsCopy) {
        return allSublists(cardsCopy, nbCards).stream().map(this::highestCombinationIn).max(Comparator.naturalOrder()).orElse(Combination.NONE);
    }

    private List<List<Card>> allSublists(List<Card> cardsCopy, int nbCards) {
        return StreamEx.cartesianPower(nbCards, cardsCopy).map(cards -> cards.stream().distinct().collect(Collectors.toList())).collect(Collectors.toList());
    }

    private Combination highestCombinationIn(List<Card> cards) {
        return CombinationType.desc().stream().map(type -> type.in(cards).orElse(Combination.NONE)).filter(c -> !c.equals(Combination.NONE)).findFirst().orElse(Combination.NONE);
    }

    public List<Card> cards() {
        return Lists.newArrayList(cards);
    }

}

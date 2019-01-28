package poker.combination;

import one.util.streamex.StreamEx;
import poker.card.Card;
import poker.card.Color;
import poker.card.Value;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static poker.Hand.HAND_SIZE;

public enum CombinationType {

    NONE {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            return Optional.empty();
        }
    },

    HIGHEST {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            return cards.stream().max(Card::compareTo).map(Combination::of);
        }
    },

    PAIR {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            final List<Card> collect = cards.stream()
                    .filter(hasPair(cards)).sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(PAIR, collect));
        }
    },

    PAIRS {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            final List<Card> pairs = cards.stream()
                    .filter(hasPair(cards)).sorted(Comparator.reverseOrder()).collect(
                            Collectors.toList());

            return pairs.size() < 4 ? Optional.empty() : Optional.of(Combination.of(PAIRS, pairs));
        }
    },

    TRIPS {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            final List<Card> collect = cards.stream()
                    .filter(hasTrips(cards)).sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(TRIPS, collect));
        }
    },

    STRAIGHT {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            if (cards.size() < HAND_SIZE) {
                return Optional.empty();
            }

            final List<Card> deduplicateValues = StreamEx.of(cards).distinct(Card::value).reverseSorted().limit(HAND_SIZE).collect(Collectors.toList());
            for (int i = 0; i < deduplicateValues.size()-1; i++) {
                if (!deduplicateValues.get(i).value().suite(deduplicateValues.get(i+1).value())) {
                    return Optional.empty();
                }

            }
            return Optional.of(Combination.of(STRAIGHT, deduplicateValues));
        }
    },

    FLUSH {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            final List<Card> sameColorCards = get5CardsOfSameColor(groupByColor(cards));
            if (sameColorCards.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(Combination.of(FLUSH, sameColorCards));
        }

        public List<Card> get5CardsOfSameColor(Map<Color, List<Card>> cardsByColor) {
            return cardsByColor
                    .entrySet().stream().filter(e -> e.getValue().size() >= HAND_SIZE)
                    .flatMap(e -> e.getValue().stream())
                    .limit(HAND_SIZE).collect(Collectors.toList());
        }

        public Map<Color, List<Card>> groupByColor(List<Card> cards) {
            return cards.stream().collect(Collectors.groupingBy(Card::color));
        }
    },

    FULL_HOUSE {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            return TRIPS.in(cards).isPresent() && PAIR.in(cards).isPresent() ? Optional.of(Combination.of(FULL_HOUSE, cards)) : Optional.empty();
        }
    },

    QUADS {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            final List<Card> collect = cards.stream()
                    .filter(hasQuads(cards)).sorted(Comparator.reverseOrder()).limit(4).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(QUADS, collect));
        }
    },

    STRAIGHT_FLUSH {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            return FLUSH.in(cards).isPresent() && STRAIGHT.in(cards).isPresent() ? Optional.of(Combination.of(STRAIGHT_FLUSH, StreamEx.of(cards).distinct(Card::value).limit(HAND_SIZE).collect(Collectors.toList()))) : Optional.empty();
        }
    };

    public static List<CombinationType> desc() {
        return Arrays.stream(values()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public abstract Optional<Combination> in(List<Card> cards);

    private static Predicate<Card> occurenceOfSameCard(List<Card> cards, int i) {
        return card -> cards.stream().filter(card1 -> card.value().equals(card1.value())).count() == i;
    }

    protected static Predicate<Card> hasPair(List<Card> cards) {
        return occurenceOfSameCard(cards, 2);
    }

    protected Predicate<Card> hasTrips(List<Card> cards) {
        return occurenceOfSameCard(cards, 3);
    }

    protected Predicate<Card> hasQuads(List<Card> cards) {
        return occurenceOfSameCard(cards, 4);
    }
}

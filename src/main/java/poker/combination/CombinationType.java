package poker.combination;

import org.assertj.core.util.Lists;
import poker.card.Card;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                    .filter(hasPair(cards)).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(PAIR, collect.subList(0, 2)));
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
                    .filter(hasTrips(cards)).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(TRIPS, collect));
        }
    },

    STRAIGHT {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            if (cards.size() != 5) {
                return Optional.empty();
            }

            List<Card> copy = Lists.newArrayList(cards);
            copy.sort(Comparator.reverseOrder());
            for (int i = 0; i < copy.size() - 1; i++) {
                if (copy.get(i).value().gap(copy.get(i + 1).value()) != 1) {
                    return Optional.empty();
                }
            }
            return Optional.of(Combination.of(STRAIGHT, copy));
        }
    },

    FLUSH {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            if (cards.size() == 5 && cards.stream().collect(Collectors.groupingBy(Card::color)).keySet().size() == 1) {
                return Optional.of(Combination.of(FLUSH, cards));
            }
            return Optional.empty();
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
                    .filter(hasQuads(cards)).collect(Collectors.toList());
            return collect.isEmpty() ? Optional.empty() : Optional.of(Combination.of(QUADS, collect));
        }
    },

    STRAIGHT_FLUSH {
        @Override
        public Optional<Combination> in(List<Card> cards) {
            return FLUSH.in(cards).isPresent() && STRAIGHT.in(cards).isPresent() ? Optional.of(Combination.of(STRAIGHT_FLUSH, cards)) : Optional.empty();
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

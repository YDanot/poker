package poker.card;

public enum Value {
    NONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    private int gapWith(Value value){
        return Math.abs(this.ordinal() - value.ordinal());
    }

    public boolean suite(Value value){
        return gapWith(value) == 1;
    }
}

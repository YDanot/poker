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

    public int gap(Value value){
        return Math.abs(this.ordinal() - value.ordinal());
    }
}

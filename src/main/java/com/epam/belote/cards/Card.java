package com.epam.belote.cards;

public class Card {
    private final CardType type;
    private final CardSuit suit;

    public Card(CardType type, CardSuit suit) {
        this.type = type;
        this.suit = suit;
    }

    public CardType getType() {
        return type;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public boolean equals(Object object) {
        return object != null && ((Card) object).getType().equals(this.type) &&
                ((Card) object).getSuit().equals(this.suit);
    }

    public String toString() {
        return this.type + " " + this.suit;
    }
}

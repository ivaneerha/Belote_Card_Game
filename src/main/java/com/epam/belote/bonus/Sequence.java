package com.epam.belote.bonus;

import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Sequence implements Bonus {
    private final CardSuit suit;
    private final List<CardType> cards;

    public Sequence(CardSuit suit, List<CardType> cards) {
        this.suit = suit;
        this.cards = unmodifiableList(cards);
    }

    public CardSuit getSuit() {
        return suit;
    }

    public List<CardType> getCards() {
        return cards;
    }

    public int getBonus() {
        switch (this.cards.size()) {
            case 3:
                return 20;
            case 4:
                return 50;
            case 5:
            case 6:
            case 7:
            case 8:
                return 100;
            default:
                return 0;
        }
    }

    public String toString() {
        return "Sequence from " + suit + " - " + getBonus();
    }
}

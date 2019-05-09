package com.epam.belote.bonus;

import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * The Sequence bonus is declared when the player has a sequence of three or more cards of the same suit, for example Jack, Queen and King of Spades
 */
public class Sequence implements Bonus {
    private final CardSuit suit;
    private final List<CardType> cards;
    private final int numberOfConsecutiveCards;

    public Sequence(CardSuit suit, List<CardType> cards, int numberOfConsecutiveCards) {
        this.suit = suit;
        this.cards = unmodifiableList(cards);
        this.numberOfConsecutiveCards = numberOfConsecutiveCards;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public List<CardType> getCards() {
        return cards;
    }

    public int getBonus() {

        switch (this.numberOfConsecutiveCards) {
            case 3:
                return 20;
            case 4:
                return 50;
            case 5:
                return 100;
            default:
                throw new UnsupportedOperationException("Incorrect number of consecutive cards!");
        }


        // returns 20 for 3 cards sequence
        // returns 50 for 4 cards sequence
        // return 100 for 5 or more cards sequence
//        throw new UnsupportedOperationException("TODO: Implement");

    }
}

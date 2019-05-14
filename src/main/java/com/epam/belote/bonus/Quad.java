package com.epam.belote.bonus;

import com.epam.belote.cards.CardType;

public class Quad implements Bonus {
    private final CardType cardType;

    public Quad(CardType cardType) {
        this.cardType = cardType;
    }

    private CardType getCardType() {
        return cardType;
    }

    public int getBonus() {

        switch (cardType) {
            case ACE:
            case KING:
            case QUEEN:
            case TEN:
                return 100;
            case NINE:
                return 150;
            case JACK:
                return 200;
            default:
                return 0;
        }
    }

    public String toString() {
        return "Quad from " + getCardType() + " - " + getBonus();
    }

}

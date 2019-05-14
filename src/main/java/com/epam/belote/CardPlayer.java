package com.epam.belote;

import com.epam.belote.Comparators.SortingCartsComparator;
import com.epam.belote.bonus.Bonus;
import com.epam.belote.bonus.Quad;
import com.epam.belote.bonus.Sequence;
import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;
import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;

import java.util.*;

public class CardPlayer implements Player {

    private List<Card> cards;
    private Set<Bonus> bonuses;

    public CardPlayer() {
        this.cards = new ArrayList<>();
        this.bonuses = new HashSet<>();
    }

    public Bid bid() {
        int randomPick = new Random().nextInt(Bid.values().length);
        return Bid.values()[randomPick];
    }

    public Set<Bonus> declareBonus() throws NoCardsException {
        if (this.cards.isEmpty()) {
            throw new NoCardsException("Cards are not dealt!");
        }

        lookForQuads();
        lookForSequences();
        return bonuses;
    }

    private void lookForQuads() {
        List<CardType> metTypes = new ArrayList<>();
        int countOfCards = 1;

        for (int index = 0; index < cards.size() - 1; index++) {
            CardType currentType = cards.get(index).getType();

            if (metTypes.contains(currentType)) {
                continue;
            }

            metTypes.add(currentType);

            if (currentType.equals(CardType.SEVEN) || currentType.equals(CardType.EIGHT)) {
                continue;
            }

            for (int index2 = index + 1; index2 < cards.size(); index2++) {
                if (cards.get(index2).getType().equals(currentType)) {
                    countOfCards++;
                }

                if (countOfCards == 4) {
                    break;
                }
            }

            if (countOfCards == 4) {
                this.bonuses.add(new Quad(currentType));
            }

            countOfCards = 1;
        }
    }

    private void lookForSequences() {
        List<CardSuit> suits = new ArrayList<>();
        CardSuit suit;
        List<CardType> cardsFromOneSuit = new ArrayList<>();

        for (int index = 0; index < cards.size() - 1; index++) {
            suit = cards.get(index).getSuit();
            if (suits.contains(suit)) {
                continue;
            } else {
                suits.add(suit);
            }

            for (int index2 = index; index2 < cards.size(); index2++) {
                Card currentCard = cards.get(index2);
                if (currentCard.getSuit().equals(suit)) {
                    cardsFromOneSuit.add(currentCard.getType());
                }
            }


            lookForSerialNumbers(cardsFromOneSuit, suit);
            cardsFromOneSuit.clear();
        }
    }

    private void lookForSerialNumbers(List<CardType> cards, CardSuit suit) {
        if (cards.size() < 3) {
            return;
        }

        List<CardType> serialCards = new ArrayList<>();

        Collections.sort(cards, new SortingCartsComparator());

        for (int index = 0; index < cards.size() - 1; index++) {
            CardType currentCardType = cards.get(index);
            serialCards.add(cards.get(index));
            int ind = 0;
            for (int index2 = index + 1; index2 < cards.size(); index2++) {
                CardType nextCardType = cards.get(index2);
                ind = index2;
                if (currentCardType.ordinal() + index2 == nextCardType.ordinal()) {
                    serialCards.add(cards.get(index2));
                } else {
                    break;
                }

            }

            index = ind;
            if (serialCards.size() >= 3) {
                bonuses.add(new Sequence(suit, serialCards));
            }
        }
    }

    public void addCard(Card card) throws InvalidInputException {
        if (card == null) {
            throw new InvalidInputException("Can not add null into cards!");
        }

        if (this.cards.contains(card)) {
            throw new InvalidInputException("The player already has that card!");
        }
        this.cards.add(card);
    }

    public Card playCard() throws NoCardsException {
        if (this.cards.isEmpty()) {
            throw new NoCardsException("Cards are not dealt!");
        }

        int randomNumber = new Random().nextInt(cards.size());
        Card card = cards.get(randomNumber);
        this.cards.remove(card);
        return card;
    }

    public Team getTeam() {
        return null;
    }

    public List<Card> getCards() {
        return this.cards;
    }

}

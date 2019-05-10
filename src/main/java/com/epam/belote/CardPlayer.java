package com.epam.belote;

import com.epam.belote.bonus.Bonus;
import com.epam.belote.bonus.Quad;
import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;

import java.util.*;

public class CardPlayer implements Player {

    private List<Card> cards;
    private List<Bonus> bonuses;
    public CardPlayer() {
        this.cards = new ArrayList<Card>();
        this.bonuses = new ArrayList<Bonus>();
    }

    public Bid bid() {
        int randomPick = new Random().nextInt(Bid.values().length);
        return Bid.values()[randomPick];
    }

    public Set<Bonus> declareBonus() {
        lookForQuads();
        return null;
    }

    private void lookForQuads() {
        List<CardType> metTypes = new ArrayList<CardType>();
        int countOfCards = 0;

        for(int index = 0; index < cards.size() - 1; index++) {
           CardType currentType = cards.get(index).getType();

           if(metTypes.contains(currentType)){
               continue;
           }

            metTypes.add(currentType);

            if(currentType.equals(CardType.SEVEN) || currentType.equals(CardType.EIGHT)) {
                continue;
            }

            for(int index2 = index + 1; index2 < cards.size(); index2++) {
                if(cards.get(index2).getType().equals(currentType)) {
                    countOfCards++;
                }

                if(countOfCards == 4) {
                    break;
                }
            }

            if(countOfCards == 4) {
                this.bonuses.add(new Quad(currentType));
            }

            countOfCards = 0;
        }
    }

    private void lookForSequences() {
        List<CardSuit> suits = new ArrayList<CardSuit>();
        CardSuit suit = null;
        List<Card> suitCards = new ArrayList<Card>();

        for(int index = 0; index < cards.size() - 1; index++){
            suit = cards.get(index).getSuit();
            if(suits.contains(suit)){
                continue;
            }

            for(int index2 = index + 1; index2 < cards.size(); index2++) {
                Card currentCard = cards.get(index2);
                if(currentCard.getSuit().equals(suit)){
                    suitCards.add(currentCard);
                }
            }

            lookForSerialNumbers(suitCards);
        }
    }

    private void lookForSerialNumbers(List<Card> cards) {
        if(cards.size() < 3) {
            return;
        }
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card playCard() {
        return null;
    }

    public Team getTeam() {
        return null;
    }

}

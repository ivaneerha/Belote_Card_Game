package com.epam.belote;

import com.epam.belote.bonus.Bonus;
import com.epam.belote.bonus.Quad;
import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;

import java.util.*;

public class CardPlayer implements Player {

    private Bid bid;
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
        CardType currentType = null;
        List<CardType> types = new ArrayList<CardType>();
        int count = 0;

        for(int index = 0; index < cards.size() - 1; index++) {
           currentType = cards.get(index).getType();
           if(types.contains(currentType)){
               continue;
           }

           types.add(currentType);

            if(currentType.equals(CardType.SEVEN) || currentType.equals(CardType.EIGHT)) {
                continue;
            }

            for(int index2 = index + 1; index2 < cards.size(); index2++) {
                if(cards.get(index2).equals(currentType)) {
                    count++;
                }

                if(count == 4) {
                    continue;
                }
            }

            if(count == 4) {
                this.bonuses.add(new Quad(currentType));
            }

            count = 0;
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

    public Card playCard() {
        return null;
    }

    public Team getTeam() {
        return null;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Bid getBid() {
        return this.bid;
    }
}

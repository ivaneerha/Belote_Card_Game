package com.epam.belote;

import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;
import com.epam.belote.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dealer implements CardDealer {

    private List<Player> players;
    private List<Card> deck;

    public Dealer(List<Player> players) {
        this.players = players;
        fillTheDeck();
    }

    private void fillTheDeck(){
        this.deck = new ArrayList<Card>();

        for(CardType type : CardType.values()) {
            for(CardSuit suit : CardSuit.values()) {
                Card card = new Card(type, suit);
                this.deck.add(card);
            }
        }
    }

    public void deal5Cards() {

        for(Player player : this.players) {
            for(int index = 0; index < 5; index++) {
                player.addCard(this.deck.get(new Random().nextInt(deck.size())));
            }
        }
    }

    public void deal3Cards() {

    }


}

package com.epam.belote;

import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;
import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer implements CardDealer {

    private List<Player> players;
    private List<Card> deck;

    public Dealer(List<Player> players) {
        this.players = players;
        this.deck = new ArrayList<>();
    }

    public void fillTheDeck() {
        this.deck = new ArrayList<>();

        for (CardType type : CardType.values()) {
            for (CardSuit suit : CardSuit.values()) {
                Card card = new Card(type, suit);
                this.deck.add(card);
            }
        }
    }

    public void deal5Cards() throws InvalidInputException, NoCardsException {
        if (this.deck.isEmpty()) {
            throw new NoCardsException("The deck is empty!");
        }

        for (Player player : this.players) {
            for (int index = 0; index < 5; index++) {
                Card card = this.deck.get(new Random().nextInt(deck.size()));
                player.addCard(card);
                this.deck.remove(card);
            }
        }
    }

    public void deal3Cards() {

    }

    public int getNumOfCardsInTheDeck() {
        return this.deck.size();
    }

}

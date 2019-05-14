package com.epam.belote;

import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;
import com.epam.belote.exceptions.NotEnoughPlayersException;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Dealer dealer;
    private List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer(players);
    }

    public void dealFirstFiveCards() throws InvalidInputException, NotEnoughPlayersException, NoCardsException {
        if (this.players.size() < 4) {
            throw new NotEnoughPlayersException("Player seats are not filled yet!");
        }
        try {
            this.dealer.deal5Cards();
        } catch (NoCardsException e) {
            this.dealer.fillTheDeck();
            this.dealer.deal5Cards();
        }
    }


    public int getAllDealtCards() {
        int numCards = 0;

        for (Player player : players) {
            numCards += player.getCards().size();
        }

        return numCards;
    }

    public Game(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) throws InvalidInputException {
        if (this.players.size() < 4) {
            this.players.add(player);
        } else {
            throw new InvalidInputException("All seats are taken!");
        }
    }

    public Bid bidding() throws InvalidInputException {
        Bid lastBid = Bid.PASS;

        if (validatePlayersSize()) {
            throw new InvalidInputException("Player seats are not filled yet!");
        }

        for (Player player : this.players) {
            Bid playerBid = player.bid();
            lastBid = bidCheck(playerBid, lastBid);
        }

        return lastBid;
    }

    private boolean validatePlayersSize() {
        return players.size() < 4;
    }

    private Bid bidCheck(Bid playerBid, Bid lastBid) {
        if (playerBid.ordinal() >= lastBid.ordinal()) {
            return playerBid;
        }
        return lastBid;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}

package com.epam.belote;

import com.epam.belote.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Dealer dealer;
    private List<Player> players;
    private Bid currentBid;

    public Game() {
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) throws InvalidInputException {
        if(this.players.size() < 4) {
            this.players.add(player);
        } else {
            throw new InvalidInputException("Player seats are filled!");
        }
    }

    public void bidding() throws Exception {
        if(players.size() < 4) {
            throw new Exception("Player seats are not filled yet!");
        }

        for(Player player : this.players) {
            Bid playerBid = player.bid();
            boolean isBidOk = checkBid(playerBid);
            while (!isBidOk) {
                playerBid = player.bid();
                isBidOk = checkBid(playerBid);
            }
        }
    }

    private boolean checkBid(Bid bid) {
        if(currentBid == null || currentBid.equals(Bid.PASS)) {
            currentBid = bid;
            return true;
        } else {
            return bid.ordinal() <= currentBid.ordinal();
        }
    }
}

package com.epam.belote;

import com.epam.belote.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements CardDealer {

    private List<Player> players;

    public Dealer() {
        this.players = new ArrayList<Player>();
    }

    public void deal5Cards() throws Exception {
        if(players.size() < 4) {
            throw new Exception("Player seats are not filled yet!");
        }
    }

    public void deal3Cards() {

    }

    public void addPlayer(Player player) throws InvalidInputException {
        if(this.players.size() < 4) {
            this.players.add(player);
        } else {
            throw new InvalidInputException("Player seats are filled!");
        }
    }
}

package com.epam.belote;

import com.epam.belote.exceptions.InvalidInputException;

public class Team {

    private Player playerOne;
    private Player playerTwo;
    private int points;

    public Team(Player playerOne, Player playerTwo) throws InvalidInputException {
        if (playerOne == null || playerTwo == null) {
            throw new InvalidInputException("Players can not be null!");
        }

        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void increasepoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }
}

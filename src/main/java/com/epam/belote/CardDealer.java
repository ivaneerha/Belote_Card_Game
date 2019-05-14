package com.epam.belote;

import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;

public interface CardDealer {

    void deal5Cards() throws InvalidInputException, NoCardsException;

    void deal3Cards();

    void fillTheDeck();
}

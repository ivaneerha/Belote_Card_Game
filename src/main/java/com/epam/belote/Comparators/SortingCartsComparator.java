package com.epam.belote.Comparators;

import com.epam.belote.cards.CardType;

import java.util.Comparator;

public class SortingCartsComparator implements Comparator<CardType> {

    public int compare(CardType card1, CardType card2) {
        return card1.ordinal() - card2.ordinal();
    }
}

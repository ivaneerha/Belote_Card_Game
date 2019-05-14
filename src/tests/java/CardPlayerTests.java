import com.epam.belote.Bid;
import com.epam.belote.CardPlayer;
import com.epam.belote.Player;
import com.epam.belote.bonus.Bonus;
import com.epam.belote.cards.Card;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;
import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardPlayerTests {

    private Player player;

    @Before
    public void setup() {
        player = new CardPlayer();
    }

    @Test
    public void bid() {
        Bid bid = player.bid();

        assertNotNull(bid);
    }

    @Test(expected = NoCardsException.class)
    public void declareBonusesWithoutCards() throws NoCardsException {
        player.declareBonus();
    }

    @Test
    public void declareSequencesWithDealtCards() throws NoCardsException, InvalidInputException {
        player.addCard(new Card(CardType.JACK, CardSuit.SPADES));
        player.addCard(new Card(CardType.QUEEN, CardSuit.SPADES));
        player.addCard(new Card(CardType.KING, CardSuit.SPADES));
        player.addCard(new Card(CardType.ACE, CardSuit.SPADES));
        player.addCard(new Card(CardType.TEN, CardSuit.SPADES));

        int points = 0;
        Set<Bonus> bonuses = player.declareBonus();
        for (Bonus bonus : bonuses) {
            points += bonus.getBonus();
        }

        Assert.assertEquals(100, points);
    }

    @Test
    public void declareQuads() throws InvalidInputException, NoCardsException {
        player.addCard(new Card(CardType.ACE, CardSuit.SPADES));
        player.addCard(new Card(CardType.ACE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.ACE, CardSuit.DIAMONDS));
        player.addCard(new Card(CardType.ACE, CardSuit.HEARTS));

        int points = 0;
        Set<Bonus> bonuses = player.declareBonus();
        for (Bonus bonus : bonuses) {
            points += bonus.getBonus();
        }

        Assert.assertEquals(100, points);
    }

    @Test
    public void declareMoreBonuses() throws InvalidInputException, NoCardsException {
        player.addCard(new Card(CardType.ACE, CardSuit.SPADES));
        player.addCard(new Card(CardType.ACE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.ACE, CardSuit.DIAMONDS));
        player.addCard(new Card(CardType.ACE, CardSuit.HEARTS));

        player.addCard(new Card(CardType.KING, CardSuit.SPADES));
        player.addCard(new Card(CardType.KING, CardSuit.CLUBS));
        player.addCard(new Card(CardType.KING, CardSuit.DIAMONDS));
        player.addCard(new Card(CardType.KING, CardSuit.HEARTS));

        player.addCard(new Card(CardType.SEVEN, CardSuit.CLUBS));
        player.addCard(new Card(CardType.EIGHT, CardSuit.CLUBS));
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.TEN, CardSuit.CLUBS));

        int points = 0;
        Set<Bonus> bonuses = player.declareBonus();
        for (Bonus bonus : bonuses) {
            points += bonus.getBonus();
        }

        Assert.assertEquals(250, points);
    }

    @Test
    public void declareInvalidQuads() throws InvalidInputException, NoCardsException {
        player.addCard(new Card(CardType.SEVEN, CardSuit.SPADES));
        player.addCard(new Card(CardType.SEVEN, CardSuit.CLUBS));
        player.addCard(new Card(CardType.SEVEN, CardSuit.DIAMONDS));
        player.addCard(new Card(CardType.SEVEN, CardSuit.HEARTS));

        int points = 0;
        Set<Bonus> bonuses = player.declareBonus();
        for (Bonus bonus : bonuses) {
            points += bonus.getBonus();
        }

        Assert.assertEquals(0, points);
    }

    @Test(expected = InvalidInputException.class)
    public void addSameCardIntoPlayersCards() throws InvalidInputException {
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
    }

    @Test(expected = InvalidInputException.class)
    public void addNullIntoPlayersCards() throws InvalidInputException {
        player.addCard(null);
    }

    @Test
    public void addOneCardIntoPlayersCards() throws InvalidInputException {
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
        assertEquals(1, player.getCards().size());
    }

    @Test
    public void playCardAndCardsDecreaseWithOne() throws NoCardsException, InvalidInputException {
        player.addCard(new Card(CardType.NINE, CardSuit.CLUBS));
        player.addCard(new Card(CardType.NINE, CardSuit.SPADES));
        int numCardsBefore = player.getCards().size();

        player.playCard();
        int numCardsAfter = player.getCards().size();

        assertEquals(numCardsBefore - 1, numCardsAfter);
    }

    @Test(expected = NoCardsException.class)
    public void playCardWhenThePlayersCardsAreEmpty() throws NoCardsException {
        player.playCard();
    }
}

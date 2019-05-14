import com.epam.belote.Bid;
import com.epam.belote.CardPlayer;
import com.epam.belote.Game;
import com.epam.belote.Player;
import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;
import com.epam.belote.exceptions.NotEnoughPlayersException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameTests {

    private Game game = new Game();

    @Before
    public void setup() {
        game = new Game();
    }

    @Test(expected = NotEnoughPlayersException.class)
    public void dealFiveCardsWithLessThanFourPlayers() throws InvalidInputException, NotEnoughPlayersException, NoCardsException {
        game.dealFirstFiveCards();
    }

    @Test
    public void dealFiveCards() throws InvalidInputException, NotEnoughPlayersException, NoCardsException {
        game.addPlayer(new CardPlayer());
        game.addPlayer(new CardPlayer());
        game.addPlayer(new CardPlayer());
        game.addPlayer(new CardPlayer());
        game.dealFirstFiveCards();
        int dealtCards = game.getAllDealtCards();

        assertEquals(20, dealtCards);
    }

    @Test(expected = InvalidInputException.class)
    public void addingMoreThanAllowedPlayers() throws InvalidInputException {
        for (int index = 0; index < 6; index++) {
            game.addPlayer(new CardPlayer());
        }
    }

    @Test
    public void addingPlayers() throws InvalidInputException {
        game.addPlayer(new CardPlayer());
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void biddingTest() throws Exception {
        CardPlayer cardPlayer = Mockito.mock(CardPlayer.class);
        CardPlayer cardPlayer2 = Mockito.mock(CardPlayer.class);
        CardPlayer cardPlayer3 = Mockito.mock(CardPlayer.class);
        CardPlayer cardPlayer4 = Mockito.mock(CardPlayer.class);

        Mockito.when(cardPlayer.bid()).thenReturn(Bid.PASS);
        Mockito.when(cardPlayer2.bid()).thenReturn(Bid.CLUBS_TRUMP);
        Mockito.when(cardPlayer3.bid()).thenReturn(Bid.ALL_TRUMPS);
        Mockito.when(cardPlayer4.bid()).thenReturn(Bid.DIAMONDS_TRUMP);

        List<Player> list = asList(cardPlayer, cardPlayer2, cardPlayer3, cardPlayer4);

        game = new Game(list);
        Bid actual = game.bidding();

        assertEquals(Bid.ALL_TRUMPS, actual);
    }

    @Test(expected = InvalidInputException.class)
    public void addingLessThanFourPlayers_fail() throws InvalidInputException {
        for (int index = 0; index < 3; index++) {
            game.addPlayer(new CardPlayer());
        }

        game.bidding();
    }
}
import com.epam.belote.CardPlayer;
import com.epam.belote.Dealer;
import com.epam.belote.exceptions.InvalidInputException;
import com.epam.belote.exceptions.NoCardsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class DealerTests {

    private Dealer dealer;

    @Before
    public void setup() {
        dealer = new Dealer(Arrays.asList(new CardPlayer(), new CardPlayer(), new CardPlayer(), new CardPlayer()));
    }

    @Test
    public void fillingTheDeck() {
        dealer.fillTheDeck();
        int numCardsAfter = dealer.getNumOfCardsInTheDeck();

        Assert.assertEquals(32, numCardsAfter);
    }

    @Test
    public void dealFiveCards() throws InvalidInputException, NoCardsException {
        dealer.fillTheDeck();
        int numCardsBefore = dealer.getNumOfCardsInTheDeck();
        dealer.deal5Cards();
        int numCardsAfter = dealer.getNumOfCardsInTheDeck();

        Assert.assertEquals(numCardsBefore - 20, numCardsAfter);
    }
}

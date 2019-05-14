import com.epam.belote.bonus.Quad;
import com.epam.belote.bonus.Sequence;
import com.epam.belote.cards.CardSuit;
import com.epam.belote.cards.CardType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BonusTests {

    @Test
    public void getQuadBonusFromInvalidCardType() {
        Quad quad = new Quad(CardType.SEVEN);
        int bonus = quad.getBonus();

        Assert.assertEquals(0, bonus);
    }

    @Test
    public void getSequenceBonus_ZERO() {
        Sequence sequence = new Sequence(CardSuit.CLUBS, Arrays.asList(CardType.TEN, CardType.JACK));
        int bonus = sequence.getBonus();

        Assert.assertEquals(0, bonus);
    }

    @Test
    public void getSequenceBonus_ONE_HUNDRED() {
        Sequence sequence = new Sequence(CardSuit.CLUBS, Arrays.asList(CardType.SEVEN,
                CardType.EIGHT, CardType.NINE, CardType.TEN, CardType.JACK, CardType.QUEEN));
        int bonus = sequence.getBonus();

        Assert.assertEquals(100, bonus);
    }
}

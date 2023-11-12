package rokas.games.blackjack.Controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import rokas.games.blackjack.Model.Card;
import rokas.games.blackjack.Model.Deck;
import rokas.games.blackjack.Model.Hand;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@ExtendWith(ApplicationExtension.class)
class DeckViewControllerTest {

    private DeckViewController deckViewController;

    @BeforeEach
    void setUp() {
        deckViewController = new DeckViewController();
        deckViewController.newGame();
    }

    @Test
    void testStandCard() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.getDeckSize()).thenReturn(10);
        Hand mockedHand = mock(Hand.class);
        deckViewController.setDeck(mockedDeck);
        deckViewController.setHand(mockedHand);
        deckViewController.setDealerHandCount(1);
        deckViewController.setDealerScore(16);

        deckViewController.standCard();

        assertEquals(2, deckViewController.getDealerHandCount());
        // Add more assertions based on the actual behavior of the standCard() method in your application
    }

    @Test
    void testResetDeckIfNeeded() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.getDeckSize()).thenReturn(0);
        deckViewController.setDeck(mockedDeck);

        deckViewController.resetDeckIfNeeded();

        verify(mockedDeck, times(1)).shuffle();
    }

    @Test
    void testDealAndDisplayCard() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.dealCard()).thenReturn(new Card("Test", "Test"));
        when(mockedDeck.getDeckSize()).thenReturn(10);
        Hand mockedHand = mock(Hand.class);
        deckViewController.setDeck(mockedDeck);
        deckViewController.setHand(mockedHand);
        deckViewController.setDealerHandCount(1);
        deckViewController.setDealerScore(16);

        deckViewController.dealAndDisplayCard();

        assertEquals(2, deckViewController.getDealerHandCount());
        // Add more assertions based on the actual behavior of the dealAndDisplayCard() method in your application
    }

    @Test
    void testHandleWinLoseConditions() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.getDeckSize()).thenReturn(10);
        Hand mockedHand = mock(Hand.class);
        deckViewController.setDeck(mockedDeck);
        deckViewController.setHand(mockedHand);
        deckViewController.setDealerHandCount(1);
        deckViewController.setDealerScore(16);

        deckViewController.handleWinLoseConditions();

        // Add more assertions based on the actual behavior of the handleWinLoseConditions() method in your application
    }

    @Test
    void testHandleWinLoseConditionsAfterSecondCard() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.getDeckSize()).thenReturn(10);
        Hand mockedHand = mock(Hand.class);
        deckViewController.setDeck(mockedDeck);
        deckViewController.setHand(mockedHand);
        deckViewController.setDealerHandCount(1);
        deckViewController.setDealerScore(16);

        deckViewController.handleWinLoseConditionsAfterSecondCard();

        // Add more assertions based on the actual behavior of the handleWinLoseConditionsAfterSecondCard() method in your application
    }

    @Test
    void testDisplayWinCondition() {
        String message = "Test message";
        Image image = new Image("test.png");

        deckViewController.displayWinCondition(message, image);

        assertEquals(message, deckViewController.getWinsText().getText());
        // Add more assertions based on the actual behavior of the displayWinCondition() method in your application
    }

    @Test
    void testCheckAndShowGameOverScreen() {
        Deck mockedDeck = mock(Deck.class);
        when(mockedDeck.getDeckSize()).thenReturn(10);
        Hand mockedHand = mock(Hand.class);
        deckViewController.setDeck(mockedDeck);
        deckViewController.setHand(mockedHand);
        deckViewController.setPlayerMoneyAmount(0);

        deckViewController.checkAndShowGameOverScreen();

        assertTrue(deckViewController.getGameOverText().isVisible());
        // Add more assertions based on the actual behavior of the checkAndShowGameOverScreen() method in your application
    }

    // Add more tests as needed to achieve 80% code coverage

}

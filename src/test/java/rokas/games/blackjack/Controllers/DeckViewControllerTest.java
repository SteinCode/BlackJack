package rokas.games.blackjack.Controllers;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import rokas.games.blackjack.Model.Deck;
import rokas.games.blackjack.Model.Hand;
public class DeckViewControllerTest {


        private DeckViewController deckViewController;
        private Deck deck;
        private Hand hand;

        @Before
        public void setUp() {
            deck = mock(Deck.class);
            hand = mock(Hand.class);
            deckViewController = new DeckViewController();
        }

        @Test
        public void testHitCardShouldResetDeck() {
            when(deck.getDeckSize()).thenReturn(0);
            deckViewController.hitCard();
            verify(deck).shuffle();
        }

        @Test
        public void testHitCardShouldHandleBust() {
            when(deck.getDeckSize()).thenReturn(5); // Assuming deck is not empty
            when(hand.extractFaceValue(anyString(), anyInt())).thenReturn(10); // Assuming current card value
            when(hand.calculateScore(anyInt(), anyInt())).thenReturn(25); // Assuming player's score
            when(deckViewController.checkIfBust(anyInt())).thenReturn(true);
            deckViewController.hitCard();
            // Write assertions for the expected behavior when bust is handled
        }

        @Test
        public void testHitCardShouldHandleMaxScore() {
            when(deck.getDeckSize()).thenReturn(5); // Assuming deck is not empty
            when(hand.extractFaceValue(anyString(), anyInt())).thenReturn(10); // Assuming current card value
            when(hand.calculateScore(anyInt(), anyInt())).thenReturn(21); // Assuming player's score
            when(deckViewController.checkIfMaxScore(anyInt())).thenReturn(true);
            deckViewController.hitCard();
            // Write assertions for the expected behavior when max score is handled
        }

        @Test
        public void testHitCardDefaultCase() {
            when(deck.getDeckSize()).thenReturn(5); // Assuming deck is not empty
            when(hand.extractFaceValue(anyString(), anyInt())).thenReturn(10); // Assuming current card value
            when(hand.calculateScore(anyInt(), anyInt())).thenReturn(15); // Assuming player's score
            deckViewController.hitCard();
            // Write assertions for the default case
        }
    }



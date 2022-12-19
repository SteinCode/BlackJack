package rokas.games.blackjack.UnitTests;

import org.testng.annotations.Test;
import rokas.games.blackjack.Controllers.DeckViewController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    DeckViewController deckViewController = new DeckViewController();
    @Test
    public void testCheckIfBust(){
        int scoreMore = 22;
        int scoreEqual = 21;
        int scoreLess = 20;
        assertTrue(deckViewController.checkIfBust(scoreMore));
        assertFalse(deckViewController.checkIfBust(scoreEqual));
        assertFalse(deckViewController.checkIfBust(scoreLess));
    }

    @Test
    public void testCompareScore(){
        int playerScore = 20;
        int dealerScore = 10;
        assertTrue(deckViewController.compareScores(playerScore, dealerScore));
        playerScore = 10;
        dealerScore = 20;
        assertFalse(deckViewController.compareScores(playerScore, dealerScore));
    }

    @Test
    public void testCheckIfGameOver(){
        int money = 1000;
        assertFalse(deckViewController.checkIfGameOver(money));
        money = 0;
        assertTrue(deckViewController.checkIfGameOver(money));
        money = -10;
        assertTrue(deckViewController.checkIfGameOver(money));
    }



}
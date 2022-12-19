package rokas.games.blackjack.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rokas.games.blackjack.Model.Card;
import rokas.games.blackjack.Model.Deck;
import rokas.games.blackjack.Model.Hand;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;



@Getter
@Setter
@AllArgsConstructor
public class DeckViewController implements Initializable {
    @FXML private Text gameOverText;
    @FXML private Button playButtonRestart;
    @FXML private Button playButtonFromZero;
    @FXML private Text betText;
    @FXML private Text moneyText;
    @FXML private ImageView mainImage;
    @FXML private Button play;
    @FXML private Button playAgainButton;
    @FXML private Text dealerScoreText;
    @FXML private Text playerScoreText;
    @FXML private Text betNotice;
    @FXML private Text playerMoney;
    @FXML private AnchorPane betPane;
    @FXML private Text betAmount;
    @FXML private Button hitButton;
    @FXML private Button standButton;
    @FXML private Button doubleButton;
    @FXML private ImageView cardsLeftImageView;
    @FXML private Text cardsInDeck;
    @FXML private Text displayDealerFaceValue;
    @FXML private Button playButton;
    @FXML private Text winsText;
    @FXML private Text displayPlayerFaceValue;
    @FXML private ImageView dealerCard1;
    @FXML private ImageView dealerCard2;
    @FXML private ImageView dealerCard3;
    @FXML private ImageView dealerCard4;
    @FXML private ImageView dealerCard5;
    @FXML private ImageView dealerCard6;
    @FXML private ImageView dealerCard7;
    @FXML private ImageView dealerCard8;
    @FXML private ImageView dealerCard9;
    @FXML private ImageView dealerCard10;
    @FXML private ImageView dealerCard11;
    @FXML private ImageView playerCard1;
    @FXML private ImageView playerCard2;
    @FXML private ImageView playerCard3;
    @FXML private ImageView playerCard4;
    @FXML private ImageView playerCard5;
    @FXML private ImageView playerCard6;
    @FXML private ImageView playerCard7;
    @FXML private ImageView playerCard8;
    @FXML private ImageView playerCard9;
    @FXML private ImageView playerCard10;
    @FXML private ImageView playerCard11;
    private Deck deck;
    private Hand hand;
    private int playerHandCount = 0;
    private int dealerHandCount = 0;
    private int playerScore = 0;
    private int dealerScore = 0;
    private Card currentCard;
    private Card previousCard;
    private int hiddenCardValue = 0;
    private int playerMoneyAmount = 0;
    private int playerBetAmount = 0;
    private boolean playerLostFlag = false;

    private static int playerStartMoneyAmount = 1000;

    public boolean getPlayerLostFlag() {
        return playerLostFlag;
    }
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerHandCount() {
        return playerHandCount;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Text getBetNotice() {
        return betNotice;
    }

    public DeckViewController() {
        this.deck = new Deck();
    }
    public void gameStart(){
        mainImage.setVisible(false);
        playButtonFromZero.setVisible(false);
        toggleElements(false);
    }
    public void showGameOverScreen(){
        hideEverything();
        gameOverText.setVisible(true);
        mainImage.setVisible(true);
        playButtonRestart.setVisible(true);
        mainImage.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/lostImage.png"));
    }
    public void gameRestart(){
        gameOverText.setVisible(false);
        mainImage.setVisible(false);
        playButtonFromZero.setVisible(false);
        toggleElements(false);
        newGame();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameOverText.setVisible(false);
        playButtonRestart.setVisible(false);
        mainImage.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/startImage.png"));
        deck = new Deck();
        deck.shuffle();
        playButton.setVisible(false);
        playAgainButton.setVisible(false);
        playerMoneyAmount = playerStartMoneyAmount;
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        hideEverything();
        cardsLeftImageView.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/icons8-ace-of-diamonds-50.png"));
    }

    public void newGame(){
        resetGameState();
        mainImage.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/startImage.png"));
        deck = new Deck();
        deck.shuffle();
        cardsLeftImageView.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/icons8-ace-of-diamonds-50.png"));
    }

    public void playAgain() {
        resetGameState();
    }

    public void startGame(){
        hiddenCardValue = 0;
        resetHandState();
        toggleElements(true);
        betNotice.setText(" ");
        roundStart();
    }

    public void roundStart(){
        for (int i = 0; i < 2; i++){
            hitCard();
            standCard();
        }
        if(playerBetAmount <= playerMoneyAmount){
            doubleButton.setVisible(true);
        }
        toggleElements(true);
        resetCardVisibility();
        playButton.setVisible(false);
        winsText.setVisible(false);
        hitButton.setVisible(true);
        standButton.setVisible(true);
    }

    private void resetGameState() {
        playButtonRestart.setVisible(false);
        playButton.setVisible(false);
        playAgainButton.setVisible(false);
        playerBetAmount = 0;
        playerMoneyAmount = 1000;
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        toggleElements(false);
    }

    private void resetHandState() {
        playerScore = 0;
        dealerScore = 0;
        dealerHandCount = 0;
        playerHandCount = 0;
    }

    private void resetCardVisibility() {
        playerCard1.setVisible(true);
        playerCard2.setVisible(true);
        playerCard3.setVisible(false);
        playerCard4.setVisible(false);
        playerCard5.setVisible(false);

        dealerCard1.setVisible(true);
        dealerCard2.setVisible(true);
        dealerCard3.setVisible(false);
        dealerCard4.setVisible(false);
        dealerCard5.setVisible(false);
    }
    public boolean checkIfBust(int score){
        if(score > 21){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkIfMaxScore(int score){
        if(score == 21){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean compareScores(int playerCurrentScore, int dealerCurrentScore){
        if(playerCurrentScore > dealerCurrentScore){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkIfPush(int playerCurrentScore, int dealerCurrentScore){
        if (playerCurrentScore == dealerCurrentScore){
            return true;
        }
        else{
            return false;
        }
    }

    public void hitCard() {
        if (deck.getDeckSize() == 0) {
            deck = new Deck();
            deck.shuffle();
        }
        hand = new Hand();
        currentCard = deck.dealCard();
        int currentCardFaceValue;
        playerHandCount++;

        ImageView playerCard = getPlayerCardImageView(playerHandCount);
        playerCard.setImage(currentCard.getImage());
        playerCard.setVisible(true);

        currentCardFaceValue = hand.extractFaceValue(currentCard.toString(), playerScore);
        playerScore = hand.calculateScore(playerScore, currentCardFaceValue);
        displayPlayerFaceValue.setText(String.valueOf(playerScore));
        cardsInDeck.setText(Integer.toString(deck.getDeckSize()));

        if (checkIfBust(playerScore) && playerHandCount > 2) {
            winsText.setVisible(true);
            winsText.setText("Dealer wins (player busted)");
            playAgainButton.setVisible(true);
            playerLostFlag = true;
            toggleButtons(false);
            if (checkIfGameOver(playerMoneyAmount)) {
                showGameOverScreen();
            }
        } else if (checkIfMaxScore(playerScore) && playerHandCount >= 2) {
            winsText.setVisible(true);
            winsText.setText("Player wins (player got 21)");
            playAgainButton.setVisible(true);
            playerMoneyAmount = playerMoneyAmount + playerBetAmount * 2;
            playerMoney.setText(Integer.toString(playerMoneyAmount));
            playerLostFlag = false;
            toggleButtons(false);
        }
        hand.setPlayerHandCardCount(playerHandCount);
        hand.setPlayerScoreCount(playerScore);
    }

    private ImageView getPlayerCardImageView(int playerHandCount) {
        switch (playerHandCount) {
            case 1: return playerCard1;
            case 2: return playerCard2;
            case 3: return playerCard3;
            case 4: return playerCard4;
            case 5: return playerCard5;
            case 6: return playerCard6;
            case 7: return playerCard7;
            case 8: return playerCard8;
            case 9: return playerCard9;
            case 10: return playerCard10;
            case 11: return playerCard11;
            default: return null;
        }
    }
    private ImageView getDealerCardByIndex(int index) {
        switch (index) {
            case 1:
                return dealerCard1;
            case 2:
                previousCard = currentCard;
                return dealerCard2;
            case 3:
                dealerCard2.setImage(previousCard.getImage());
                return dealerCard3;
            case 4:
                return dealerCard4;
            case 5:
                return dealerCard5;
            case 6:
                return dealerCard6;
            case 7:
                return dealerCard7;
            case 8:
                return dealerCard8;
            case 9:
                return dealerCard9;
            case 10:
                return dealerCard10;
            case 11:
                return dealerCard11;
            default:
                throw new IndexOutOfBoundsException("Invalid dealer card index: " + index);
        }
    }

    public void standCard() {
        if (deck.getDeckSize() == 0) {
            deck = new Deck();
            deck.shuffle();
        }

        hand = new Hand();
        currentCard = deck.dealCard();
        int currentCardFaceValue;
        dealerHandCount++;

        ImageView dealerCard = getDealerCardByIndex(dealerHandCount);
        dealerCard.setImage(currentCard.getImage());
        dealerCard.setVisible(true);
        currentCardFaceValue = hand.extractFaceValue(currentCard.toString(), dealerScore);

        if (dealerHandCount == 2){
            hiddenCardValue = currentCardFaceValue;
        }
        else if (dealerHandCount == 3){
            currentCardFaceValue = currentCardFaceValue + hiddenCardValue;
            dealerScore = hand.calculateScore(dealerScore, currentCardFaceValue);
        }
        else{
            dealerScore = hand.calculateScore(dealerScore, currentCardFaceValue);
        }
        displayDealerFaceValue.setText(String.valueOf(dealerScore));
        hand.setDealerHandCardCount(dealerHandCount);
        hand.setDealerScoreCount(dealerScore);
        cardsInDeck.setText(Integer.toString(deck.getDeckSize()));
        if(checkIfMaxScore(dealerScore+hiddenCardValue) && dealerHandCount == 2){
            winsText.setVisible(true);
            winsText.setText("Dealer wins (dealer got 21)");
            playAgainButton.setVisible(true);
            toggleButtons(false);
            dealerCard2.setImage(previousCard.getImage());
            if(checkIfGameOver(playerMoneyAmount)){
                showGameOverScreen();
            }
        }
        else if(dealerHandCount > 2){
            if(checkIfBust(dealerScore)){
                winsText.setVisible(true);
                winsText.setText("Player wins (dealer busted)");
                playAgainButton.setVisible(true);
                playerMoneyAmount += playerBetAmount*2;
                playerMoney.setText(Integer.toString(playerMoneyAmount));
                toggleButtons(false);
            }
            else if(dealerScore <= 20 && checkIfDealerHitNext(dealerScore)){
                standCard();
            }
            else if(checkIfMaxScore(dealerScore)){
                winsText.setVisible(true);
                winsText.setText("Dealer wins (dealer got 21)");
                playAgainButton.setVisible(true);
                toggleButtons(false);
                if(checkIfGameOver(playerMoneyAmount)){
                    showGameOverScreen();
                }
            }
            else if (compareScores(playerScore, dealerScore)){
                winsText.setVisible(true);
                winsText.setText("Player wins (player score is bigger)");
                playAgainButton.setVisible(true);
                playerMoneyAmount += playerBetAmount*2;
                playerMoney.setText(Integer.toString(playerMoneyAmount));
                toggleButtons(false);
            }
            else if (checkIfPush(playerScore, dealerScore)){
                winsText.setVisible(true);
                winsText.setText("Push!");
                playAgainButton.setVisible(true);
                playerMoneyAmount += playerBetAmount;
                playerMoney.setText(Integer.toString(playerMoneyAmount));
                toggleButtons(false);
            }
            else{
                winsText.setVisible(true);
                winsText.setText("Dealer wins (dealer score is bigger)");
                playAgainButton.setVisible(true);
                toggleButtons(false);
                if(checkIfGameOver(playerMoneyAmount)){
                    showGameOverScreen();
                }
            }
        }

    }

    public void doubleCard() {
        playerBetAmount *= 2;
        playerMoneyAmount -= playerBetAmount;
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        hitCard();
        if (!playerLostFlag) {
            standCard();
        }
    }

    public void betTen(ActionEvent actionEvent) {
        addBet(10);
    }
    public void betTwentyFive(ActionEvent actionEvent) {
        addBet(25);
    }

    public void betFifty() {
        addBet(50);
    }

    public void betHundred() {
        addBet(100);
    }

    public void betTwoHundred() {
        addBet(200);
    }

    public void betAllIn() {
        addBet(playerMoneyAmount);
    }

    public void restartBet() {
        addBet(-1);
    }


    public void addBet(int amount) {
        if (amount > 0 && playerMoneyAmount >= amount) {
            playerBetAmount += amount;
            playerMoneyAmount -= amount;
            betAmount.setText(Integer.toString(playerBetAmount));
            playerMoney.setText(Integer.toString(playerMoneyAmount));
            playButton.setVisible(true);
        } else if (amount <= 0) {
            playerMoneyAmount += playerBetAmount;
            playerBetAmount = 0;
            betAmount.setText(Integer.toString(playerBetAmount));
            playerMoney.setText(Integer.toString(playerMoneyAmount));
            playButton.setVisible(false);
        } else {
            betNotice.setText("Not enough money!");
        }
    }


    public boolean checkIfDealerHitNext(int score) {
        int probability = 0;
        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;
        if (score > 0 && score <= 10) {
            return true;
        } else if (score > 10 && score <= 14) {
            probability = 80;
        } else if (score > 14 && score <= 16) {
            probability = 50;
        } else if (score == 17) {
            probability = 20;
        } else if (score == 18) {
            probability = 10;
        } else if (score == 19) {
            probability = 5;
        } else {
            return false;
        }
        return probability >= randInt;
    }

    public boolean checkIfGameOver(int money){
        if (money <= 0){
            return true;
        }
        else{
            return false;
        }
    }


    public void toggleButtons(boolean toggleFlag){
        standButton.setVisible(toggleFlag);
        hitButton.setVisible(toggleFlag);
        doubleButton.setVisible(toggleFlag);
    }
    public void hideEverything(){
        doubleButton.setVisible(false);
        betPane.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        winsText.setVisible(false);
        playAgainButton.setVisible(false);
        playButtonRestart.setVisible(false);
        dealerScoreText.setVisible(false);
        cardsInDeck.setVisible(false);
        cardsLeftImageView.setVisible(false);
        displayPlayerFaceValue.setVisible(false);
        displayDealerFaceValue.setVisible(false);
        betAmount.setVisible(false);
        betText.setVisible(false);
        moneyText.setVisible(false);

        playerScoreText.setVisible(false);
        playerMoney.setVisible(false);
        playerCard1.setVisible(false);
        playerCard2.setVisible(false);
        playerCard3.setVisible(false);
        playerCard4.setVisible(false);
        playerCard5.setVisible(false);

        dealerCard1.setVisible(false);
        dealerCard2.setVisible(false);
        dealerCard3.setVisible(false);
        dealerCard4.setVisible(false);
        dealerCard5.setVisible(false);
    }

    public void toggleElements(boolean toggleFlag){
        if (toggleFlag){
            betPane.setVisible(false);
            hitButton.setVisible(true);
            standButton.setVisible(true);
            playButton.setVisible(false);
            playAgainButton.setVisible(false);
            cardsInDeck.setVisible(true);
            playerScoreText.setVisible(true);
            dealerScoreText.setVisible(true);
            cardsInDeck.setVisible(true);
            cardsLeftImageView.setVisible(true);
            displayPlayerFaceValue.setVisible(true);
            displayDealerFaceValue.setVisible(true);
        }
        else{
            betPane.setVisible(true);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            doubleButton.setVisible(false);
            winsText.setVisible(false);
            playerScoreText.setVisible(false);
            dealerScoreText.setVisible(false);
            cardsInDeck.setVisible(false);
            cardsLeftImageView.setVisible(false);
            displayPlayerFaceValue.setVisible(false);
            displayDealerFaceValue.setVisible(false);
            betAmount.setVisible(true);
            betText.setVisible(true);
            moneyText.setVisible(true);
            playerMoney.setVisible(true);

            playerCard1.setVisible(false);
            playerCard2.setVisible(false);
            playerCard3.setVisible(false);
            playerCard4.setVisible(false);
            playerCard5.setVisible(false);

            dealerCard1.setVisible(false);
            dealerCard2.setVisible(false);
            dealerCard3.setVisible(false);
            dealerCard4.setVisible(false);
            dealerCard5.setVisible(false);
        }
    }



}

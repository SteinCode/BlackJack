package rokas.games.blackjack.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rokas.games.blackjack.Model.Card;
import rokas.games.blackjack.Model.Deck;
import rokas.games.blackjack.Model.Hand;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

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
        playerMoneyAmount = 1000;
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        hideEverything();
        cardsLeftImageView.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/icons8-ace-of-diamonds-50.png"));
    }
    public void newGame(){
        playButtonRestart.setVisible(false);
        mainImage.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/startImage.png"));
        deck = new Deck();
        deck.shuffle();
        playButton.setVisible(false);
        playAgainButton.setVisible(false);
        playerBetAmount = 0;
        playerMoneyAmount = 1000;
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        toggleElements(false);
        cardsLeftImageView.setImage(new Image("file:src/main/resources/rokas/games/blackjack/Media/icons8-ace-of-diamonds-50.png"));
    }

    public void playAgain() {
        toggleElements(false);
        playAgainButton.setVisible(false);
        playerBetAmount = 0;
        betAmount.setText(Integer.toString(playerBetAmount));
    }
    public void startGame(){
        hiddenCardValue = 0;
        playerScore = 0;
        dealerScore = 0;
        dealerHandCount = 0;
        playerHandCount = 0;
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

        playButton.setVisible(false);
        winsText.setVisible(false);

        hitButton.setVisible(true);
        standButton.setVisible(true);

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

    public void hitCard(){
        if(deck.getDeckSize() == 0){
            deck = new Deck();
            deck.shuffle();
        }
        hand = new Hand();
        currentCard = deck.dealCard();
        int currentCardFaceValue;
        playerHandCount++;
        switch (playerHandCount) {
            case 1 -> {
                playerCard1.setImage(currentCard.getImage());
                playerCard1.setVisible(true);
            }
            case 2 -> {
                playerCard2.setImage(currentCard.getImage());
                playerCard2.setVisible(true);
            }
            case 3 -> {
                playerCard3.setImage(currentCard.getImage());
                playerCard3.setVisible(true);
                doubleButton.setVisible(false);
            }
            case 4 -> {
                playerCard4.setImage(currentCard.getImage());
                playerCard4.setVisible(true);
            }
            case 5 -> {
                playerCard5.setImage(currentCard.getImage());
                playerCard5.setVisible(true);
            }
            case 6 -> {
                playerCard6.setImage(currentCard.getImage());
                playerCard6.setVisible(true);
            }
            case 7 -> {
                playerCard7.setImage(currentCard.getImage());
                playerCard7.setVisible(true);
            }
            case 8 ->{
                playerCard8.setImage(currentCard.getImage());
                playerCard8.setVisible(true);
            }
            case 9 ->{
                playerCard9.setImage(currentCard.getImage());
                playerCard9.setVisible(true);
            }
            case 10 ->{
                playerCard10.setImage(currentCard.getImage());
                playerCard10.setVisible(true);
            }
            case 11 ->{
                playerCard11.setImage(currentCard.getImage());
                playerCard11.setVisible(true);
            }
        }

        currentCardFaceValue = hand.extractFaceValue(currentCard.toString(), playerScore);
        playerScore = hand.calculateScore(playerScore, currentCardFaceValue);
        displayPlayerFaceValue.setText(String.valueOf(playerScore));
        cardsInDeck.setText(Integer.toString(deck.getDeckSize()));
        if(checkIfBust(playerScore) && playerHandCount > 2){
            winsText.setVisible(true);
            winsText.setText("Dealer wins (player busted)");
            playAgainButton.setVisible(true);
            playerLostFlag = true;
            toggleButtons(false);
            if(checkIfGameOver(playerMoneyAmount)){
                showGameOverScreen();
            }
        }
        else if(checkIfMaxScore(playerScore) && playerHandCount >= 2){
            winsText.setVisible(true);
            winsText.setText("Player wins (player got 21)");
            playAgainButton.setVisible(true);
            playerMoneyAmount = playerMoneyAmount + playerBetAmount*2;
            playerMoney.setText(Integer.toString(playerMoneyAmount));
            playerLostFlag = false;
            toggleButtons(false);
        }
        hand.setPlayerHandCardCount(playerHandCount);
        hand.setPlayerScoreCount(playerScore);
    }

    public void standCard() {
        if(deck.getDeckSize() == 0){
            deck = new Deck();
            deck.shuffle();
        }
        hand = new Hand();
        currentCard = deck.dealCard();
        int currentCardFaceValue;
        dealerHandCount++;
        switch (dealerHandCount) {
            case 1 -> {
                dealerCard1.setImage(currentCard.getImage());
                dealerCard1.setVisible(true);
            }
            case 2 -> {
                previousCard = currentCard;
                dealerCard2.setImage(currentCard.getBackOfCardImage());
                dealerCard2.setVisible(true);
            }
            case 3 -> {
                dealerCard3.setImage(currentCard.getImage());
                dealerCard2.setImage(previousCard.getImage());
                dealerCard3.setVisible(true);
            }
            case 4 -> {
                dealerCard4.setImage(currentCard.getImage());
                dealerCard4.setVisible(true);
            }
            case 5 -> {
                dealerCard5.setImage(currentCard.getImage());
                dealerCard5.setVisible(true);
            }
            case 6 -> {
                dealerCard6.setImage(currentCard.getImage());
                dealerCard6.setVisible(true);
            }
            case 7 -> {
                dealerCard7.setImage(currentCard.getImage());
                dealerCard7.setVisible(true);
            }
            case 8 -> {
                dealerCard8.setImage(currentCard.getImage());
                dealerCard8.setVisible(true);
            }
            case 9 ->{
                dealerCard9.setImage(currentCard.getImage());
                dealerCard9.setVisible(true);
            }
            case 10 ->{
                dealerCard10.setImage(currentCard.getImage());
                dealerCard10.setVisible(true);
            }
            case 11 ->{
                dealerCard11.setImage(currentCard.getImage());
                dealerCard11.setVisible(true);
            }
        }
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
        playerBetAmount = playerBetAmount*2;
        playerMoneyAmount = playerMoneyAmount - playerBetAmount;
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        hitCard();
        System.out.println(playerLostFlag);
        if(playerLostFlag == false){
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

    public void toggleButtons(boolean toggleFlag){
        standButton.setVisible(toggleFlag);
        hitButton.setVisible(toggleFlag);
        doubleButton.setVisible(toggleFlag);
    }
    public void addBet(int amount){
        if(playerMoneyAmount >= amount && amount > 0){
            playerBetAmount += amount;
            playerMoneyAmount -= amount;
        }
        else if(playerMoneyAmount < amount){
            betNotice.setText("Not enough money!");
        }
        else if (amount <= -1){
            playerMoneyAmount = playerMoneyAmount + playerBetAmount;
            playerBetAmount = 0;
        }
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
        if(playerBetAmount > 0){
            playButton.setVisible(true);
        }
        else{
            playButton.setVisible(false);
        }
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

    public boolean checkIfDealerHitNext(int score){
    int probability = 0;
    Random rand = new Random();
    int randInt = rand.nextInt(100);
    randInt = randInt+1;
        if(score > 0 && score <= 10){
            System.out.println(score+" Yes");
            return true;
        }
        else if(score > 10 && score <= 14){
            probability = 80;
            if(probability >= randInt){
                System.out.println(score+" Yes");
                return true;
            }
            else{
                System.out.println(score+" No");
                return false;
            }
        }
        else if(score > 14 && score <= 16){
            probability = 50;
            if(probability >= randInt){
                System.out.println(score+" Yes");
                return true;
            }
            else{
                System.out.println(score+" No");
                return false;
            }
        }
        else if(score == 17){
            probability = 20;
            if(probability >= randInt){
                System.out.println(score+" Yes");
                return true;
            }
            else{
                System.out.println(score+" No");
                return false;
            }

        }
        else if(score == 18){
            probability = 10;
            if(probability >= randInt){
                System.out.println(score+" Yes");
                return true;
            }
            else{
                System.out.println(score+" No");
                return false;
            }
        }
        else if(score == 19){
            probability = 5;
            if(probability >= randInt){
                System.out.println(score+" Yes");
                return true;
            }
            else{
                System.out.println(score+" No");
                return false;
            }
        }
        else{
            System.out.println(score+" No");
            return false;
        }

    }
    public void addDelay(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean checkIfGameOver(int money){
        if (money <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void hideEverything(){
        betPane.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        winsText.setVisible(false);
        playAgainButton.setVisible(false);
        playerScoreText.setVisible(false);
        dealerScoreText.setVisible(false);
        cardsInDeck.setVisible(false);
        cardsLeftImageView.setVisible(false);
        displayPlayerFaceValue.setVisible(false);
        displayDealerFaceValue.setVisible(false);
        betAmount.setVisible(false);
        betText.setVisible(false);
        moneyText.setVisible(false);
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



}

package rokas.games.blackjack.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import rokas.games.blackjack.Model.Card;
import rokas.games.blackjack.Model.Deck;
import rokas.games.blackjack.Model.Hand;


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

    public Text getWinsText() {
        return winsText;
    }

    public Text getGameOverText() {
        return gameOverText;
    }

    public void setDealerHandCount(int dealerHandCount) {
        this.dealerHandCount = dealerHandCount;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public void setPlayerMoneyAmount(int playerMoneyAmount) {
        this.playerMoneyAmount = playerMoneyAmount;
    }

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

    public int getDealerHandCount() {
        return dealerHandCount;
    }

    private boolean playerLostFlag = false;

    private final Random randomNumber = new Random();
    private static final int PLAYERSTARTMONEYAMOUNT = 1000;

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
        playerMoneyAmount = PLAYERSTARTMONEYAMOUNT;
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
        return score > 21;
    }

    public boolean checkIfMaxScore(int score){
        return score == 21;
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
        if (playerCard != null) {
            playerCard.setImage(currentCard.getImage());
            playerCard.setVisible(true);
        } else {
            return;
        }

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
        return switch (playerHandCount) {
            case 1 -> playerCard1;
            case 2 -> playerCard2;
            case 3 -> playerCard3;
            case 4 -> playerCard4;
            case 5 -> playerCard5;
            case 6 -> playerCard6;
            case 7 -> playerCard7;
            case 8 -> playerCard8;
            case 9 -> playerCard9;
            case 10 -> playerCard10;
            case 11 -> playerCard11;
            default -> null;
        };
    }
    private ImageView getDealerCardByIndex(int index) {
        return switch (index) {
            case 1 -> dealerCard1;
            case 2 -> {
                previousCard = currentCard;
                yield dealerCard2;
            }
            case 3 -> {
                dealerCard2.setImage(previousCard.getImage());
                yield dealerCard3;
            }
            case 4 -> dealerCard4;
            case 5 -> dealerCard5;
            case 6 -> dealerCard6;
            case 7 -> dealerCard7;
            case 8 -> dealerCard8;
            case 9 -> dealerCard9;
            case 10 -> dealerCard10;
            case 11 -> dealerCard11;
            default -> throw new IndexOutOfBoundsException("Invalid dealer card index: " + index);
        };
    }

    public void standCard() {
        resetDeckIfNeeded();

        dealAndDisplayCard();

        handleWinLoseConditions();
    }

    /**
     * Reset the deck if it's empty.
     */
    void resetDeckIfNeeded() {
        if (this.deck.getDeckSize() == 0) {
            this.deck = new Deck();
            this.deck.shuffle();
        }
    }

    /**
     * Deal a card, display it, and update the dealer's hand count and score.
     */
    void dealAndDisplayCard() {
        this.hand = new Hand();
        this.currentCard = this.deck.dealCard();
        ++this.dealerHandCount;
        ImageView dealerCard = this.getDealerCardByIndex(this.dealerHandCount);
        dealerCard.setImage(this.currentCard.getImage());
        dealerCard.setVisible(true);

        int currentCardFaceValue = this.hand.extractFaceValue(this.currentCard.toString(), this.dealerScore);

        if (this.dealerHandCount == 2) {
            this.hiddenCardValue = currentCardFaceValue;
        } else if (this.dealerHandCount == 3) {
            currentCardFaceValue += this.hiddenCardValue;
        }

        this.dealerScore = this.hand.calculateScore(this.dealerScore, currentCardFaceValue);

        this.displayDealerFaceValue.setText(String.valueOf(this.dealerScore));
        this.hand.setDealerHandCardCount(this.dealerHandCount);
        this.hand.setDealerScoreCount(this.dealerScore);
        this.cardsInDeck.setText(Integer.toString(this.deck.getDeckSize()));
    }

    /**
     * Handle win/lose conditions and display appropriate messages.
     */
    void handleWinLoseConditions() {
        if (this.dealerHandCount == 2 && this.checkIfMaxScore(this.dealerScore + this.hiddenCardValue)) {
            displayWinCondition("Dealer wins (dealer got 21)", this.previousCard.getImage());
            checkAndShowGameOverScreen();
        } else if (this.dealerHandCount > 2) {
            handleWinLoseConditionsAfterSecondCard();
        }
    }

    /**
     * Handle win/lose conditions after the second card is dealt.
     */
    void handleWinLoseConditionsAfterSecondCard() {
        if (this.checkIfBust(this.dealerScore)) {
            displayWinCondition("Player wins (dealer busted)", null);
        } else if (this.dealerScore <= 20 && this.checkIfDealerHitNext(this.dealerScore)) {
            this.standCard();
        } else if (this.checkIfMaxScore(this.dealerScore)) {
            displayWinCondition("Dealer wins (dealer got 21)", null);
            checkAndShowGameOverScreen();
        } else {
            displayWinCondition("Dealer wins (dealer score is bigger)", null);
            checkAndShowGameOverScreen();
        }
    }

    /**
     * Display the win condition and update UI accordingly.
     *
     * @param message   The win condition message to display.
     * @param image     The image to set for the dealer's second card (can be null).
     */
    void displayWinCondition(String message, Image image) {
        this.winsText.setVisible(true);
        this.winsText.setText(message);
        this.playAgainButton.setVisible(true);
        this.toggleButtons(false);

        if (image != null) {
            this.dealerCard2.setImage(image);
        }

        checkAndShowGameOverScreen();
    }

    /**
     * Check if the game is over based on the player's money amount.
     */
    void checkAndShowGameOverScreen() {
        if (this.checkIfGameOver(this.playerMoneyAmount)) {
            this.showGameOverScreen();
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

    public void betTen() {
        adjustBetAmount(10);
    }
    public void betTwentyFive() {
        adjustBetAmount(25);
    }

    public void betFifty() {
        adjustBetAmount(50);
    }

    public void betHundred() {
        adjustBetAmount(100);
    }

    public void betTwoHundred() {
        adjustBetAmount(200);
    }

    public void betAllIn() {
        adjustBetAmount(playerMoneyAmount);
    }

    public void restartBet() {
        adjustBetAmount(-1);
    }


    /**
     * Adjusts the player's bet amount based on the given input.
     * If the input is valid, updates the bet amount, player money, and UI accordingly.
     * If the input is not valid, displays a "Not enough money" notice.
     *
     * @param amount The amount to adjust the player's bet.
     */
    public void adjustBetAmount(int amount) {
        if (isValidBetAmount(amount)) {
            updatePlayerBetAndMoney(amount);
            updateUI();
            showPlayButton();
        } else {
            displayNotEnoughMoneyNotice();
        }
    }

    /**
     * Checks if the given bet amount is valid.
     *
     * @param amount The bet amount to check.
     * @return True if the amount is greater than zero and the player has enough money, otherwise false.
     */
    boolean isValidBetAmount(int amount) {
        return amount > 0 && playerMoneyAmount >= amount;
    }

    /**
     * Updates the player's bet and money amounts based on the given input.
     *
     * @param amount The amount to adjust the player's bet.
     */
    void updatePlayerBetAndMoney(int amount) {
        if (amount <= 0) {
            playerMoneyAmount += playerBetAmount;
            playerBetAmount = 0;
        } else {
            playerBetAmount += amount;
            playerMoneyAmount -= amount;
        }
    }

    /**
     * Updates the UI to reflect the current player's bet and money amounts.
     */
    void updateUI() {
        betAmount.setText(Integer.toString(playerBetAmount));
        playerMoney.setText(Integer.toString(playerMoneyAmount));
    }

    /**
     * Displays a "Not enough money" notice and hides the play button.
     */
    void displayNotEnoughMoneyNotice() {
        betNotice.setText("Not enough money!");
        playButton.setVisible(false);
    }

    /**
     * Sets play button visibility to true
     */
    void showPlayButton() {
        playButton.setVisible(true);
    }

    public boolean checkIfDealerHitNext(int score) {
        int probability = 0;
        int randInt = randomNumber.nextInt(100) + 1;
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
        return money <= 0;
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

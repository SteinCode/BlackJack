package rokas.games.blackjack.Model;

public class Hand {
    private int playerHandCardCount;
    private int dealerHandCardCount;

    private int playerScoreCount;
    private int dealerScoreCount;

//    public Hand(){
//        this.playerHandCardCount = 0;
//    }
    public int getPlayerHandCardCount() {
        return playerHandCardCount;
    }
    public void setPlayerHandCardCount(int playerHandCardCount) {
        this.playerHandCardCount = playerHandCardCount;
    }

    public int getPlayerScoreCount() {
        return playerScoreCount;
    }
    public void setPlayerScoreCount(int playerScoreCount) {
        this.playerScoreCount = playerScoreCount;
    }

    public int getDealerHandCardCount() {
        return dealerHandCardCount;
    }

    public void setDealerHandCardCount(int dealerHandCardCount) {
        this.dealerHandCardCount = dealerHandCardCount;
    }

    public int getDealerScoreCount() {
        return dealerScoreCount;
    }

    public void setDealerScoreCount(int dealerScoreCount) {
        this.dealerScoreCount = dealerScoreCount;
    }

    public int extractFaceValue(String faceValueText, int currentPlayerScore){
        int faceValue;
        char[] chars = faceValueText.toCharArray();
        StringBuilder sb = new StringBuilder();
        String sbToString;
        for(char c : chars){
            if(Character.isDigit(c)){
                sb.append(c);
            }
            else{
                break;
            }
        }
        if(sb.length() > 0){
            sbToString = sb.toString();
            faceValue = Integer.parseInt(sbToString);
        }
        else if(faceValueText.contains("ace")){
            if(currentPlayerScore <= 10){
                faceValue = 11;
            }
            else{
                faceValue = 1;
            }
        }
        else{
            faceValue = 10;
        }
        return faceValue;
    }

    public int calculateScore(int ScoreCount, int faceValue){
        ScoreCount += faceValue;
        return ScoreCount;
    }

}

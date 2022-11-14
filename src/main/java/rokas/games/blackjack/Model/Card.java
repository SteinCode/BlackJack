package rokas.games.blackjack.Model;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

public class Card {
    private String faceName, suit;
    private int faceValue;
    private Image image;
    private Image backOfCardImage;

    public Card(String faceName, String suit){
        setFaceName(faceName);
        setSuit(suit);
        String fileName = faceName + "_of_"+suit+".png";
        image = new Image("file:src/main/resources/rokas/games/blackjack/Media/"+fileName);
        backOfCardImage = new Image("file:src/main/resources/rokas/games/blackjack/Media/backOfCard.png");
    }




    //GETTER
    public String getFaceName() {
        return faceName;
    }
    public String getSuit() {
        return suit;
    }
    public Image getImage() {
        return image;
    }
    public Image getBackOfCardImage() {
        return backOfCardImage;
    }




    //SETERIAI
    public void setFaceName(String faceName) {
        List<String> validFaceNames = getValidFaceNames();
        faceName = faceName.toLowerCase();
        if (validFaceNames.contains(faceName)){
            this.faceName = faceName;
        }
        else{
            throw new IllegalArgumentException("Invalid face name entered. Please enter a valid one. Valid facenames are: " +
                    validFaceNames);
        }

    }
    public void setSuit(String suit) {
        List<String> validSuits = getValidSuits();
        suit = suit.toLowerCase();

        if(validSuits.contains(suit)){
            this.suit = suit;
        }
        else{
            throw new IllegalArgumentException("Invalid suit entered. Please enter a valid one. Valid suits are: " +
                    validSuits);
        }
    }
    public void setBackOfCardImage(Image backOfCardImage) {
        this.backOfCardImage = backOfCardImage;
    }



    //KORTU REIKSMES VALIDACIJA
    public static List<String> getValidFaceNames(){
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");
    }
    public static List<String> getValidSuits(){
        return Arrays.asList("hearts", "diamonds", "spades", "clubs");
    }


    //TOSTRING
    public String toString(){
        return String.format("%s of %s", faceName, suit);
    }

}

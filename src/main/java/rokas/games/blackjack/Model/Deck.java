package rokas.games.blackjack.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> usedCards;

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;

    }
    public Deck(){
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceNames();
        deck = new ArrayList<>();
        for (String suit:suits){
            for (String faceName: faceNames){
                deck.add(new Card(faceName, suit));
            }
        }

    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card dealCard()
    {
        if (deck.size()>0)
            return deck.remove(0);
        else
            return null;
    }

    public int getDeckSize(){
        int deckSize = this.deck.size();
        return deckSize;
    }
}

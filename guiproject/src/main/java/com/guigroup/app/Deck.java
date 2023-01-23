// new changes on this version:
//  - fixed clicker 
//  - generation of easy, medium and hard decks

package com.guigroup.app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Deck {
    private final Map<Integer,Card> deck;
    

    public Deck(int level){
        deck = new HashMap<>();

        if(level == 2){
            // medium level
            generateMediumDeck();
        } else if(level == 3){
            // hard level
            generateHardDeck();
        } else {
            // easy level (default)
            generateEasyDeck();
        }

    }

    private class Card {
        Integer cardNo;
        String filePath;
        Card(Integer cardNo, String filePath){
            this.cardNo = cardNo;
            this.filePath = filePath;
        }
    }

    public ImageIcon IconFactory(Integer cardNo){
        if(!deck.containsKey(cardNo)) {
            System.out.println("IconFactory problem");
            return null;
        }
        try {
            BufferedImage cardImg = ImageIO.read(getClass().getClassLoader().getResource(deck.get(cardNo).filePath));
            // System.out.println("Current Image URL: " + getClass().getClassLoader().getResource(deck.get(cardNo).filePath).toString());
            return new ImageIcon(cardImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void generateEasyDeck(){
        Card card;
        int i = -1;

        // undiscovered image
        card = new Card(i++,"images/ic_help_outline_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        // discovered image
        card = new Card(i++,"images/ic_done_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m2.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m3.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m4.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m5.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m6.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m7.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m8.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m9.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m10.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m11.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m12.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m13.png");
        deck.put(card.cardNo, card);
    }

    private void generateMediumDeck(){
        Card card;
        int i = -1;

        // undiscovered image
        card = new Card(i++,"images/ic_help_outline_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        // discovered image
        card = new Card(i++,"images/ic_done_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m2.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m3.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m4.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m5.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m6.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m7.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m8.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m9.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m10.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m11.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m12.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m13.png");
        deck.put(card.cardNo, card);
    }

    private void generateHardDeck(){
        Card card;
        int i = -1;

        // undiscovered image
        card = new Card(i++,"images/ic_help_outline_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        // discovered image
        card = new Card(i++,"images/ic_done_black_18dp.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m2.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m3.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m4.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m5.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m6.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m7.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m8.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m9.png"); 
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m10.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m11.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m12.png");
        deck.put(card.cardNo, card);        

        card = new Card(i++,"images/m13.png");
        deck.put(card.cardNo, card);        
         
    }
}

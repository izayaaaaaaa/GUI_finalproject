package com.guigroup.app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Deck {
    private final Map<Integer,String> mapeasyDeck, mapmediumDeck, maphardDeck;
    
    public Deck(int level){
        mapeasyDeck = new HashMap<>();
        mapmediumDeck = new HashMap<>();
        maphardDeck = new HashMap<>();

        if(level == 2){
            generateMediumDeck();

        } else if(level == 3){
            generateHardDeck();

        } else {
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

    private void createCard(Integer cardNo, String filePath, Map<Integer,String> deck){
        Card card = new Card(cardNo, filePath);
        deck.put(card.cardNo, card.filePath);
    }

    public ImageIcon getCard(Integer cardNo, Integer deckLevel){
        Map<Integer,String> deck;
        // identify which deck will the card come from
        if(deckLevel == 2){
            deck = mapmediumDeck;
        } else if(deckLevel == 3){
            deck = maphardDeck;
        } else {
            deck = mapeasyDeck;
        }

        try {
            BufferedImage cardImg = ImageIO.read(getClass().getClassLoader().getResource(deck.get(cardNo)));
            // System.out.println("Current Image URL: " + getClass().getClassLoader().getResource(deck.get(cardNo)).toString());
            return new ImageIcon(cardImg);
        } 
        catch (IOException e) {
            if(!deck.containsKey(cardNo)) {
                System.out.println("Card not found");
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    private void generateEasyDeck(){
        int i = -1;
        createCard(i++, "images/qm.png", mapeasyDeck);
        createCard(i++, "images/c.png", mapeasyDeck);
        createCard(i++, "images/m2.png", mapeasyDeck);
        createCard(i++, "images/m3.png", mapeasyDeck);
        createCard(i++, "images/m4.png", mapeasyDeck);
        createCard(i++, "images/m5.png", mapeasyDeck);
        createCard(i++, "images/m6.png", mapeasyDeck); 
        createCard(i++, "images/m7.png", mapeasyDeck);
    }

    private void generateMediumDeck(){
        int i = -1;

        createCard(i++, "images/qm.png", mapmediumDeck);
        createCard(i++, "images/c.png", mapmediumDeck);
        createCard(i++, "images/m2.png", mapmediumDeck);
        createCard(i++, "images/m3.png", mapmediumDeck);
        createCard(i++, "images/m4.png", mapmediumDeck);
        createCard(i++, "images/m5.png", mapmediumDeck);
        createCard(i++, "images/m6.png", mapmediumDeck);
        createCard(i++, "images/m7.png", mapmediumDeck);
        createCard(i++, "images/m8.png", mapmediumDeck);
        createCard(i++, "images/m9.png", mapmediumDeck);
    }
    
    private void generateHardDeck(){
        // generate 12 cards
        int i = -1;
        createCard(i++, "images/qm.png", maphardDeck);
        createCard(i++, "images/c.png", maphardDeck);
        createCard(i++, "images/m2.png", maphardDeck);
        createCard(i++, "images/m3.png", maphardDeck);
        createCard(i++, "images/m4.png", maphardDeck);
        createCard(i++, "images/m5.png", maphardDeck);
        createCard(i++, "images/m6.png", maphardDeck);
        createCard(i++, "images/m7.png", maphardDeck);
        createCard(i++, "images/m8.png", maphardDeck);
        createCard(i++, "images/m9.png", maphardDeck);
        createCard(i++, "images/m10.png", maphardDeck);
        createCard(i++, "images/m11.png", maphardDeck);
        createCard(i++, "images/m12.png", maphardDeck);
    }

}

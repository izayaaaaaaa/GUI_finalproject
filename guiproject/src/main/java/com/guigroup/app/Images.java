package com.guigroup.app;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Images {
    private class Item{
        Integer intCod;
        String strNomeRecurso;
        Item(Integer intCod, String strNomeRecurso){
            this.intCod = intCod;
            this.strNomeRecurso = strNomeRecurso;
        }
    }
    private final Map<Integer,Item> mapa;
    public Images(){
        mapa = new HashMap<>();
        preenche();
    }

    public String getResourceName(Integer intCod){
        return mapa.get(intCod).strNomeRecurso;
    }
    public ImageIcon IconFactory(Integer intCod){
      if(!mapa.containsKey(intCod)) {
          System.out.println("IconFactory problem");
          return null;
      }
      return new ImageIcon(
              getClass()
                      .getClassLoader()
                      .getResource(getResourceName(intCod)));
    }
    private void preenche(){
        Item item;
        int i = -1;

        // undiscovered image
        item = new Item(i++,"images/ic_help_outline_black_18dp.png");
        mapa.put(item.intCod, item);        

        // discovered image
        item = new Item(i++,"images/ic_done_black_18dp.png");
        mapa.put(item.intCod, item);        


        item = new Item(i++,"images/m2.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m3.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m4.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m5.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m6.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m7.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m8.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m9.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m10.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m11.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m12.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/m13.png");
        mapa.put(item.intCod, item);        
         
    }
    
}

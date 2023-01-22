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


        item = new Item(i++,"images/ic_airport_shuttle_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_all_inclusive_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_beach_access_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_business_center_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_casino_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_child_care_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_child_friendly_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_fitness_center_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_free_breakfast_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_hot_tub_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_kitchen_black_18dp.png");
        mapa.put(item.intCod, item);        

        item = new Item(i++,"images/ic_pool_black_18dp.png");
        mapa.put(item.intCod, item);        
    }
    
}

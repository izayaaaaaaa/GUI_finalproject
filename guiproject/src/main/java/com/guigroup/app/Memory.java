// no clicks counter!!
// start -> new game -> solve (cheat/bug)
package com.guigroup.app;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
// import java.util.logging.Level;
// import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.Timer;

public class Memory {
    // ================================================ VARIABLE INITIALIZATION ================================================

    private JFrame frame;
    
    private Deck gameDeck;

    ArrayList<Integer> listShuffle;
    private List<buttonCard> listCards;

    Integer numCorrectPairs;

    private JMenuBar menuBar;
    private JMenu levelsMenu, optionsMenu;
    private JMenuItem easyItem, mediumItem, hardItem, aboutItem, exitItem;
    
    private JPanel panelTitle, panelGrid, panelControl;
    private JButton buttonNew, buttonSolve, buttonStart;

    private JLabel labelTimer;
    private Timer timer, timer2;
    private int timeLeft; // in secs
    private boolean gameTimerStart = false; 

    private buttonCard previousCard;

    private int round, level, gridRow, gridCol, numCards;
    
    
    // ================================================ COMPILE RUN THROUGH ================================================
    Memory(){
        level = 1;
        round = 0;
        gameDeck = new Deck(level);
        numCorrectPairs = 0;
    }

    public void createGUI(){
        frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        createMenuBar(); 
        createPanelTitle();
        createPanelControl();
        setupLevel(6, 4, 3, level);
        createPanelGrid();
        
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);
    }

    private void createMenuBar(){
        // Menu
        menuBar = new JMenuBar();
        
        levelsMenu = new JMenu("Levels");
        easyItem = new JMenuItem("Easy");
        mediumItem = new JMenuItem("Medium");
        hardItem = new JMenuItem("Hard");

        optionsMenu = new JMenu("Options");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");

        levelsMenu.add(easyItem);
        levelsMenu.add(mediumItem);
        levelsMenu.add(hardItem);
        
        optionsMenu.add(aboutItem);
        optionsMenu.add(exitItem);
        
        menuBar.add(levelsMenu);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);

        easyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameDeck = new Deck(1);
                

            }
        });
        mediumItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameDeck = new Deck(2);
                level = 2;
                System.out.println("Deck Level " + level);
            }
        });
        hardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameDeck = new Deck(3);
                level = 3;
                System.out.println("Deck Level " + level);
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("About");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }
        });
    }

    private void createPanelTitle(){
        // Title
        labelTimer = new JLabel("Countdown:" + timeLeft);
        enlargeFont(labelTimer, 2);
        
        panelTitle = new JPanel(new GridLayout());
        panelTitle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelTitle.add(labelTimer);

        frame.add(panelTitle,BorderLayout.NORTH);
    }

    private void createPanelControl(){
        // Controls
        panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panelControl.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        buttonNew = new JButton("New Game");
        enlargeFont(buttonNew, 2);
        panelControl.add(buttonNew);

        buttonSolve = new JButton("Solve");
        enlargeFont(buttonSolve, 2);
        panelControl.add(buttonSolve);

        buttonStart = new JButton("Start");
        enlargeFont(buttonStart, 2);
        panelControl.add(buttonStart);

        frame.add(panelControl,BorderLayout.SOUTH);

        buttonNew.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                NewGame();
                buttonStart.setVisible(true);
                buttonSolve.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        buttonSolve.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                Solve(false);
                buttonStart.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        buttonStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                Start();
                buttonStart.setVisible(false);
                buttonSolve.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void createPanelGrid(){
        panelGrid = new JPanel(new GridBagLayout());
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));

        listCards = new ArrayList<>();
        previousCard = null;
        int x = 0;

        for(int i = 0; i < gridCol; i++){
            for(int j = 0; j < gridRow; j++){
                // System.out.println("x: " + x);
                Integer numShuffle = (Integer) listShuffle.get(x);
                x++;

                buttonCard currentCard = new buttonCard(numShuffle);
                // System.out.println("listShuffle: " + listShuffle.get(x));
                currentCard.setIcon(gameDeck.getCard(-1, level));
                
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                
                panelGrid.add(currentCard, c);
                listCards.add(currentCard);

                currentCard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(currentCard.cardNo == 0){ // if done card, do nothing
                            return;
                        }

                        if(currentCard.equals(previousCard)) return; // repeated click, do nothing
                        
                        if(gameTimerStart == true){
                            currentCard.setIcon(gameDeck.getCard(currentCard.cardNo, level));
                                        
                            if(previousCard == null){
                                previousCard = currentCard;
                                return;
                            }

                            if(Objects.equals(currentCard.cardNo, previousCard.cardNo)){

                                currentCard.setIcon(gameDeck.getCard(0, level));
                                currentCard.cardNo = 0;

                                previousCard.setIcon(gameDeck.getCard(0, level));
                                previousCard.cardNo = 0;

                                previousCard = null;
                                numCorrectPairs++;

                                if(numCorrectPairs >= numCards){
                                    Solve(true);
                                }	
                            }
                            else {
                                previousCard.setIcon(gameDeck.getCard(-1, level));
                                previousCard = currentCard;
                            }
                        }
                    }
                });
            }       
        }            
        // }
        // catch(Exception ex){System.out.println("Exception:" + ex);}

        frame.add(panelGrid,BorderLayout.CENTER);
    }

    // ================================================ TIMER FEATURE ================================================

    private void Start(){
        if(gameTimerStart == false){
            showCard();
                
        }
        if(gameTimerStart == true){
            countdown();
            buttonSolve.setVisible(true);
        } 
    }

    private void Solve(Boolean bMostrarCliques){
        timer.stop();
        
        numCorrectPairs = numCards;
        previousCard = null;
        
        for(int i = 0; i < listCards.size();i++){
            buttonCard button = listCards.get(i);
            button.setIcon(gameDeck.getCard((Integer) listShuffle.get(i), level));
            button.cardNo = 0;
            listCards.set(i, button);
        }
        panelGrid.repaint();
    }
    
    private void NewGame(){
        try{
            if(timer2.isRunning()){
                timer2.stop();
                labelTimer.setText("Countdown: 0");
            }
            else if(timer.isRunning()){
                timer.stop();
            }
        } catch(Exception e){
            System.out.println("Exception:"+e);
        }

        gameTimerStart = false;
        Collections.shuffle(listShuffle);
        numCorrectPairs = 0;
        
        previousCard = null;
        
        for(int i = 0; i < listCards.size();i++){
            buttonCard button = listCards.get(i);
            button.cardNo = (Integer) listShuffle.get(i);
            button.setIcon(gameDeck.getCard(-1, level));
            listCards.set(i, button);
        }
        
        panelGrid.repaint();
            
    }
    
    private void showCard(){
        timeLeft= 5;
        timer2 = new Timer(1000, new TimerListener()); // fire every second
        timer2.start();
        
        for(int i = 0; i < listCards.size();i++){
            buttonCard button = listCards.get(i);
            button.setIcon(gameDeck.getCard((Integer) listShuffle.get(i), level));
        }
    }

    private void createShuffledNumbers(){
        if(round != 0)
        {
            listShuffle.clear();
        }
        
        for (int i = 1; i <= numCards; i++) {
            listShuffle.add(i);
            listShuffle.add(i);
        }
        Collections.shuffle(listShuffle);
    }

    private void countdown(){ // Starts the timer
        timeLeft= 11;
        timer = new Timer(1000, new TimerListener()); // fire every second
        timer.start();
    }
    
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println("Time remaining: " + timeLeft + " seconds");  
            timeLeft--;
            labelTimer.setText("Countdown: " + timeLeft);

            if (timeLeft == 0 && gameTimerStart == false) {
                timer2.stop();
                gameTimerStart = true;
                System.out.println("gameTimerStart = true;");
                for(int i = 0; i < listCards.size();i++){
                    buttonCard button = listCards.get(i);
                    button.setIcon(gameDeck.getCard(-1, level));
                }
                Start();
            }
            else if(timeLeft == 0) {
                timer.stop();
                Solve(true);
            }
        }
    }

    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
    
    
    
            
    private void setupLevel(int numCards, int gridCol, int gridRow, int level){
        numCards = this.numCards;
        gridCol = this.gridCol;
        gridRow = this.gridRow;
        level = this.level;

        createShuffledNumbers();
        if(round != 0) {
            if(level == 2) {
                numCards = 20;
                gridCol = 5;
                gridRow = 4;

                panelGrid.repaint();
            }
            else if(level == 3) {
                numCards = 24;
                gridCol = 6;
                gridRow = 4;

                panelGrid.repaint();
            } 
            else {
                numCards = 12;
                gridCol = 4;
                gridRow = 3;

                panelGrid.repaint();
            }
        }

        round++;

    }

    
    
    

    

    private class buttonCard extends JButton{
        Integer cardNo;
        public buttonCard(Integer cardNo){
            this.cardNo = cardNo;
        }
    }
    
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Memory mem = new Memory();
                mem.createGUI();
                
            }
        });
    }
}
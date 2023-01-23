// put a congratulatory when fully solved!
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

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.Timer;

public class Memory {
    private JFrame frame;
    private JPanel panelTitle, panelGrid, panelControl;
    private JButton buttonNew, buttonSolve, buttonStart;

    private buttonGame buttonLastClicked;
    // private Deck easyDeck, mediumDeck, 
    private Deck hardDeck;
    
    private JLabel labelTimer;
    
    Integer intCombined;
    ArrayList<Integer>  listShuffle;

    
    private List<buttonGame> listButtons;

    private Timer timer, timer2;
    private int timeLeft; // in secs
    private boolean gameTimerStart = false; // Cards cannot be flipped if false.

    private JMenuBar menuBar;
    private JMenu levelsMenu, optionsMenu;
    private JMenuItem easyItem, mediumItem, hardItem, aboutItem, exitItem;

    public Memory(){ 
        // easyDeck = new Deck(1);
        // mediumDeck = new Deck(2);
        hardDeck = new Deck(3);

        intCombined = 0;

        listShuffle = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            listShuffle.add(i);
            listShuffle.add(i);
        }
        Collections.shuffle(listShuffle);

    }
    
    private class buttonGame extends JButton{
        Integer iCod;
        public buttonGame(Integer iCod){
            this.iCod = iCod;
        }
    }
    
    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
    
    private void countdown(){ // Starts the timer
        timeLeft= 11;
        timer = new Timer(1000, new TimerListener()); // fire every second
        timer.start();
    }
    
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLeft--;
            labelTimer.setText("Countdown: " + timeLeft);

            if (timeLeft == 0 && gameTimerStart == false) {
                timer2.stop();
                gameTimerStart = true;
                for(int i = 0; i < listButtons.size();i++){
                    buttonGame button = listButtons.get(i);
                    button.setIcon(hardDeck.IconFactory(-1));
                }
                Start();
            }
            else if(timeLeft == 0) {
                timer.stop();
                Solve(true);
            }
        }
    }

    private void showCard(){
        timeLeft= 5;
        timer2 = new Timer(1000, new TimerListener()); // fire every second
        timer2.start();
        for(int i = 0; i < listButtons.size();i++){
            buttonGame button = listButtons.get(i);
            button.setIcon(hardDeck.IconFactory((Integer) listShuffle.get(i)));
        }
    }

    private void Solve(Boolean bMostrarCliques){
        timer.stop();
        intCombined = 12;
        buttonLastClicked = null;
        
        for(int i = 0; i < listButtons.size();i++){
            buttonGame button = listButtons.get(i);
            button.setIcon(hardDeck.IconFactory((Integer) listShuffle.get(i)));
            button.iCod = 0;
            listButtons.set(i, button);
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
        }catch(Exception e){
            System.out.println("Exception:"+e);
        }

        gameTimerStart = false;
        Collections.shuffle(listShuffle);
        intCombined = 0;
        buttonLastClicked = null;
        
        for(int i = 0; i < listButtons.size();i++){
            buttonGame button = listButtons.get(i);
            button.iCod = (Integer) listShuffle.get(i);
            button.setIcon(hardDeck.IconFactory(-1));
            listButtons.set(i, button);
        }
        
        panelGrid.repaint();
            
    }
            
    private void Start(){
        if(gameTimerStart == false){
            showCard();
                
        }
        if(gameTimerStart == true){
            countdown();
            buttonSolve.setVisible(true);
        } 
    }
    
    public void ShowWindow(){
        frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
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
                System.out.println("Easy");
            }
        });
        mediumItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Medium");
            }
        });
        hardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hard");
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
        

        // Title
        labelTimer = new JLabel("Countdown:" + timeLeft);
        enlargeFont(labelTimer, 2);
        
        panelTitle = new JPanel(new GridLayout());
        panelTitle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        panelTitle.add(labelTimer);
        frame.add(panelTitle,BorderLayout.NORTH);
        
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

        // grid principal
        panelGrid = new JPanel(new GridBagLayout());
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));
        // 6 x 4 = 24 
        // 24 have two possibilities of 12
        listButtons = new ArrayList<>();
        buttonLastClicked = null;
        int x = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                Integer intNumSorteado = (Integer) listShuffle.get(x);
                buttonGame buttonItem = new buttonGame(intNumSorteado);
                
                buttonItem.setIcon(hardDeck.IconFactory(-1));
                x++;
                

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                panelGrid.add(buttonItem, c);

                listButtons.add(buttonItem);
                
                buttonItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // done cards aren't clickable or flippable
                        if(buttonItem.iCod == 0){
                            return;
                        }

                        // repeated click will do nothing
                        if(buttonItem.equals(buttonLastClicked)) return;
                        
                        if(gameTimerStart == true){
                            buttonItem.setIcon(hardDeck.IconFactory(buttonItem.iCod));
                                        
                            if(buttonLastClicked == null){
                                buttonLastClicked = buttonItem;
                                return;
                            }

                            // logic for matching pairs
                            if(Objects.equals(buttonItem.iCod, buttonLastClicked.iCod)){

                                buttonItem.setIcon(hardDeck.IconFactory(0));
                                buttonItem.iCod = 0;

                                buttonLastClicked.setIcon(hardDeck.IconFactory(0));
                                buttonLastClicked.iCod = 0;

                                buttonLastClicked = null;
                                intCombined++;
                                if(intCombined >= 12){
                                    Solve(true);
                                }	
                            }
                            else {
                                buttonLastClicked.setIcon(hardDeck.IconFactory(-1));
                                buttonLastClicked = buttonItem;
                            }
                        }
                    }
                });
            }
        }
        frame.add(panelGrid,BorderLayout.CENTER);
        
        
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);
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
                mem.ShowWindow();
                
            }
        });
    }
}
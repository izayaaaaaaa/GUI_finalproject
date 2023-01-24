// the relation of level menuitems and the game items can still be improved

package com.guigroup.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Memory {
    // ================================================ VARIABLE INITIALIZATION ================================================

    private JFrame frame;
    
    private Deck gameDeck;

    private ArrayList<Integer> listShuffle;
    private List<buttonCard> listCards;

    Integer numCorrectPairs;

    private JMenuBar menuBar;
    private JMenu levelsMenu, optionsMenu, gameMenu;
    private JMenuItem easyItem, mediumItem, hardItem, aboutItem, exitItem, newGameItem, solveGameItem, startGameItem;
    
    JPanel panelTitle, panelGrid, panelControl;
    private JButton buttonNew, buttonSolve, buttonStart;

    private JLabel labelTitle, labelTimer;
    private boolean isTimerRunning = false; 
    private Integer timeRemaining;
    private Timer timerTest, showCardsTimer; 
    int showCardsTimeLeft = 4;

    private buttonCard previousCard;

    private int level, gridRow, gridCol, numCards;
       
    // ================================================ COMPILE RUN THROUGH ================================================
    Memory(){
        level = 1;
        gameDeck = new Deck(level);
        numCorrectPairs = 0;
        setupLevel();

    }

    public void createGUI(){
        frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        

        createMenuBar();
        createPanelTitle();
        createPanelControl();
        createPanelGrid();
        
        frame.pack();
        frame.setVisible(true);
    }

    private void createMenuBar(){
        menuBar = new JMenuBar();
        
        gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");
        solveGameItem = new JMenuItem("Solve Game");
        startGameItem = new JMenuItem("Start Game");

        levelsMenu = new JMenu("Levels");
        easyItem = new JMenuItem("Easy");
        mediumItem = new JMenuItem("Medium");
        hardItem = new JMenuItem("Hard");

        optionsMenu = new JMenu("Options");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");

        gameMenu.add(newGameItem);
        gameMenu.add(solveGameItem);
        gameMenu.add(startGameItem);

        levelsMenu.add(easyItem);
        levelsMenu.add(mediumItem);
        levelsMenu.add(hardItem);
        
        optionsMenu.add(aboutItem);
        optionsMenu.add(exitItem);
        
        menuBar.add(gameMenu);
        menuBar.add(levelsMenu);
        menuBar.add(optionsMenu);

        frame.setJMenuBar(menuBar);      

        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {newGameTest();}
        });

        solveGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {solveGameTest();}
        });

        startGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {startGameTest();}
        });

        easyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = 1;
                newGameTest();
            }
        });
        mediumItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = 2;
                newGameTest();
            }

        });
        hardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = 3;
                newGameTest();
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 JOptionPane.showMessageDialog(frame, "Match Monster V.1.01 \nMatch the tiles before the timer runs out!");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });
    }

    private void createPanelTitle(){
        labelTitle = new JLabel("Match Monsters");
        labelTitle.setForeground(Color.white);
        enlargeFont(labelTitle, 3);
        
        labelTimer = new JLabel("Time Left: " + timeRemaining);
        labelTimer.setForeground(Color.white);
        enlargeFont(labelTimer, 2);
        
        panelTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 30));
        panelTitle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelTitle.setBackground(Color.decode("#042243"));
        panelTitle.add(labelTitle);
        panelTitle.add(labelTimer);
        frame.add(panelTitle,BorderLayout.NORTH);
    }

    private void createPanelControl(){   
        panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelControl.setBackground(Color.decode("#042243"));
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
            public void mousePressed(MouseEvent e) {newGameTest();}

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
            public void mousePressed(MouseEvent e) {solveGameTest();}
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
            public void mousePressed(MouseEvent e) {startGameTest();}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void createPanelGrid(){
        panelGrid = new JPanel();
        panelGrid.setBackground(Color.decode("#042243"));
        panelGrid.setLayout(new GridLayout(0,4,20,20));
        panelGrid.setBorder(new EmptyBorder(20, 50, 20, 50));

        listCards = new ArrayList<>();
        previousCard = null;
        int x = 0;

        for(int i = 0; i < gridCol; i++){
            for(int j = 0; j < gridRow; j++){
                Integer numShuffle = listShuffle.get(x);
                x++;

                buttonCard currentCard = new buttonCard(numShuffle);
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
                        if(currentCard.cardNo == 0){return;} // if done card, do nothing

                        if(currentCard.equals(previousCard)) return; // repeated click, do nothing
                        
                        if(isTimerRunning == true){
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
                                    solveGameTest();
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
        
        frame.add(panelGrid,BorderLayout.CENTER);
    }

    private void createShuffledNumbers(){
        listShuffle = new ArrayList<>();
        
        for (int i = 1; i <= numCards; i++) {
            listShuffle.add(i);
            listShuffle.add(i);
        }
        Collections.shuffle(listShuffle);
    }

    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
    
    private void setupLevel(){
        if(level == 2) {
            numCards = 10;
            gridCol = 5;
            gridRow = 4;
            timeRemaining = 20;
        }
        else if(level == 3) {
            numCards = 12;
            gridCol = 6;
            gridRow = 4;
            timeRemaining = 15;
        } 
        else {
            numCards = 6;
            gridCol = 4;
            gridRow = 3;
            timeRemaining = 25;
        }
        createShuffledNumbers();
    }

    private class buttonCard extends JButton{
        Integer cardNo;
        public buttonCard(Integer cardNo){
            this.cardNo = cardNo;
        }
    }

    private void changeGrid(int level) {
        gameDeck = new Deck(level);
        setupLevel();
        frame.remove(panelGrid);
        frame.remove(panelTitle);
        
        createPanelGrid();
        createPanelTitle();

        frame.pack();
        frame.validate();
        frame.repaint();
    }

    public void newGameTest(){
        if(isTimerRunning == true) {
            timerTest.stop();
            isTimerRunning = false;
        }
        
        changeGrid(level);
        startGameItem.setVisible(true);
        solveGameItem.setVisible(true);
        buttonStart.setVisible(true);
        buttonSolve.setVisible(true);
    }

    public void solveGameTest(){
        if (isTimerRunning == true) {
            timerTest.stop();
            isTimerRunning = false;
        }
        
        numCorrectPairs = numCards;
        previousCard = null;

        for(int i = 0; i < listCards.size(); i++) {
            buttonCard cardSolved = listCards.get(i);
            cardSolved.setIcon(gameDeck.getCard(listShuffle.get(i), level));
        }

        solveGameItem.setVisible(false);
        startGameItem.setVisible(false);
        buttonSolve.setVisible(false);
        buttonStart.setVisible(false);
        frame.validate();
        frame.repaint();
    }

    public void startGameTest(){
        if (isTimerRunning == false) {
            showCardsTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show Cards!!

                    for(int i = 0; i < listCards.size(); i++) {
                        buttonCard cardSolved = listCards.get(i);
                        cardSolved.setIcon(gameDeck.getCard(listShuffle.get(i), level));
                    }

                    frame.validate();
                    frame.repaint();

                    // Show Cards Timer
                    showCardsTimeLeft--;
                    labelTimer.setText("Get Ready! " + showCardsTimeLeft);

                    if(showCardsTimeLeft == 0) {
                        showCardsTimer.stop();
                        labelTimer.setText("Game!");
                        showCardsTimeLeft = 4;

                        // Flip the cards back!
                        for(int i = 0; i < listCards.size(); i++) {
                            buttonCard cardSolved = listCards.get(i);
                            cardSolved.setIcon(gameDeck.getCard(-1, level));
                        }

                        frame.validate();
                        frame.repaint();

                        // Real Timer
                        labelTimer.setText("Time Left: " + timeRemaining);
                        timerTest = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                timeRemaining--;
                                labelTimer.setText("Time Left: " + timeRemaining);

                                if(timeRemaining == 0) {
                                    timerTest.stop();
                                    solveGameTest();
                                }
                            }
                        });
                        timerTest.start();
                        isTimerRunning = true; 
                    }
                }
            });
            showCardsTimer.start();
            
            startGameItem.setVisible(false);
            buttonStart.setVisible(false);
        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Memory mem = new Memory();
                mem.createGUI();
                
            }
        });
    }
}
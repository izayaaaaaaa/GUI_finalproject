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
    private final Images imagens;
    
    private JLabel labelTitle, labelTimer;
    
    Integer intQtdOpened;
    Integer intCombined;
    ArrayList<Integer>  listShuffle;

    
    private List<buttonGame> listButtons;

    private Timer timer;
    private int timeLeft = 11; // in secs
    private boolean gameTimerStart = false; // Cards cannot be flipped if false.

    private JMenuBar menuBar;
    private JMenu levelsMenu, optionsMenu;
    private JMenuItem easyItem, mediumItem, hardItem, aboutItem, exitItem;
    
    private class buttonGame extends JButton{
        Integer iCod;
        public buttonGame(Integer iCod){
            this.iCod = iCod;
        }
    }
    
    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
    
    public Memory(){ 
        imagens = new Images();
        intQtdOpened = 0;
        intCombined = 0;

        listShuffle = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            listShuffle.add(i);
            listShuffle.add(i);
        }
        Collections.shuffle(listShuffle);

    }
    
    private void countdown(){ // Starts the timer
        timer = new Timer(1000, new TimerListener()); // fire every second
        timer.start();
    }
    
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("Time remaining: " + timeLeft + " seconds");  
          timeLeft--;
          labelTimer.setText("Countdown: " + timeLeft);

          if (timeLeft == 0) {
            timer.stop();
          }
        }
    }

    private void Solve(Boolean bMostrarCliques){
        if(intQtdOpened == -1) return;
        labelTitle.setText("Number of Clicks: " + 
                (bMostrarCliques? intQtdOpened.toString():"Auto Resolution"));

        intQtdOpened = -1;
        intCombined = 12;
        buttonLastClicked = null;
        
        for(int i = 0; i < listButtons.size();i++){
            buttonGame button = listButtons.get(i);
            button.setIcon(imagens.IconFactory((Integer) listShuffle.get(i)));
            button.iCod = 0;
            listButtons.set(i, button);
        }
        panelGrid.repaint();
    }
    private void NewGame(){
        gameTimerStart = false;
        Collections.shuffle(listShuffle);
        intQtdOpened = 0;
        intCombined = 0;
        labelTitle.setText("Number of Clicks: 0");
        buttonLastClicked = null;
        
        for(int i = 0; i < listButtons.size();i++){
            buttonGame button = listButtons.get(i);
            button.iCod = (Integer) listShuffle.get(i);
            button.setIcon(imagens.IconFactory(-1));
            listButtons.set(i, button);
        }
        panelGrid.repaint();
        
        System.out.println("New game starting...");
        if(timer.isRunning()){
            timer.stop();
        }
    }
        
    private void Start(){
        countdown();
        gameTimerStart = true;
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

        // action listener for each menu item
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
        labelTitle = new JLabel("Number of Clicks: 0");
        enlargeFont(labelTitle, 2);
        labelTimer = new JLabel("Countdown:" + timeLeft);
        enlargeFont(labelTimer, 2);
        
        panelTitle = new JPanel(new GridLayout());
        panelTitle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        panelTitle.add(labelTitle);
        panelTitle.add(labelTimer);
        frame.add(panelTitle,BorderLayout.NORTH);
        
        // Controls
        panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER
                , 50, 0));
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
            public void mouseClicked(MouseEvent e) {
                NewGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        buttonSolve.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
            Solve(false);
          }

          @Override
          public void mousePressed(MouseEvent e) {}

          @Override
          public void mouseReleased(MouseEvent e) {}

          @Override
          public void mouseEntered(MouseEvent e) {}

          @Override
          public void mouseExited(MouseEvent e) {}
        });

        buttonStart.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
            Start();
          }

          @Override
          public void mousePressed(MouseEvent e) {}

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
                
                buttonItem.setIcon(imagens.IconFactory(-1));
                x++;
                

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                panelGrid.add(buttonItem, c);

                // list botoes usado no novo jogo
                listButtons.add(buttonItem);
                
                buttonItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(buttonItem.iCod == 0){
                            return;
                        }
                        // rule
                        // if there was a repeated click on the same button it is not worth...
                        if(buttonItem.equals(buttonLastClicked)) return;

                        labelTitle.setText("Number of Clicks: " + ++intQtdOpened);
                        
                        if(gameTimerStart == true){
                          buttonItem.setIcon(imagens.IconFactory(buttonItem.iCod));
                        
                          if(buttonLastClicked == null){
                              buttonLastClicked = buttonItem;
                              return;
                          }

                          if(Objects.equals(buttonItem.iCod, buttonLastClicked.iCod)){

                              buttonItem.setIcon(imagens.IconFactory(0));
                              buttonItem.iCod = 0;

                              buttonLastClicked.setIcon(imagens.IconFactory(0));
                              buttonLastClicked.iCod = 0;

                              buttonLastClicked = null;
                              intCombined++;
                              if(intCombined >= 12){
                                  Solve(true);
                              }
                                  
                          }
                          else {
                            buttonLastClicked.setIcon(imagens.IconFactory(-1));
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

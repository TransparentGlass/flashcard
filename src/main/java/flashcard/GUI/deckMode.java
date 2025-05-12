package flashcard.GUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import flashcard.deck;

public final class deckMode extends JFrame {
    deck currentDeck;

    public deckMode(deck deck){
        this.currentDeck = deck;
        init(currentDeck);
    }

    void init(deck currentDeck){
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        JTextArea questionsArea = new JTextArea();
        JTextArea answersArea = new JTextArea();




        
    }
}

package flashcard.GUI;

import javax.swing.*;

import flashcard.deck;
import flashcard.flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class testGUI {
    private JFrame frame;
    private JPanel studyPanel;

    public testGUI(deck deck){
        frame = new JFrame("Study mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        

        flashcard current = deck.getHead();

        studyPanel = new JPanel();
        studyPanel.setLayout(new BorderLayout());
        
        //the question
        JLabel questionLabel = new JLabel(current.getQuestion());
        questionLabel.setFont(new Font("Serif", Font.PLAIN, 68));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel answerLabel = new JLabel(current.getAnswer());
        answerLabel.setFont(new Font("Serif", Font.PLAIN, 68));
        answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        answerLabel.setVerticalAlignment(SwingConstants.CENTER);

        

        JButton showAnswerButton = new JButton("Show answer");
        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(Box.createVerticalStrut(200));
        bottomPanel.add(showAnswerButton);
        bottomPanel.setBackground(Color.CYAN);
        showAnswerButton.setVerticalAlignment(SwingConstants.CENTER);

        studyPanel.add(questionLabel, BorderLayout.CENTER);
        studyPanel.add(bottomPanel, BorderLayout.SOUTH);

        showAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                studyPanel.remove(questionLabel);
                studyPanel.add(answerLabel);
            }
        });

       
        
        
        frame.add(studyPanel);
        frame.repaint();
        

        frame.setVisible(true);


        



        
    }
    

    
}

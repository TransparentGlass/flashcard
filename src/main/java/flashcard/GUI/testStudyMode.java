package flashcard.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import flashcard.deck;
import flashcard.flashcard;


public class testStudyMode {
    private final JFrame frame;
    private JPanel studyPanel;
        flashcard current;
        JLabel questionLabel;
        JLabel answerLabel;
    
    public testStudyMode(deck deck){
        frame = new JFrame("Study mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        

        current = deck.getHead();

        studyPanel = new JPanel();
        studyPanel.setLayout(new BorderLayout());

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Nimbus is not available or failed to load, fallback to default
            e.printStackTrace();
        }

        
        //the question
        questionLabel = new JLabel(current.getQuestion());
        questionLabel.setFont(new Font("Serif", Font.PLAIN, 68));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);

        answerLabel = new JLabel(current.getAnswer());
        answerLabel.setFont(new Font("Serif", Font.PLAIN, 68));
        answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        answerLabel.setVerticalAlignment(SwingConstants.CENTER);

        

        JButton showAnswerButton = new JButton("Show answer");
        showAnswerButton.setVerticalAlignment(SwingConstants.CENTER);

        JButton nextQuestionButton = new JButton("Next question");
        nextQuestionButton.setVerticalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(Box.createVerticalStrut(200));
        bottomPanel.add(showAnswerButton);
        studyPanel.add(questionLabel, BorderLayout.CENTER);
        studyPanel.add(bottomPanel, BorderLayout.SOUTH);

        showAnswerButton.addActionListener((ActionEvent e) -> {
            studyPanel.remove(questionLabel);
            studyPanel.add(answerLabel);
            bottomPanel.remove(showAnswerButton);
            bottomPanel.add(nextQuestionButton);
            bottomPanel.revalidate();
            bottomPanel.repaint();
            studyPanel.revalidate();
            studyPanel.repaint();
        });

        nextQuestionButton.addActionListener((ActionEvent e) -> {
            
            if (current == null){
                JOptionPane.showMessageDialog(null, "No more questions available", "End of questions", JOptionPane.ERROR_MESSAGE);
                return;
            }
            current = current.next;
                String question = current.getQuestion();
                String answer = current.getAnswer();
    
    
                studyPanel.remove(answerLabel);
                bottomPanel.remove(nextQuestionButton);
                
                answerLabel = new JLabel(answer);
                answerLabel.setFont(new Font("Serif", Font.PLAIN, 68));
                answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                answerLabel.setVerticalAlignment(SwingConstants.CENTER);
    
                questionLabel = new JLabel(question);
                questionLabel.setFont(new Font("Serif", Font.PLAIN, 68));
                questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                questionLabel.setVerticalAlignment(SwingConstants.CENTER);
    
               
                studyPanel.add(questionLabel, BorderLayout.CENTER);
                bottomPanel.add(showAnswerButton);
                studyPanel.revalidate(); 
                bottomPanel.repaint();
           
            
            

        });

       
        
        
        frame.add(studyPanel);
        frame.revalidate();
        frame.repaint();
        

        frame.setVisible(true);


        



        
    }
    

    
}

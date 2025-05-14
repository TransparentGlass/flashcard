package flashcard.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import flashcard.deck;
import flashcard.flashcard;


public class StudyMode {
    private final JFrame frame;
    private JPanel studyPanel;
        flashcard current;
        JLabel questionLabel;
        JLabel answerLabel;
    
    public StudyMode(deck deck){
        frame = new JFrame("Meowmory: Study mode");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon logoIcon = new ImageIcon ("data/img/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        frame.setIconImage(logoImage);
        

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

        
        //Show Answer Button
        ImageIcon ansIcon = new ImageIcon("data/img/ans.png");
        Image ansImg = ansIcon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon ansShowIcon = new ImageIcon(ansImg);

        JButton showAnswerButton = new JButton(ansShowIcon);
        showAnswerButton.setBorderPainted(false);
        showAnswerButton.setContentAreaFilled(false);
        showAnswerButton.setFocusPainted(false);
        showAnswerButton.setOpaque(false);
        showAnswerButton.setVerticalAlignment(SwingConstants.CENTER);


        //Next question Button
        ImageIcon nextIcon = new ImageIcon("data/img/next.png");
        Image nextImg = nextIcon.getImage().getScaledInstance(330, 100, Image.SCALE_SMOOTH);
        ImageIcon nextQuestIcon = new ImageIcon(nextImg);

        JButton nextQuestionButton = new JButton(nextQuestIcon);
        nextQuestionButton.setBorderPainted(false);
        nextQuestionButton.setContentAreaFilled(false);
        nextQuestionButton.setFocusPainted(false);
        nextQuestionButton.setOpaque(false);
        nextQuestionButton.setVerticalAlignment(SwingConstants.CENTER);


        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(Box.createVerticalStrut(200));
        bottomPanel.add(showAnswerButton);
        bottomPanel.setBackground(new Color(248, 226, 190));
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
            
            current = current.next;
            String question = null;
                try {
                    question = current.getQuestion();
                } catch (java.lang.NullPointerException error) {
                    JOptionPane.showMessageDialog(null, "No more questions available", "End of questions", JOptionPane.ERROR_MESSAGE);
                }
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

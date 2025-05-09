
package flashcard.GUI;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonCC {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flashcard Study Thing");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 

        // BG panel (left side)
        JPanel bg = new JPanel();
        bg.setLayout(null); 
        bg.setBackground(new Color(255, 209, 220)); // light pink
        bg.setBounds(0, 0, 350, 1500);
        frame.add(bg);

        // Load and scale image
        ImageIcon icon = new ImageIcon("data/img/addImageSprite.png");
        Image scaledImg = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        // Create image button
        JButton addbutton = new JButton(scaledIcon);
        addbutton.setBounds(0, 80, 250, 250);
        addbutton.setBorderPainted(false);
        addbutton.setContentAreaFilled(false);
        addbutton.setFocusPainted(false);
        addbutton.setOpaque(false);
        bg.add(addbutton);
/*
        // Delete Deck Button
        JButton delbutton = new JButton("Delete Deck");
        delbutton.setBounds(30, 120, 130, 30);
        delbutton.setBorderPainted(false);
        delbutton.setContentAreaFilled(false);
        delbutton.setFocusPainted(false);
        delbutton.setOpaque(false);
        bg.add(delbutton); 

        // Study Mode! Button
        JButton studbutton = new JButton("STUDY MODE");
        studbutton.setBounds(30, 170, 130, 30);
        studbutton.setBorderPainted(false);
        studbutton.setContentAreaFilled(false);
        studbutton.setFocusPainted(false);
        studbutton.setOpaque(false);
        bg.add(studbutton); 
*/        
        // Add Deck Button Action
        addbutton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame, true);
            dialog.setUndecorated(true);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(frame);
            dialog.setLayout(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(new Color(255, 209, 220));
            panel.setBounds(0, 0, 300, 150); 
            dialog.add(panel);

            JLabel label = new JLabel("Enter deck name:");
            label.setBounds(30, 20, 240, 20);
            panel.add(label);

            JTextField textField = new JTextField();
            textField.setBounds(30, 45, 240, 25);
            panel.add(textField);

            JButton okButton = new JButton("OK");
            okButton.setBounds(60, 90, 80, 30);
            panel.add(okButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(160, 90, 80, 30);
            panel.add(cancelButton);

            okButton.addActionListener(ae -> {
                String deckname = textField.getText().trim();
                if (!deckname.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Deck '" + deckname + "' created!");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Deck name cannot be empty.");
                }
            });

            cancelButton.addActionListener(ae -> dialog.dispose());

            dialog.setVisible(true);
        });
/*
        // Delete Deck Button Action
        delbutton.addActionListener(e -> {
            JDialog popup = new JDialog(frame, "Delete Raaah", true);
            popup.setSize(200, 100);
            popup.setLayout(new FlowLayout());
            popup.add(new JLabel("...Deleted"));
            popup.setLocationRelativeTo(frame);
            popup.setVisible(true);
        });
*/
        frame.setVisible(true);
    }
}

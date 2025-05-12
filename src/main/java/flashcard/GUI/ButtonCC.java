
package flashcard.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
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
        bg.setBackground(new Color(253, 181, 108)); // orange ey
        bg.setBounds(0, 0, 350, 1500);
        frame.add(bg);

        // Load and scale ADD image
        ImageIcon addicon = new ImageIcon("data/img/addImg.png");
        Image scaledaddImg = addicon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon addIcon = new ImageIcon(scaledaddImg);

        // Load and scale DELETE image
        ImageIcon delicon = new ImageIcon("data/img/deleteImg.png");
        Image scaleddelImg = delicon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon delIcon = new ImageIcon(scaleddelImg);

        // Load and scale STUDY MORE image
        ImageIcon studicon = new ImageIcon("data/img/studModeImg.png");
        Image scaledstudImg = studicon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon studIcon = new ImageIcon(scaledstudImg);

        // Load and scale EXIT image
        ImageIcon exiticon = new ImageIcon("data/img/exitImg.png");
        Image scaledexitImg = exiticon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon exitIcon = new ImageIcon(scaledexitImg);

        // Create image button
        JButton addbutton = new JButton(addIcon);
        addbutton.setBounds(0, 80, 250, 250);
        addbutton.setBorderPainted(false);
        addbutton.setContentAreaFilled(false);
        addbutton.setFocusPainted(false);
        addbutton.setOpaque(false);
        bg.add(addbutton);

        // Delete Deck Button
        JButton delbutton = new JButton(delIcon);
        delbutton.setBounds(0, 220, 250, 250);
        delbutton.setBorderPainted(false);
        delbutton.setContentAreaFilled(false);
        delbutton.setFocusPainted(false);
        delbutton.setOpaque(false);
        bg.add(delbutton); 

        // Study Mode! Button
        JButton studbutton = new JButton(studIcon);
        studbutton.setBounds(0, 370, 250, 250);
        studbutton.setBorderPainted(false);
        studbutton.setContentAreaFilled(false);
        studbutton.setFocusPainted(false);
        studbutton.setOpaque(false);
        bg.add(studbutton); 

          // Exit Button
        JButton exitbutton = new JButton(exitIcon);
        exitbutton.setBounds(0, 500, 250, 250);
        exitbutton.setBorderPainted(false);
        exitbutton.setContentAreaFilled(false);
        exitbutton.setFocusPainted(false);
        exitbutton.setOpaque(false);
        bg.add(exitbutton); 
        
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

        // Delete Deck Button Action
        delbutton.addActionListener(e -> {
            JDialog popup = new JDialog(frame, "Delete Raaah", true);
            popup.setSize(200, 100);
            popup.setLayout(new FlowLayout());
            popup.add(new JLabel("...Deleted"));
            popup.setLocationRelativeTo(frame);
            popup.setVisible(true);
        });

        frame.setVisible(true);
    }
}

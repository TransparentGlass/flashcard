
package flashcard.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
    
public class menu {
    ImageIcon newFileIcon;
    JFrame frame;
    public void init() {
        frame = new JFrame("Flashnyan");
        frame.setSize(1000, 800);
        frame.getContentPane().setBackground(new Color(255, 246, 231));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 

        // BG panel (left side)
        JPanel bg = new JPanel();
        bg.setLayout(null); 
        bg.setBackground(new Color(248, 226, 190)); // pastel yellow
        bg.setBounds(0, 0, 350, 1500);
        frame.add(bg);

        // Load and scale ADD image
        ImageIcon addicon = new ImageIcon("data/img/menu sprites/CreateNewDeck.png");
        Image scaledaddImg = addicon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon addIcon = new ImageIcon(scaledaddImg);

        // Load and scale DELETE image
        ImageIcon delicon = new ImageIcon("data/img/menu sprites/DeleteDeck.png");
        Image scaleddelImg = delicon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon delIcon = new ImageIcon(scaleddelImg);

        // Load and scale STUDY MORE image
        ImageIcon studicon = new ImageIcon("data/img/menu sprites/StudyMode.png");
        Image scaledstudImg = studicon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon studIcon = new ImageIcon(scaledstudImg);

        // Load and scale EXIT image
        ImageIcon exiticon = new ImageIcon("data/img/menu sprites/Exit.png");
        Image scaledexitImg = exiticon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon exitIcon = new ImageIcon(scaledexitImg);

        ImageIcon fileIcon = new ImageIcon("data/img/menu sprites/file-sprite.png");
        Image scaledFileIcon = fileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        newFileIcon = new ImageIcon(scaledFileIcon);

        // Create image button
        JButton addbutton = new JButton(addIcon);
        addbutton.setBounds(0, 80, 280, 100);
        addbutton.setBorderPainted(false);
        addbutton.setContentAreaFilled(false);
        addbutton.setFocusPainted(false);
        addbutton.setOpaque(false);
        bg.add(addbutton);

        // Delete Deck Button
        JButton delbutton = new JButton(delIcon);
        delbutton.setBounds(0, 220, 280, 150);
        delbutton.setBorderPainted(false);
        delbutton.setContentAreaFilled(false);
        delbutton.setFocusPainted(false);
        delbutton.setOpaque(false);
        bg.add(delbutton); 

        // Study Mode! Button
        JButton studbutton = new JButton(studIcon);
        studbutton.setBounds(0, 370, 280, 150);
        studbutton.setBorderPainted(false);
        studbutton.setContentAreaFilled(false);
        studbutton.setFocusPainted(false);
        studbutton.setOpaque(false);
        bg.add(studbutton); 

          // Exit Button
        JButton exitbutton = new JButton(exitIcon);
        exitbutton.setBounds(0, 600, 280, 150);
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

        ViewAllDeck();

        frame.setVisible(true);
    }
<<<<<<< HEAD

    void ViewAllDeck(){
        System.out.println("View all deck has ran");
        JPanel deckPanel = new JPanel();
        
        deckPanel.setLayout(new MigLayout("inset 20, debug", "[]10[]10[]10[]10[]"));
        File[] DeckList = getAllDeck();

        for(File file: DeckList){
            JPanel filePanel = new JPanel();
            filePanel.setLayout(new MigLayout("debug", "[]", "[]"));
            JButton fileIcon = new JButton(newFileIcon);
            JLabel fileName = new JLabel(file.getName());

            filePanel.add(fileIcon, "grow, h 40!, w 40!, wrap");
            filePanel.add(fileName);
            
            deckPanel.add(filePanel);
            deckPanel.revalidate();
            deckPanel.repaint();



        }

        frame.add(deckPanel);
        frame.revalidate();
        frame.repaint();

    }

    public File[] getAllDeck(){
        String filePath = "data/decks/";
        File directory = new File(filePath);

       if (!directory.exists() || !directory.isDirectory()) {
        System.err.println("Directory does not exist or is invalid: " + filePath);
        return new File[0]; // Return empty array
        }

        File[] allDecks = directory.listFiles(File::isFile);

        return allDecks;
    }
}
=======
}
>>>>>>> 0823348a476c3edd9df8f52c019371dd2547dae2

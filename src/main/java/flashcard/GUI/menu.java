
package flashcard.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import flashcard.FileManager;
import flashcard.deck;
import net.miginfocom.swing.MigLayout;
    
public class menu {
    private static ImageIcon newFileIcon;
    private static JFrame frame;
    private static JScrollPane scrollPane;
    private static JButton selectedButton = null;
    private static File selectedButtonFile = null;

    public void init() {
        frame = new JFrame("Meowmory");
        frame.setSize(1000, 800);
        frame.getContentPane().setBackground(new Color(255, 246, 231));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setResizable(false);

        ImageIcon logoIcon = new ImageIcon ("data/img/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        frame.setIconImage(logoImage);

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

        // Load and scale EXIT image
        ImageIcon exiticon = new ImageIcon("data/img/menu sprites/Exit.png");
        Image scaledexitImg = exiticon.getImage().getScaledInstance(280, 100, Image.SCALE_SMOOTH);
        ImageIcon exitIcon = new ImageIcon(scaledexitImg);

        ImageIcon fileIcon = new ImageIcon("data/img/menu sprites/file-sprite.png");
        Image scaledFileIcon = fileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        newFileIcon = new ImageIcon(scaledFileIcon);

        // Create image button
        JButton addbutton = new JButton(addIcon);
        addbutton.setBounds(0, 250, 280, 100);
        addbutton.setBorderPainted(false);
        addbutton.setContentAreaFilled(false);
        addbutton.setFocusPainted(false);
        addbutton.setOpaque(false);
        bg.add(addbutton);

        // Delete Deck Button
        JButton delbutton = new JButton(delIcon);
        delbutton.setBounds(0, 380, 280, 150);
        delbutton.setBorderPainted(false);
        delbutton.setContentAreaFilled(false);
        delbutton.setFocusPainted(false);
        delbutton.setOpaque(false);
        bg.add(delbutton); 
        
        deleteDeck(delbutton);

          // Exit Button
        JButton exitbutton = new JButton(exitIcon);
        exitbutton.setBounds(0, 600, 280, 150);
        exitbutton.setBorderPainted(false);
        exitbutton.setContentAreaFilled(false);
        exitbutton.setFocusPainted(false);
        exitbutton.setOpaque(false);
        bg.add(exitbutton); 

        exitbutton.addActionListener(exit ->{
            frame.dispose();
        });
        
        // Add Deck Button Action
        addbutton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame, true);
            dialog.setUndecorated(true);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(frame);
            dialog.setLayout(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(new Color(255, 203, 149));
            panel.setBounds(0, 0, 300, 150); 
            dialog.add(panel);

            JLabel label = new JLabel("Enter Deck Name:");
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
                    FileManager openFile = new FileManager(new deck());
                    openFile.createFile(deckname);
                    dialog.dispose();
                    refreshFrame();
                } else {
                    JOptionPane.showMessageDialog(frame, "Deck name cannot be empty.");
                }
            });
            
            cancelButton.addActionListener(ae -> dialog.dispose());

            dialog.setVisible(true);
        });


        ViewAllDeck();

        frame.revalidate();
        frame.repaint();

        frame.setVisible(true);
    }

    void ViewAllDeck() {
        
        System.out.println("View all deck has ran");

        // Create the main deck panel
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(null); // Null layout
        deckPanel.setBounds(350, 0, 650, 780); // Set location and size of the panel

        scrollPane = new JScrollPane(deckPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBounds(350, 0, 635, 780);

        // Get the list of files (Decks)
        File[] DeckList = getAllDeck();

        int x = 30; // Starting x position
        int y = 0; // Starting y position
        int panelWidth = 130; // Width of each file panel
        int panelHeight = 130; // Height of each file panel
        int gap = 0; // Gap between panels

        Font fileTextFont= null;

        try {
            fileTextFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/font/PixelType.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fileTextFont);
            fileTextFont = fileTextFont.deriveFont(22f);



        } catch (Exception e) {
            System.err.println("Font did not load properly");
        }

        // Iterate through each file and create its corresponding panel
        for (File file : DeckList) {
        
            JPanel filePanel = new JPanel();
            filePanel.setLayout(new MigLayout("inset 20 20 0 20, gap 0", "[grow]", "[center]")); // Null layout for the file panel
            filePanel.setBounds(x, y, panelWidth, panelHeight); // Position each file panel 

            // Create the file icon button
            JButton fileIcon = new JButton(newFileIcon); // Replace "Icon" with your image icon
            fileIcon.setOpaque(true); // Makes the button transparent
            fileIcon.setContentAreaFilled(false); // Removes the content area
            fileIcon.setBorderPainted(false); // Removes the border
            fileIcon.setFocusPainted(false);

            fileIcon.addMouseListener(new MouseAdapter() {
                @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1){
                    if (selectedButton != null && selectedButtonFile != null){
                        selectedButton.getParent().setBackground(null);
                        selectedButtonFile = null;
                        
                    }

                    selectedButton = fileIcon;
                    selectedButtonFile = file;
                    filePanel.setBackground(new Color(255, 209, 220));
                }
                if (e.getClickCount() == 2) { // Double-click detected
                    deck newDeck = new deck();
                    FileManager openFile = new FileManager(newDeck);
                    openFile.loadFile(file.getName());
                    new deckMode(openFile.getDeck(), file.getName());
                }
            }
        });

            // Create the file name label
            JTextArea fileName = new JTextArea(file.getName());
            fileName.setLineWrap(true); // Enable line wrapping
            fileName.setWrapStyleWord(true); // Wrap at word boundaries
            fileName.setEditable(false); // Make it non-editable like a label
            fileName.setOpaque(false); // Make the background transparent
            fileName.setPreferredSize(new Dimension(80, 40)); // Set preferred size

            if (fileTextFont != null){
                fileName.setFont(fileTextFont);
            }

            // Add components to the file panel
            filePanel.add(fileIcon, "wrap");
            filePanel.add(fileName, "align center");

            // Add the file panel to the main deck panel
            deckPanel.add(filePanel);

            // Update x and y for the next panel
            x += panelWidth + gap;
            if (x + panelWidth > 650) { // Assuming 650 is the fixed width of the deck panel
                x = 30; // Reset to starting x position
                y += panelHeight + gap; // Move to the next row
            }

            int totalHeight = y + panelHeight + gap;
            deckPanel.setPreferredSize(new Dimension(650, totalHeight));
            deckPanel.revalidate(); // Revalidate to ensure the JScrollPane updates
        }

        // Add the deck panel to the frame
        frame.add(scrollPane);

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }

    void deleteDeck(JButton deleteButton){
        
        deleteButton.addActionListener(e -> {

            if (selectedButton == null || selectedButtonFile == null){
                JOptionPane.showMessageDialog(null,
                "Press a deck and then delete.", "",JOptionPane.DEFAULT_OPTION);
                return;
            }
            int result = JOptionPane.showConfirmDialog(null,
             "Do you want to delete " + selectedButtonFile.getName() + " ?", "Delete deck", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

            switch (result) {
                case JOptionPane.YES_OPTION:
                    if (selectedButton != null || selectedButtonFile != null){
                    deck newdDeck = new deck();
                    FileManager openFile = new FileManager(newdDeck);
                        if (openFile.deleteFile(selectedButtonFile.getName())){
                            JOptionPane.showMessageDialog(null, "Deleted deck!", "Delete deck", JOptionPane.DEFAULT_OPTION);
                            refreshFrame();
                        };

                    }
                    break;

                case JOptionPane.NO_OPTION:
                    break;

                default:
                    throw new AssertionError();
            }
        });
    }

    public File[] getAllDeck() {
        String filePath = "data/decks/";
        File directory = new File(filePath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Directory does not exist or is invalid: " + filePath);
            return new File[0]; // Return empty array
        }

        File[] allDecks = directory.listFiles(File::isFile);

        if (allDecks != null) {
            // Sort the files by last modified date in descending order
            Arrays.sort(allDecks, (file1, file2) -> Long.compare(file2.lastModified(), file1.lastModified()));
        }

        return allDecks;
}

    public void refreshFrame(){
        frame.dispose();
         // Re-add components or regenerate the layout
        init(); // Assuming this method regenerates the list of decks
        frame.revalidate(); // Recalculate the layout
        frame.repaint(); 
    }
}

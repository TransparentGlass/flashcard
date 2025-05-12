package flashcard.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import flashcard.FileManager;
import flashcard.deck;
import flashcard.flashcard;
import net.miginfocom.swing.MigLayout;

public final class deckMode extends JFrame {
    deck currentDeck;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private int cardCount;
    private int idCount;
    private String FileName;

    public deckMode(deck deck, String FileName){
        this.currentDeck = deck;
        this.FileName = FileName;
        this.cardCount = currentDeck.listLength();
        init(currentDeck);
    }

    void init(deck currentDeck){
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("fillx, insets 20"));

        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

        UtilityUI();

        add(scrollPane);
        
        loadDeckToUI();
        checkForID();

        System.out.println(cardCount);

       
        repaint();
        revalidate();

    }
    
    void CreateFlashcard(flashcard card){
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new MigLayout("fillx, insets 10", "[grow, left] 20 [grow, right]"));

        flashcard currentCard = card;

       //Delete Button
        ImageIcon delIcon = new ImageIcon("data/img/2.png");
        Image delImg = delIcon.getImage().getScaledInstance(100, 35, Image.SCALE_SMOOTH);
        ImageIcon del2Icon = new ImageIcon(delImg);

        JButton deleteFCButton = new JButton(del2Icon);
        deleteFCButton.setBorderPainted(false);
        deleteFCButton.setContentAreaFilled(false);
        deleteFCButton.setFocusPainted(false);
        deleteFCButton.setOpaque(false);

        //Question Text Area
        JTextArea questionsArea = new JTextArea(currentCard.getQuestion());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(148, 120, 113), 3), "Question");
        titledBorder.setTitleFont(new Font("SansSerif", Font.BOLD, 16));

        questionsArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        questionsArea.setBorder(titledBorder);
        questionsArea.setWrapStyleWord(true);
        questionsArea.setLineWrap(true);

        questionsArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                questionsArea.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e){
                questionsArea.select(0, 0);
            }
        });


        //Answer Text Area
        JTextArea answersArea = new JTextArea(currentCard.getAnswer());
        TitledBorder titleddBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(148, 120, 113), 3), "Answer");
        titleddBorder.setTitleFont(new Font("SansSerif", Font.BOLD, 16));

        answersArea.setBorder(titleddBorder);
        answersArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        answersArea.setWrapStyleWord(true);
        answersArea.setLineWrap(true);

        answersArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                answersArea.selectAll();
            }
            
            @Override
            public void focusLost(FocusEvent e){
                answersArea.select(0, 0);
            }
        });


        if ("".equals(currentCard.getQuestion())){
            questionsArea.setText("Type your question here...");
            answersArea.setText("Type your answers here...");
        }

        questionsArea.getDocument().addDocumentListener((SimpleDocumentListener) () -> {
            currentCard.setQuestion(questionsArea.getText());
        });

        answersArea.getDocument().addDocumentListener((SimpleDocumentListener) () -> {
            currentCard.setAnswer(answersArea.getText());
        });
 
       deleteFCButton.addActionListener(ae -> {
            if (currentCard == null) {
                JOptionPane.showMessageDialog(null, "No card selected to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            boolean isDeleted = currentDeck.DeleteCardByID(currentCard.getID());
            if (!isDeleted) {
                JOptionPane.showMessageDialog(null, "Failed to delete the card. Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cardCount = currentDeck.listLength();

            mainPanel.remove(cardPanel);
        
            mainPanel.revalidate();
            mainPanel.repaint();

            JOptionPane.showMessageDialog(null, "Card deleted successfully!", "Delete Card", JOptionPane.INFORMATION_MESSAGE);
        });

        cardPanel.add(questionsArea, "growx, h 15%, align left");
        cardPanel.add(answersArea, "growx, h 15% ,align right, wrap");
        cardPanel.add(deleteFCButton);

        cardPanel.repaint();
        cardPanel.revalidate();
      


        mainPanel.add(cardPanel, "growx, align center, wrap");
        mainPanel.revalidate();
        mainPanel.repaint();

        repaint();
        revalidate();
    }

    void addFlashcard(){
        cardCount++;
        checkForID();

        currentDeck.addCard("", "", idCount);
        flashcard currentCard = currentDeck.getTail();

        CreateFlashcard(currentCard);
       
        
    }

    void loadFlashcards(int id){
        flashcard currentCard = currentDeck.FindFlashcardByID(id);
        CreateFlashcard(currentCard);
    }

    void loadDeckToUI() {
        
        // Check if the current deck is null or uninitialized
        if (currentDeck == null) {
            System.err.println("Error: Deck is not initialized.");
            return;
        }

        // Check if the deck is empty
        if (currentDeck.head == null) {
            System.out.println("The deck is empty. No flashcards to load.");
            return;
        }

        // Traverse the deck and load each flashcard
        flashcard current = currentDeck.getHead();
        int loopCounter = currentDeck.listLength();
        while (current != null) {
            // Add the flashcard to the UI or application
            
            loopCounter--;
            loadFlashcards(current.getID());
            current = current.next;
            if (loopCounter == 0){
                break;
            }
        }

        System.out.println("Deck loaded successfully.");
    }

    
    void UtilityUI(){

        //Save Button
        ImageIcon saveIcon = new ImageIcon("data/img/3.png");
        Image saveImg = saveIcon.getImage().getScaledInstance(100, 35, Image.SCALE_SMOOTH);
        ImageIcon save3Icon = new ImageIcon(saveImg);

        JButton SaveButton = new JButton(save3Icon);
        SaveButton.setBorderPainted(false);
        SaveButton.setContentAreaFilled(false);
        SaveButton.setFocusPainted(false);
        SaveButton.setOpaque(false);

        SaveButton.addActionListener(s -> {
            
            FileManager file = new FileManager(this.currentDeck);
            if (file.saveFile(FileName)){
                System.out.println("File saved successfully");
                JOptionPane.showMessageDialog(null, "File is saved!", "Save file", JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showMessageDialog(null, "File not saved!", "Error save file", JOptionPane.ERROR_MESSAGE);
            }
        });
        //Add Button
        ImageIcon addIcon = new ImageIcon("data/img/1.png");
        Image addImg = addIcon.getImage().getScaledInstance(100, 35, Image.SCALE_SMOOTH);
        ImageIcon add1Icon = new ImageIcon(addImg);

        JButton addFlashcardButton = new JButton(add1Icon);
        addFlashcardButton.setBorderPainted(false);
        addFlashcardButton.setContentAreaFilled(false);
        addFlashcardButton.setFocusPainted(false);
        addFlashcardButton.setOpaque(false);


        addFlashcardButton.addActionListener(ae -> {
            flashcard currentCard = currentDeck.getTail();
            if (currentCard.getQuestion().isEmpty() || currentCard.getAnswer().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Answer and/or question must not be empty before adding a new card",
                    "Error",
                    JOptionPane.ERROR_MESSAGE 
                );
            } else {
                addFlashcard();
            }
        }
        );

        JPanel barPanel = new JPanel();
        barPanel.setLayout(new MigLayout("", "[][][][][][]"));
        barPanel.setBackground(new Color(255, 246, 231));
        

        barPanel.add(SaveButton);
        barPanel.add(addFlashcardButton);
        mainPanel.add(barPanel,  "north");



    }

    void checkForID(){
        if (currentDeck.listID().contains(idCount)){
            int biggestID = 0;
            for(int id: currentDeck.listID() ){
                if (id > biggestID){
                    biggestID = id;
                }
            }

            idCount = biggestID + 1;

        }
    }


}



@FunctionalInterface
interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
    void update();

    @Override
    default void insertUpdate(javax.swing.event.DocumentEvent e) {
        update();
    }

    @Override
    default void removeUpdate(javax.swing.event.DocumentEvent e) {
        update();
    }

    @Override
    default void changedUpdate(javax.swing.event.DocumentEvent e) {
        update();
    }
}
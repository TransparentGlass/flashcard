package flashcard.GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import flashcard.deck;
import flashcard.flashcard;
import net.miginfocom.swing.MigLayout;

public final class deckMode extends JFrame {
    deck currentDeck;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private int cardCount;

    public deckMode(deck deck){
        this.currentDeck = deck;
        this.cardCount = currentDeck.listLength();
        init(currentDeck);
    }

    void init(deck currentDeck){
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("fillx, insets 20, debug"));

        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

        add(scrollPane);
        
        loadDeckToUI();

        System.out.println(cardCount);

       
        repaint();
        revalidate();

    }
    
    void CreateFlashcard(flashcard card){
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new MigLayout("fillx, insets 10, debug", "[grow, left] 20 [grow, right]"));

        flashcard currentCard = card;
        JButton addFlashcardButton = new JButton("Add");
        JButton deleteFCButton = new JButton("Delete");

        JTextArea questionsArea = new JTextArea(currentCard.getQuestion());
        questionsArea.setLineWrap(true);
        questionsArea.setBorder(BorderFactory.createTitledBorder("Question"));

        JTextArea answersArea = new JTextArea(currentCard.getAnswer());
        answersArea.setLineWrap(true);
        answersArea.setBorder(BorderFactory.createTitledBorder("Answer"));


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



        addFlashcardButton.addActionListener(ae -> {
            if (currentCard.getQuestion().isEmpty() || currentCard.getAnswer().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Answer and/or question must not be empty before adding a new card",
                    "Error",
                    JOptionPane.ERROR_MESSAGE 
                );
            } else if (currentDeck.tail.getQuestion().isEmpty() || currentDeck.tail.getAnswer().isEmpty() ){
                 JOptionPane.showMessageDialog(
                    null,
                    "Answer and/or question must not be empty before adding a new card",
                    "Error",
                    JOptionPane.ERROR_MESSAGE 
                );
            } else {
                addFlashcard();

                cardPanel.revalidate();
                cardPanel.repaint(); 
            }
        });

       deleteFCButton.addActionListener(ae -> {
            if (currentCard == null) {
                JOptionPane.showMessageDialog(null, "No card selected to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            boolean isDeleted = currentDeck.DeleteCardByID(currentCard.getCount());
            if (!isDeleted) {
                JOptionPane.showMessageDialog(null, "Failed to delete the card. Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            System.out.println(cardCount);
            cardCount = currentDeck.listLength();

            mainPanel.remove(cardPanel);
        
            mainPanel.revalidate();
            mainPanel.repaint();

            JOptionPane.showMessageDialog(null, "Card deleted successfully!", "Delete Card", JOptionPane.INFORMATION_MESSAGE);
        });

        cardPanel.add(questionsArea, "growx, h 15%, align left");
        cardPanel.add(answersArea, "growx, h 15% ,align right, wrap");
        cardPanel.add(deleteFCButton);
        cardPanel.add(addFlashcardButton, "align right, span, wrap");

        cardPanel.repaint();
        cardPanel.revalidate();
      


        mainPanel.add(cardPanel, "growx, align center, wrap");
        mainPanel.revalidate();
        mainPanel.repaint();

        repaint();
        revalidate();
    }

    void addFlashcard(){
        currentDeck.addCard("", "", cardCount);
        flashcard currentCard = currentDeck.getTail();

        CreateFlashcard(currentCard);
       
        
    }

    void loadFlashcardUI(int id){
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
            loadFlashcardUI(current.getCount());
            current = current.next;
            if (loopCounter == 0){
                break;
            }
        }

        System.out.println("Deck loaded successfully.");
    }

    
    void saveDeckUI(){
        //to be made
        JButton SaveButton = new JButton("Save");

        
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
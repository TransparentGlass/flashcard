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
    private JPanel cardPanel;

    public deckMode(deck deck){
        this.currentDeck = deck;
        cardCount = currentDeck.listLength();
        init(currentDeck);
    }

    void init(deck currentDeck){
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("fillx, insets 30" ));

        cardPanel = new JPanel();
        cardPanel.setLayout(new MigLayout("fillx, insets 30, debug", "[grow, left] 20 [grow, right]"));
        

        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

        add(scrollPane);
        
        loadDeckToUI();
        addFlashcard();

        System.out.println(cardCount);

       
        repaint();
        revalidate();

    }

    void addFlashcard(){
        cardCount += 1;
        currentDeck.addCard("", "", cardCount);

        flashcard currentCard = currentDeck.getTail();
       
        JButton addFlashcardButton = new JButton("Add");
        JButton deleteFCButton = new JButton("Delete");

        JTextArea questionsArea = new JTextArea("Type your questions here...");
        questionsArea.setLineWrap(true);
        questionsArea.setBorder(BorderFactory.createTitledBorder("Question"));

        JTextArea answersArea = new JTextArea("Type your answers here...");
        answersArea.setLineWrap(true);
        answersArea.setBorder(BorderFactory.createTitledBorder("Answer"));


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
            } else {
                addFlashcard();

                cardPanel.remove(addFlashcardButton);

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

            cardPanel.remove(deleteFCButton);
            cardPanel.remove(questionsArea);
            cardPanel.remove(answersArea);
            cardPanel.remove(addFlashcardButton);

            mainPanel.revalidate();
            mainPanel.repaint();

            JOptionPane.showMessageDialog(null, "Card deleted successfully!", "Delete Card", JOptionPane.INFORMATION_MESSAGE);
        });

        cardPanel.add(questionsArea, "growx, h 10%, align left");
        cardPanel.add(answersArea, "growx, h 10%,align right, wrap");
        cardPanel.add(deleteFCButton, "gap bottom 15");
        cardPanel.add(addFlashcardButton, "align right, span, gap bottom 15");
      


        mainPanel.add(cardPanel, "growx, align center, wrap");

        repaint();
        revalidate();
        



    }

    void loadFlashcardUI(int id){
        flashcard currentCard = currentDeck.FindFlashcardByID(id);

        JButton addFlashcardButton = new JButton("Add");
        JButton deleteFCButton = new JButton("Delete");

        JTextArea questionsArea = new JTextArea(currentCard.getQuestion());
        questionsArea.setLineWrap(true);
        questionsArea.setBorder(BorderFactory.createTitledBorder("Question"));

        JTextArea answersArea = new JTextArea(currentCard.getAnswer());
        answersArea.setLineWrap(true);
        answersArea.setBorder(BorderFactory.createTitledBorder("Answer"));

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
            } else {
                addFlashcard();

                cardPanel.remove(addFlashcardButton);

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

            cardPanel.remove(deleteFCButton);
            cardPanel.remove(questionsArea);
            cardPanel.remove(answersArea);
            cardPanel.remove(addFlashcardButton);

            cardPanel.revalidate();
            cardPanel.repaint();

            JOptionPane.showMessageDialog(null, "Card deleted successfully!", "Delete Card", JOptionPane.INFORMATION_MESSAGE);
        });

        cardPanel.add(questionsArea, "growx, h 10%, align left");
        cardPanel.add(answersArea, "growx, h 10%, align right, wrap");
        cardPanel.add(deleteFCButton, "gap bottom 15");
        cardPanel.add(addFlashcardButton, "align right, span, wrap, gap bottom 15");
      
        cardPanel.repaint();
        cardPanel.revalidate();

        mainPanel.add(cardPanel, "growx, align center, wrap");

        repaint();
        revalidate();
        



    
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
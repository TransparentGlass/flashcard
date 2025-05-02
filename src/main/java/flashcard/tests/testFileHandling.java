package flashcard.tests;

import flashcard.deck;
import flashcard.flashcardFileManager;

public class testFileHandling {

    public testFileHandling() {
        deck newDeck = new deck();
        flashcardFileManager fileManager = new flashcardFileManager(newDeck);

        String fileName = "testDeck";
        fileManager.createFile(fileName);
        
        

        //test if it loads
        fileManager.readFile(fileName);
        deck currentDeck = fileManager.getDeck();

        // currentDeck.addCard("question 1", "answer 1");
        // currentDeck.addCard("question 2", "answer 2");
        // currentDeck.addCard("question 3", "answer 3");
        // currentDeck.addCard("question 4", "answer 4");
        // currentDeck.addCard("question 5", "answer 5");

        //check if currentdeck got added
        System.out.println(currentDeck.printList());

        //check if it will update if we delete
        currentDeck.deleteCard(4);
        fileManager.updateFile(fileName);

        fileManager.readFile(fileName);
    
        // fileManager.readFile(fileName);
        
        
        // fileManager.updateFile(fileName);

        // fileManager.readFile(fileName);

        



    }
    
}

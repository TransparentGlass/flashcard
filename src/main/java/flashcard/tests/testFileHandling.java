package flashcard.tests;

import flashcard.deck;
import flashcard.flashcardFileManager;

public class testFileHandling {

    public testFileHandling(deck deck) {
        flashcardFileManager fileManager = new flashcardFileManager(deck);

        String fileName = "testDeck";
        fileManager.createFile(fileName);
        fileManager.updateFile(fileName);
        fileManager.readFile(fileName);


    }
    
}

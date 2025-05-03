package flashcard.tests;

import flashcard.deck;
import flashcard.flashcardFileManager;

public class testFileHandling {
    deck currentDeck;
    public testFileHandling(deck deck) {
        deck newdeck = new deck();
        flashcardFileManager fileManager = new flashcardFileManager(newdeck);
        String fileName = "testDeck";
        this.currentDeck = fileManager.loadFile(fileName);
        
    }

    public deck getLoadedDeck(){
        return this.currentDeck;
    }

    
    
}

package flashcard.tests;

import flashcard.FileManager;
import flashcard.deck;

public class testFileHandling {
    deck currentDeck;
    public testFileHandling(deck deck) {
        deck newdeck = new deck();
        FileManager fileManager = new FileManager(newdeck);
        String fileName = "testDeck";
        this.currentDeck = fileManager.loadFile(fileName);
        
    } 

    public deck getLoadedDeck(){
        return this.currentDeck;
    }

    
    
}

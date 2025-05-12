package flashcard.tests;

import flashcard.FileManager;
import flashcard.deck;

public class testFileHandling {
    deck currentDeck;
    public testFileHandling(deck deck) {
        FileManager fileManager = new FileManager(deck);
        fileManager.createFile("LatestTest");
        fileManager.saveFile("LatestTest");

    } 

    public deck getLoadedDeck(){
        return this.currentDeck;
    }

    
    
}

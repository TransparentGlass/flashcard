package flashcard;


import flashcard.GUI.testStudyMode;
import flashcard.tests.testDeck;
import flashcard.tests.testFileHandling;

public class Main {
    public static void main(String[] args) {
        
        testDeck test = new testDeck();
        deck newDeck = new deck();
        testFileHandling file = new testFileHandling(newDeck);

        new testStudyMode(file.getLoadedDeck());

        // testDeck newDeck = new testDeck();
        
        








        

    }
}
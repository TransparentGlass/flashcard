package flashcard;


import flashcard.GUI.testStudyMode;
import flashcard.tests.testDeck;

public class Main {
    public static void main(String[] args) {
        testDeck test = new testDeck();
        new testStudyMode(test.getDeck());

        // testDeck newDeck = new testDeck();
        // new testFileHandling();
        








        

    }
}
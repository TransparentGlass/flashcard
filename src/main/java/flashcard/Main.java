package flashcard;


import flashcard.GUI.testGUI;
import flashcard.tests.testDeck;
import flashcard.tests.testFileHandling;

public class Main {
    public static void main(String[] args) {
        testDeck test = new testDeck();
        new testGUI(test.getDeck());
        new testFileHandling(test.getDeck());
        








        

    }
}
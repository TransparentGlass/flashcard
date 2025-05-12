package flashcard;

import flashcard.GUI.menu;
import flashcard.tests.testDeck;
import flashcard.tests.testStudyMode;

public class Main {
    public static void main(String[] args) {
 
        menu menu = new menu();
        menu.init();

        testDeck newDeck = new testDeck();
        
        testStudyMode newtest = new testStudyMode(newDeck.getDeck());
        
        








        

    }
}
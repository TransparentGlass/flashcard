package flashcard;


import flashcard.GUI.jilMenu;
import flashcard.tests.testDeck;
import flashcard.tests.testStudyMode;

public class Main {
    public static void main(String[] args) {
 
        jilMenu menu = new jilMenu();

        menu.init();

        testDeck newDeck = new testDeck();
        
        testStudyMode newtest = new testStudyMode(newDeck.getDeck());
        
        








        

    }
}
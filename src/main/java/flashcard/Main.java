package flashcard;

import flashcard.GUI.deckMode;

public class Main {
    public static void main(String[] args) {
 
        // menu menu = new menu();
        // menu.init();
        FileManager file = new FileManager(new deck());

        file.loadFile("LatestTest");

        

        
        // testStudyMode newtest = new testStudyMode(file.getDeck());

        deckMode newDeckMode = new deckMode(file.getDeck());

        

        








        

    }
}
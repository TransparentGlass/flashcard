package flashcard;

import javax.swing.SwingUtilities;

import flashcard.GUI.menu;

public class Main {
    public static void main(String[] args) {
 
        SwingUtilities.invokeLater(() -> {
            new menu().init();
        });

    }
}
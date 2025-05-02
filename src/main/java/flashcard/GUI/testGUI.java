package flashcard.GUI;

import javax.swing.JFrame;

public class testGUI {
    private JFrame frame;

    public testGUI(){
        frame = new JFrame("Study mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
    

    
}

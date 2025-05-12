package flashcard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileManager {
    public deck currentDeck;

    public FileManager(deck deck) {
        this.currentDeck = deck;
    }

    public void createFile(String fileName){
        
        try {
            File newFile = new File("data/decks/"+ fileName+ ".txt");

            if (newFile.createNewFile()){
                JOptionPane.showMessageDialog(null, "File Created!", "File creation", JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showMessageDialog(null, "File already exists!", "File creation", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IOException e) {
            e.printStackTrace();          
        }
        
    }

    public deck loadFile(String fileName){
        
        //this is assuming that the deck is empty
        if(!isDeckEmpty(currentDeck)){
            System.out.println("Deck has contents. Create a new deck");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/decks"+ fileName+ ".txt"))){
            
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("\\|");

                if (parts.length >= 2){
                    String question = parts[0].trim();
                    String answer = parts[1].trim();    
                    this.currentDeck.addCard(question, answer);
                    System.out.println(line);
                }
                
            }

            return currentDeck;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveFile(String fileName) {
        flashcard current = currentDeck.getHead();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/decks"+ fileName+ ".txt"))) {
            for (int i = 0; i < currentDeck.listLength(); i++) {
                writer.write(current.getQuestion() + " | " + current.getAnswer());
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public void deleteFile(String fileName){
        String filePath = "data/decks" + fileName + ".txt";
        File currentFile = new File(filePath);
        
        if (!currentFile.exists()){
            System.out.println("File does not exist");
            return;
        }

        if (currentFile.delete()){
            System.out.println("File deleted");
        } else {
            System.out.println("File failed to delete");
        }
    }

    public File[] getAllDeck(){
        String filePath = "data/decks/";
        File directory = new File(filePath);

       if (!directory.exists() || !directory.isDirectory()) {
        System.err.println("Directory does not exist or is invalid: " + filePath);
        return new File[0]; // Return empty array
        }

        File[] allDecks = directory.listFiles(File::isFile);

        return allDecks;
    }
    

    public deck getDeck(){
        return this.currentDeck;
    }

    public boolean isDeckEmpty(deck deck){
        if (deck.head == null){
            return true;
        }

        return false;
    }


    
    


    
}

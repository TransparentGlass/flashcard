package flashcard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class flashcardFileManager {
    public deck currentDeck;

    public flashcardFileManager(deck deck) {
        this.currentDeck = deck;
    }

    public void createFile(String fileName){
        
        try {
            File newFile = new File("data/"+ fileName+ ".txt");

            if (newFile.createNewFile()){
                System.out.println("File successfully created");
            } else {
                System.out.println("File already created");
            }
            
        } catch (IOException e) {
            e.printStackTrace();          
        }
        
    }

    public void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader("data/"+ fileName+ ".txt"))){
            
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile(String fileName) {
        flashcard current = currentDeck.getHead();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/"+ fileName+ ".txt"))) {
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
        String filePath = "data/" + fileName + ".txt";
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

    public deck getDeck(){
        return this.currentDeck;
    }


    
    


    
}

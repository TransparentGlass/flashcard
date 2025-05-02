package flashcard;

public class Main {
    public static void main(String[] args) {
        deck deck = new deck();

        deck.deleteCard(1);

        deck.addCard("question 1", "answer 1");
        deck.addCard("question 2", "answer 2");
        deck.addCard("question 3", "answer 3");
        deck.addCard("question 4", "answer 4");
        deck.addCard("question 5", "answer 5");
        
        System.out.println("Length of list "+deck.listLength());
        System.out.println(deck.printList()); 

        System.out.println("Delete function:");
        deck.deleteCard(5);
        deck.deleteCard(10);
        System.out.println(deck.printList());





        

    }
}
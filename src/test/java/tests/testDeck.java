package tests;

import flashcard.deck;

public class testDeck{
    public static deck deck = new deck();
    
    public testDeck(){
        deck.deleteCard(1);

        deck.addCard("Do you love me?", " tung tung tung sahur", 1);
        deck.addCard("Are you a hotdog?", " cappucino assasanino", 2);
        deck.addCard("question 3", "answer 3", 3);
        deck.addCard("question 4", "answer 4", 4);
        deck.addCard("question 5", "answer 5", 5);

        deck.insertCard("i like hotdogs", "me too", 7);
        
        System.out.println("Length of list "+deck.listLength());
        System.out.println(deck.printList()); 

        System.out.println("Delete function:");
        deck.deleteCard(5);
        deck.deleteCard(10);
        System.out.println(deck.printList());

        
        
    }

    public deck getDeck(){
        return deck;
    }
}
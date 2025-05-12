package flashcard;

import java.util.ArrayList;



public class deck {
    public flashcard head;
    public flashcard tail;

    

    public void addCard(String question, String answer, int id){
        flashcard newFlashcard = new flashcard(question, answer, id);
        
        if (isEmpty()){
            head = newFlashcard;
            tail = newFlashcard;
            return;
        }

        tail.next = newFlashcard;
        newFlashcard.prev = tail;
        tail = newFlashcard;
        


    }

    public void deleteCard(int count) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        if (count < 1 || count > listLength()) {
            System.out.println("Invalid index: negative index or count is greater than the list size");
            return;
        }

        // Case 1: Delete the head node
        if (count == 1) {
            if (head.next != null) {
                head.next.prev = null; // Detach the previous pointer of the new head
            }
            head = head.next; // Update the head
            if (head == null) {
                tail = null; // If the list becomes empty, update the tail
            }
            return;
        }

        // Case 2: Delete the tail node
        if (count == listLength()) {
            if (tail.prev != null) {
                tail.prev.next = null; // Detach the last node
            }
            tail = tail.prev; // Update the tail
            if (tail == null) {
                head = null; // If the list becomes empty, update the head
            }
            return;
        }

        // Case 3: Delete a middle node
        flashcard current = head;
        for (int i = 1; i < count - 1; i++) {
            current = current.next; // Traverse to the node before the one to be deleted
        }

        // Update pointers to remove the node
        current.next.next.prev = current; // Update the `prev` pointer of the next node
        current.next = current.next.next; // Update the `next` pointer of the current node
    }

    public boolean DeleteCardByID(int count) {
        // Validate the input
        if (count < 0) {
            System.err.println("Invalid ID: " + count);
            return false;
        }

        flashcard current = head;

        // Traverse the linked list to find the card
        while (current != null && current.ID != count) {
            current = current.next;
        }

        // If card is not found
        if (current == null) {
            System.err.println("Card with ID " + count + " not found.");
            return false;
        }

        // If deleting the head
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null; // Update the new head's prev pointer
            } else {
                tail = null; // If the list becomes empty, update the tail as well
            }
        }
        // If deleting the tail
        else if (current == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null; // Update the new tail's next pointer
            } else {
                head = null; // If the list becomes empty, update the head as well
            }
        }
        // If deleting a middle node
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
           
        }

        System.out.println("Card with ID " + count + " deleted successfully.");
        return true;
    }
    public void insertCard(String question, String answer, int id) {
        flashcard newFlashcard = new flashcard(question, answer, id);

        // Case 1: If the list is empty, add the card as the first card
        if (isEmpty()) {
            System.out.println("List is empty. Adding the card as the first card.");
            head = newFlashcard;
            tail = newFlashcard;
            return;
        }

        // Case 2: If the card should be inserted at the beginning of the list
        if (id <= head.ID) {
            System.out.println("Inserting card at the beginning.");
            newFlashcard.next = head;
            head.prev = newFlashcard;
            head = newFlashcard;
            return;
        }

        if (id >= tail.ID) {
        System.out.println("Inserting card at the end.");
        newFlashcard.prev = tail;
        tail.next = newFlashcard;
        tail = newFlashcard;
        tail.next = null; // Ensure the new tail's 'next' pointer is null
        return;
        }

        // Case 4: Insert in the middle of the list
        flashcard current = head;

        // Traverse the list to find the correct position
        while (current != null && current.ID < id) {
            current = current.next;
        }

        // Insert the new card before the current card
        System.out.println("Inserting card in the middle.");
        newFlashcard.prev = current.prev;
        newFlashcard.next = current;
        if (current.prev != null) {
            current.prev.next = newFlashcard;
        }
        current.prev = newFlashcard;
}

    public flashcard FindFlashcardByID(int id) {
        // Check if the list is empty
        if (head == null) {
            System.err.println("The list is empty. No flashcard to find.");
            return null;
        }

        flashcard current = head;

        // Traverse the list to find the flashcard with the given ID
        while (current != null) {
            if (current.ID == id) {
                return current; // Flashcard found
            }
            current = current.next;
        }

        // If no flashcard is found with the given ID
        System.err.println("Flashcard with ID " + id + " not found.");
        return null;
    }
    
    public String printList(){

        StringBuilder list = new StringBuilder();
        flashcard current = head;
        int num = 0;

        if (isEmpty()){
            System.out.println("list is empty");
            return null;
        }

        while (current != null){
            num++;
            list.append(num).append(". ").append("Question: ").append(current.front).append("| Answer: ").append(current.back).append(" | id: ").append(current.ID).append("\n");
            current = current.next;
        }
        return list.toString();
        
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int listLength(){
        int count = 0;


        flashcard current= head;
        while(current != null){
            count++;
            current = current.next;
        }

        return count;        
    }

    public ArrayList<Integer> listID(){
        flashcard current = this.head;
        ArrayList<Integer> IDList = new ArrayList<>();

        if (isEmpty()){
            return IDList;
        }

        
        while (current != null){
            IDList.add(current.getID());
            current = current.next;
        }
        
        return IDList;
    }
    public flashcard getHead(){
        return this.head;
    }


    public flashcard getTail(){
        return this.tail;
    }

    
}

package flashcard;


public class deck {
    flashcard head;
    int count = 0;

    void addCard(String question, String answer){
        flashcard newFlashcard = new flashcard(question, answer);
        
        if (isEmpty()){
            head = newFlashcard;
            return;
        }

        flashcard current = head;

        while(current.next != null){
            current = current.next;
        }

        current.next = newFlashcard;
        newFlashcard.prev = current;
        return;


    }

    void deleteCard(int count){

        
        if (isEmpty()){
            System.out.println("List is empty");
            return;
        }        

        if (count > listLength() || count < 0){
            System.out.println("negative number or count is greater than the list");
            return;
        }

        if (count == 1){
            head = head.next;
        }

        flashcard current = head;
        for (int i = 1; i < count - 1; i++){
            current = current.next;
        }

        if (current.next != null){
            current.next = current.next.next;
        }
        
    }

    void insertCard(String question, String answer){

    }

    void printList(){
        flashcard current = head;
        int num = 0;

        if (isEmpty()){
            System.out.println("list is empty");
            return;
        }

        while (current != null){
            num++;
            System.out.println(num + ". Question: "+ current.front + "| Answer: " + current.back);
            current = current.next;
        }
        
    }

    boolean isEmpty(){
        if (head == null){
            return true;
        }

        return false;
    }

    int listLength(){
        int count = 0;

        if (isEmpty()){
            return 0;
        }

        flashcard current= head;
        while(current != null){
            count++;
            current = current.next;
        }

        return count;        
    }


    
}

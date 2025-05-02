package flashcard;


public class deck {
    public flashcard head;
    

    public void addCard(String question, String answer){
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
        


    }

    public void deleteCard(int count){

        
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
            if (current.next.next != null){
                current.next.next.prev = current;
            }
            
            current.next = current.next.next;
        }
    }

    public void insertCard(String question, String answer, int count){
        
        if (isEmpty()){
            System.out.println("list is empty");
            return;
        }

        if (count > listLength() || count < 0){
            System.out.println("negative number or count is greater than the list");
            return;
        }

        flashcard newFlashcard = new flashcard(question, answer);
        flashcard current = this.head;

        for (int i = 1; i < count - 1; i++){
            current = current.next;
        }

        
        if (current.next!= null){
            current.next.prev = newFlashcard;
        }
        newFlashcard.next = current.next;
        newFlashcard.prev = current;
        current.next = newFlashcard;
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
            list.append(num).append(". ").append("Question: ").append(current.front).append("| Answer: ").append(current.back).append("\n");
            current = current.next;
        }
        return list.toString();
        
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int listLength(){
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

    public flashcard getHead(){
        return this.head;
    }


    
}

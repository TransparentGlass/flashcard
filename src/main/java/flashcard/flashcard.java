package flashcard;


public class flashcard{
    public flashcard next;
    public flashcard prev;
    int ID;
    String front;
    String back;

    public flashcard(String front, String back, int count){
        this.next = null;
        this.prev = null;
        this.front = front;
        this.back = back;
        this.ID = count;
    }

    public void setQuestion(String q){
        this.front = q;
    }

    public void setAnswer(String a){
        this.back = a;
    }

    public void setID(int a){
        this.ID = a;
    }

    public int getID(){
        return this.ID;
    }


    public String getAnswer(){
        return this.back;
    }

    public String getQuestion(){
        return this.front;

    }


}


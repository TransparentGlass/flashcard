package flashcard;

public class flashcard{
    flashcard next;
    flashcard prev;
    String front;
    String back;

    public flashcard(String front, String back){
        this.next = null;
        this.prev = null;
        this.front = front;
        this.back = back;
    }

    public void setQuestion(String q){
        this.front = q;
    }

    public void setAnswer(String a){
        this.back = a;
    }

    public String getAnswer(){
        return this.back;
    }

    public String getQuestion(){
        return this.front;

    }


}


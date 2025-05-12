package flashcard;


public class flashcard{
    public flashcard next;
    public flashcard prev;
    int count;
    String front;
    String back;

    public flashcard(String front, String back, int count){
        this.next = null;
        this.prev = null;
        this.front = front;
        this.back = back;
        this.count = count;
    }

    public void setQuestion(String q){
        this.front = q;
    }

    public void setAnswer(String a){
        this.back = a;
    }

    public void setCount(int a){
        this.count = a;
    }

    public int getCount(){
        return this.count;
    }


    public String getAnswer(){
        return this.back;
    }

    public String getQuestion(){
        return this.front;

    }


}


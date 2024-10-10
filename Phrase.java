import java.util.LinkedList;

public class Phrase {
    LinkedList<Bigram> queue;
    private boolean empty;
    private int size;

    public Phrase(){
        queue = new LinkedList<>();
    }
    public void enqueue(Bigram b){

    }

    public Bigram dequeue(){
        return null;
    }

    public Bigram peek(){
        return null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return empty;
    }
}

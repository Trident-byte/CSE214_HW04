import java.util.LinkedList;

public class Phrase extends LinkedList<Bigram>{

    /**
     * Creates an empty Phrase
     */
    public Phrase(){
        super();
    }

    public static Phrase buildPhraseFromStringforEnc(String s){
        return null;
    }

    /**
     * Adds a new Bigram to the end of the Phrase 
     *
     * @param b
     *    New bigram to be added
     */
    public void enqueue(Bigram b){
        addLast(b);
    }

    /**
     * Removes and returns the first Bigram in the Phrase
     *
     * @return
     *    Returns the first Bigram in Phrase
     */
    public Bigram dequeue(){
        return removeFirst();
    }

    /**
     * Returns without removing the first Bigram in the phrase;
     *
     * @return
     *    The first bigram in nthe phrase
     */
    public Bigram peek(){
        return peekFirst();
    }

    public Phrase encrypt(KeyTable key){
        return null;
    }

    public Phrase decrypt(KeyTable key){
        return null;
    }

    public String toString(){
        return null;
    }
}

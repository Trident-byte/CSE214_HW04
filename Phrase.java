import java.util.LinkedList;
/**
 * The <code>Phrase</code> represents a queue of bigram 
 * created by using the string. It also encrypts or decryptes the
 * queue of bigrams.
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class Phrase extends LinkedList<Bigram>{
    private int size;
    private static final int KEY_TABLE_DIMENSION = 5;
    /**
     * Creates an empty Phrase
     */
    public Phrase(){
        super();
    }

    /**
     * Builds and returns a new Phrase object, which 
     * is a queue containing bigrams representing s
     * 
     * @param s
     *    The String to represent as a Bigram queue.
     * @return
     *    The new Phrase object which contains a queue of Bigram objects representing s.
     */
    public static Phrase buildPhraseFromStringforEnc(String s){
        Phrase newPhrase = new Phrase();
        s = s.replaceAll("[^a-zA-Z]+", "").toLowerCase();
        for(int i = 0; i < s.length(); i += 2){
            if(i + 1 == s.length()){
                s += "x";
            }
            char char1 = s.charAt(i);
            char char2 = s.charAt(i + 1);
            if(char1 == 'j'){
                char1 = 'i';
            }
            if(char2 == 'j'){
                char2 = 'i';
            }
            if(char1 == char2){
                char2 = 'x';
                s = s.substring(0, i + 1) + "x" + s.substring(i + 1);
            }
            Bigram nextBigram = new Bigram(char1, char2);
            System.out.println(nextBigram.toString());
            newPhrase.enqueue(nextBigram);
        }
        return newPhrase;
    }

    /**
     * Adds a new Bigram to the end of the Phrase 
     *
     * @param b
     *    New bigram to be added
     */
    public void enqueue(Bigram b){
        addLast(b);
        size++;
    }

    /**
     * Removes and returns the first Bigram in the Phrase
     *
     * @return
     *    Returns the first Bigram in Phrase
     */
    public Bigram dequeue(){
        size--;
        return removeFirst();
    }

    /**
     * Returns without removing the first Bigram in the phrase;
     *
     * @return
     *    The first bigram in the phrase
     */
    public Bigram peek(){
        return peekFirst();
    }

    /**
     * Encrypts this Phrase, storing the encrypted bigrams
     * in a new Phrase queue object and returning it.
     * 
     * <dt>Precondition
     *    <dd>key is not null
     * 
     * @param key
     *    The KeyTable to use to encrypt this Phrase
     * @return
     *    The new Phrase object which contains a queue of Bigram objects 
     *    representing the encrypted version of this Phrase.
     * @throws IllegalArgumentException
     *    Thrown if key is null
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException{
        Phrase newPhrase = new Phrase();
        char[][] table = key.getKeyTable();
        while(!isEmpty()){
            int[] coords = findCoords(dequeue(), key);
            char char1,char2;
            if(coords[1] == coords[3]){
                char1 = table[(coords[0] + 1) % KEY_TABLE_DIMENSION][coords[1]];
                char2 = table[(coords[2] + 1) % KEY_TABLE_DIMENSION][coords[3]];
            }
            else if(coords[0] == coords[2]){
                char1 = table[coords[0]][(coords[1] + 1) % KEY_TABLE_DIMENSION];
                char2 = table[coords[2]][(coords[3] + 1) % KEY_TABLE_DIMENSION];
            }
            else{
                char1 = table[coords[0]][coords[3]];
                char2 = table[coords[2]][coords[1]];
            }
            Bigram newBigram = new Bigram(char1, char2);
            newPhrase.enqueue(newBigram);
        }
        return newPhrase;
    }

    /**
     * Decrypts this Phrase, storing the encrypted bigrams
     * in a new Phrase queue object and returning it.
     * 
     * <dt>Precondition
     *    <dd>key is not null
     * 
     * @param key
     *    The KeyTable to use to decrypt this Phrase
     * @return
     *    The new Phrase object which contains a queue of Bigram objects 
     *    representing the decrypted version of this Phrase.
     * @throws IllegalArgumentException
     *    Thrown if key is null
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException{
        Phrase newPhrase = new Phrase();
        char[][] table = key.getKeyTable();
        while(!isEmpty()){
            int[] coords = findCoords(dequeue(), key);
            char char1,char2;
            if(coords[1] == coords[3]){
                if(coords[0] == 0){
                    coords[0] = KEY_TABLE_DIMENSION;
                }
                if(coords[2] == 0){
                    coords[2] = KEY_TABLE_DIMENSION;
                }
                char1 = table[coords[0] - 1][coords[1]];
                char2 = table[coords[2] - 1][coords[3]];
            }
            else if(coords[0] == coords[2]){
                if(coords[1] == 0){
                    coords[1] = KEY_TABLE_DIMENSION;
                }
                if(coords[3] == 0){
                    coords[3] = KEY_TABLE_DIMENSION;
                }
                char1 = table[coords[0]][coords[1] - 1];
                char2 = table[coords[2]][coords[3] - 1];
            }
            else{
                char1 = table[coords[0]][coords[3]];
                char2 = table[coords[2]][coords[1]];
            }
            Bigram newBigram = new Bigram(char1, char2);
            newPhrase.enqueue(newBigram);
        }
        return newPhrase;
    }

    @Override
    /**
     * Returns the size of queue
     * 
     * @return
     *    returns size of queue
     */
    public int size(){
        return size;
    }

    @Override
    /**
     * Returns if the queue is empty
     * 
     * @return
     *    If size equal zero which indicates if its empty or not
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns a String representation of the Phrase formed by the collection of Bigrams
     * 
     * @return 
     *    A string representation of the Phrase formed by the collection of Bigrams
     */
    public String toString(){
        String s = "";
        Phrase storage = new Phrase();
        while(!isEmpty()){
            Bigram b = dequeue();
            s += b.getFirst();
            s += b.getSecond();
            storage.enqueue(b);
        }
        while(!storage.isEmpty()){
            Bigram b = storage.dequeue();
            enqueue(b);
        }
        return s.toUpperCase();
    }

    private int[] findCoords(Bigram b, KeyTable key){
        int[] coordinates = new int[4];
        char char1 = b.getFirst();
        char char2 = b.getSecond();
        coordinates[0] = key.findRow(char1);
        coordinates[1] = key.findCol(char1);
        coordinates[2] = key.findRow(char2);
        coordinates[3] = key.findCol(char2);
        return coordinates;
    }
}

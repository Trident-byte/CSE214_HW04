/**
 * The <code>Bigram</code> represents a bigram
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class Bigram {
    private char first;
    private char second;

    /**
     * Creates an empty Bigram
     */
    public Bigram(){

    }

    /**
     * Creates a bigram with two characters
     *
     * @param firstChar
     *    The first character of the bigram
     * @param secondChar
     *    The second character of the bigram
     */
    public Bigram(char firstChar, char secondChar){
        first = firstChar;
        second = secondChar;
    }

    /**
     * Returns the two characters of the bigram as a concatenated String
     *
     * @return
     *    Returns the two characters of the bigram as a concatenated String
     */
    public String toString(){
        String string = Character.toString(first) + " " + Character.toString(second);
        return string;
    }

    /**
     * Returns the first character of the bigram
     *
     * @return
     *    Returns the first character
     */
    public char getFirst() {
        return first;
    }

    /**
     * Returns the second character of the bigram
     *
     * @return
     *    Returns the second character
     */
    public char getSecond() {
        return second;
    }

    /**
     * Sets the value of the first field with a new char
     *
     * @param first
     *    The value of the character of the first field
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * Sets the value of the second field with a new char
     *
     * @param second
     *    The value of the character of the second field
     */
    public void setSecond(char second) {
        this.second = second;
    }
}

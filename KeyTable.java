/** 
 * The <code>KeyTable</code> creates a key
 * to decrypt and encrypt a message. It is also
 * referenced by other programs during the encryption
 * and decryption process.
 * 
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954  
 *    Recitation: 02
**/
public class KeyTable{
    private char[][] key;

    /**
     * Creates an empty KeyTable
     */
    public KeyTable(){
        key = new char[5][5];
    }

    /**
     * Creates a key based on a given phrase 
     * 
     * @param phrase
     *    Phrase used to generate key
     * @return
     *    Returns a new KeyTable object with a full key table
     */
    public static KeyTable buildFromString(String phrase){
        boolean[] alphabet;
        alphabet = new boolean[26];
        KeyTable keyTable = new KeyTable();
        int charsUsed = 0;
        for(int i = 0; i < phrase.length(); i++){
            char letter = phrase.charAt(i);
            if(Character.isAlphabetic(letter) && !alphabet[letter - 'a']){
                keyTable.key[charsUsed/5][charsUsed%5] = letter;
                alphabet[letter - 'a'] = true;
                charsUsed++;
            }
        }
        for(int i = 0; i < alphabet.length; i++){
            char letter = (char) (i + 'a');
            if(!alphabet[i] && letter != 'j'){
                keyTable.key[charsUsed/5][charsUsed%5] = letter;
                charsUsed++;
            }
        }
        return keyTable;
    }

    /**
     * Returns the row in which c occurs.
     *
     * <dt>Precondition
     *    <dd> c is a valid letter.
     * @param c
     *    The character to locate within the key matrix
     * @return
     *    The index of the row in which c occurs
     * @throws
     *    IllegalArgumentException thrown if c is not a valid letter in the key matrix.
     */
    public int findRow(char c) throws IllegalArgumentException{
        if(!Character.isAlphabetic(c)){
            throw new IllegalArgumentException("Not a valid letter");
        }
        return findPosition(c)[0];
    }

    /**
     * Returns the column in which c occurs.
     *
     * <dt>Precondition
     *   <dd> c is a valid letter.
     * @param c
     *    The character to locate within the key matrix
     * @return
     *    The index of the column in which c occurs
     * @throws
     *    IllegalArgumentException thrown if c is not a valid letter in the key matrix.
     */
    public int findCol(char c) throws IllegalArgumentException{
        if(!Character.isAlphabetic(c)){
            throw new IllegalArgumentException("Not a valid letter");
        }
        return findPosition(c)[1];
    }

    /**
     * Returns the key generated by the <code>KeyTable</code>
     * 
     * @return
     *    Returns the key generated by the <code>KeyTable</code>
     */
    public char[][] getKeyTable(){
        return key;
    }

    /**
     * Sets the key of the keytable based on a 2-d char array
     * 
     * @param key
     *    New value of the key field
     */
    public void setKeyTable(char[][] key){
        this.key = key;
    }

    /**
     * Creates a string representation of the key in a 5 by 5 grid
     * @return
     *    A string representation of the key as a 5 by 5 grid
     */
    public String toString(){
        String s = "";
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                s += Character.toString(key[i][j]) + " ";
            }
            s += "\n";
        }
        return s.toUpperCase();
    }

    private int[] findPosition(char c){
        int[] pos = new int[2];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(c == key[i][j]){
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }
}
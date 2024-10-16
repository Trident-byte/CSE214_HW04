import java.security.Key;
import java.util.Scanner;

/**
 * The <code>PlayFairEncryptionEngine</code> holds the menu driver
 * for the program.
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class PlayFairEncryptionEngine {
    /**
    * Main will first prompt the user for input 
    * in order to generate the KeyTable object. If no input is given, a 
    * default KeyTable object will be created (the alphabet, excluding J). 
    * The driver will allow the user to change the current key, print the current 
    * key, enter a plaintext input and receive an encrypted version, or enter an 
    * encrypted input and receive a plaintext version 
    *
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        KeyTable key = generateKey(input);
        System.out.println(key.toString());
        boolean closed = false;
        while(!closed){
            System.out.print("Please select an option: ");
            String command = input.nextLine().strip().toUpperCase();
            if(command.equals("CK")){
                key = generateKey(input);
            }
            else if(command.equals("PK")){
                System.out.println(key.toString());
            }
            else if(command.equals("EN")){
                Phrase phrase = encryptPhrasae(input, key);
                System.out.println(phrase.toString());
            }
            else if(command.equals("Q")){
                closed = true;
            }
        }
        input.close();
    }

    private static KeyTable generateKey(Scanner input){
        System.out.print("Enter key phrase: ");
        String keyPhrase= input.nextLine();
        System.out.println("Menu:");
        System.out.println("(CK) - Change key");
        System.out.println("(PK) - Print key");
        System.out.println("(EN) - Encrypt");
        System.out.println("(DE) - Decrypt");
        System.out.println("(Q) - Quit");
        if(keyPhrase == ""){
            keyPhrase = "playfair example";
        }
        try{
            KeyTable key = KeyTable.buildFromString(keyPhrase);
            System.out.println("Generation success!");
            return key;
        }
        catch(Exception e){
            System.out.println("Generation failed!");
        }
        return null;
    }

    private static Phrase encryptPhrasae(Scanner input, KeyTable key){
        System.out.print("Please enter a phrase to encrypt: ");
        String string= input.nextLine();
        try{
            Phrase phrase = Phrase.buildPhraseFromStringforEnc(string);
            phrase = phrase.encrypt(key);
            return phrase;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Invaliad String");
        }
        return null;
    }
}

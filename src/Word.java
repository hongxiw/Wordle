/**
 * This class represents a single Word that will fit on a row in a WordleGrid
 * @author Hongxi Wen
 */
public class Word {

    /**
     * An Array or the letters of the split up word
     */
    private String[] word;

    /**
     * Initializes a new Word object by splitting the String word into single letters
     * @param word The String word that will be stored by this object
     */
    public Word(String word) {
        this.word = word.split("");
    }

    /**
     * Returns the Array of letters of the word the object stores
     * @return The Array of letters of the stored word
     */
    public String[] getLetterList() {
        return word;
    }

    /**
     * Returns the original word stored in the object
     * @return The original stored word
     */
    public String toString() {
        String answer = "";
        for(String letter : word) {
            answer += letter;
        }
        return answer;
    }

}

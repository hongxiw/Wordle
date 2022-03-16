import java.util.ArrayList;
/**
 * This class represents the grid of letters that can be printed for players to see guesses
 *
 * @author Hongxi Wen
 */
public class WordleGrid {


    public static final String WHITE = "\u001B[37m";
    public static final String YELLOW = "\033[0;33m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\u001B[0m";
    private static final String[][] EMPTY_GRID = {
            {"_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_"}
    };

    /**
     * A 2DArray of String that will be printed to show guess and correct letters
     */
    private String[][] grid;
    /**
     * The Word that represents the correct answer
     */
    private Word answer;
    /**
     * An ArrayList of guesses the player has already inputted
     */
    private ArrayList<Word> guesses;
    /**
     * If the grid has been solved yet
     */
    private boolean solved;

    /**
     * Initializes a WordleGrid object
     * @param answer The String that will be converted into a Word object as the correct answer
     */
    public WordleGrid(String answer) {
        grid = EMPTY_GRID;
        this.answer = new Word(answer);
        guesses = new ArrayList<>();
        solved = false;
    }

    /**
     * Returns the Word object representing the correct answer
     * @return The Word object representing the correct answer
     */
    public Word getAnswer() {
        return answer;
    }

    /**
     * Returns if the answer has been guessed or the board is solved
     * @return true if the board is solved, false if the answer has not been guessed yet
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     *
     * @param guess
     * @return
     */
    public void addWord(String guess) {
        if(guesses.size() <= 5) {
            guesses.add(new Word(guess));
            updateGrid();
            if(answer.toString().equals(guess)) {
                solved = true;
            }
        }
    }

    /**
     * Updates the grid 2DArray with the ArrayList of guesses. Colors each letter based on if it is found in the answer
     */
    public void updateGrid() {
        for(int row = 0; row < guesses.size(); row++) {
            for(int col = 0; col < 5; col++) {
                String letter = guesses.get(row).getLetterList()[col];
                if(letter.equals(answer.getLetterList()[col])) {
                    grid[row][col] = GREEN + letter + RESET;
                } else if(letterFoundInAnswer(letter)) {
                    grid[row][col] = YELLOW + letter + RESET;
                } else {
                    grid[row][col] = WHITE + letter + RESET;
                }
            }
        }
    }

    /**
     * Prints the 2DArray grid for the player to see their guesses and correct letters
     */
    public void printGrid() {
        for(String[] row : grid) {
            for(String character : row) {
                System.out.print(character + " ");
            }
            System.out.println();
        }
    }

    /**
     * Helper method that sees if searchForLetter is found in the answer
     * @param searchForLetter The letter to search for
     * @return true if the letter is found in the answer, false otherwise
     */
    private boolean letterFoundInAnswer(String searchForLetter) {
        for(String letter: answer.getLetterList()) {
            if(letter.equals(searchForLetter)) {
                return true;
            }
        }
        return false;
    }

}
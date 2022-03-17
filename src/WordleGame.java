import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that holds the main logic for running a game of Wordle
 * @author Hongxi Wen
 */
public class WordleGame {

    /**
     * ArrayList of the possible answers
     */
    private final ArrayList<String> possibleAnswers;
    /**
     * ArrayList that represents a dictionary of allowed guesses
     */
    private final ArrayList<String> allowedGuesses;
    /**
     * WordleGrid to store guesses into
     */
    private WordleGrid grid;
    /**
     * Scanner to listen for player input
     */
    private Scanner scan;

    /**
     * Initializes a new WordleGame object and loads possible answers and guesses into the ArrayLists
     */
    public WordleGame() {
        possibleAnswers = new ArrayList<>();
        allowedGuesses = new ArrayList<>();
        loadPossibleAnswers();
        loadPossibleGuesses();
        scan = new Scanner(System.in);
    }

    /**
     * Method to begin playing Wordle
     */
    public void play() {
        String answer = possibleAnswers.get((int) (Math.random() * (possibleAnswers.size())));
        grid = new WordleGrid(answer);
        System.out.println("Welcome to Wordle!");
        int guesses = 0;
        boolean solved = false;
        grid.printGrid();
        while(guesses < 6 && !solved) {
            boolean invalidGuess = true;
            while(invalidGuess) {
                System.out.println("Debug Answer: " + answer);
                System.out.print("Enter your guess: ");
                String guess = scan.nextLine().toLowerCase();
                if(guess.length() == 5 && (allowedGuesses.contains(guess) || possibleAnswers.contains(guess))) {
                    grid.addWord(guess);
                    guesses++;
                    invalidGuess = false;
                } else {
                    System.out.println("Not a valid guess!");
                }
            }
            grid.printGrid();
            solved = grid.isSolved();
        }
        if(solved) {
            System.out.println("Congrats! You guessed the word in " + guesses + " guesses!");
        } else {
            System.out.println("You ran out of guesses!");
            System.out.println("The correct word was " + grid.getAnswer());
        }
    }

    /**
     * Loads the possible answers from the text file into the possibleAnswers ArrayList
     */
    private void loadPossibleAnswers()
    {
        try {
            Scanner input = new Scanner(new File("src\\answers.txt"));
            while (input.hasNext()) {
                String word = input.next();
                possibleAnswers.add(word);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads the possible answers from the text file into the allowedGuesses ArrayList
     */
    private void loadPossibleGuesses() {
        try {
            Scanner input = new Scanner(new File("src\\guesses.txt"));
            while (input.hasNext()) {
                String word = input.next();
                allowedGuesses.add(word);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
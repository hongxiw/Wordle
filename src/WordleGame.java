import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordleGame {

    private final ArrayList<String> possibleAnswers;
    private final ArrayList<String> allowedGuesses;
    private WordleGrid grid;
    private Scanner scan;

    public WordleGame() {
        possibleAnswers = new ArrayList<>();
        allowedGuesses = new ArrayList<>();
        loadPossibleAnswers();
        loadPossibleGuesses();
        scan = new Scanner(System.in);
    }

    public void play() {
        String answer = possibleAnswers.get((int) (Math.random() * (possibleAnswers.size())));
        System.out.println("Answer: " + answer);
        grid = new WordleGrid(answer);
        System.out.println("Welcome to Wordle!");
        int guesses = 0;
        boolean solved = false;
        grid.printGrid();
        while(guesses < 6 && !solved) {
            boolean invalidGuess = true;
            while(invalidGuess) {
                System.out.print("Enter your guess: ");
                String guess = scan.nextLine().toLowerCase();
                if(guess.length() == 5 && (allowedGuesses.contains(guess) || possibleAnswers.contains(guess))) {
                    grid.addWord(guess);
                    guesses++;
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
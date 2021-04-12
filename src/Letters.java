import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Numbers round in Countdown game.
 *
 * @author David W. Arnold
 * @version 12/04/2021
 */
public class Letters
{
    private final int TOTAL_LETTERS;
    private final String VOWELS = "AEIOU";
    private final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";
    private final String VOWEL_INPUT = "v";
    private final String CONSONANT_INPUT = "c";

    // The random choosen letters available.
    private List<Character> choosenLetters;
    private Scanner scanner;

    public Letters(int numOfLetters)
    {
        this.TOTAL_LETTERS = numOfLetters;
        chooseLetters();
    }

    /**
     * Print a list of possible words.
     */
    public void printPossibleWords()
    {
        // TODO: Implement list of possible words, for group of random letters.
        System.out.println("\nPossible words, coming soon...\n");
    }

    /**
     * Choose the letters for the letters round.
     */
    private void chooseLetters()
    {
        choosenLetters = new ArrayList<>();
        scanner = new Scanner(System.in);
        for (int i = 0; i < TOTAL_LETTERS; i++) {
            outOfWhile:
            while (true) {
                System.out.println("\n(" + (TOTAL_LETTERS - i) +
                        " letters left) Would you like a Vowel (v) or Consonant (c)?\n");
                switch (scanner.next()) {
                    case VOWEL_INPUT:
                        choosenLetters.add(randomVowel());
                        printBoard();
                        break outOfWhile;
                    case CONSONANT_INPUT:
                        choosenLetters.add(randomConsonant());
                        printBoard();
                        break outOfWhile;
                    default:
                        System.out.println("\nPlease provide one of the valid inputs: (" +
                                VOWEL_INPUT + "," +
                                CONSONANT_INPUT + ")\n");
                }
            }
        }
    }

    private Character randomVowel()
    {
        // Generate random Vowel.
        return VOWELS.charAt(new Random().nextInt(VOWELS.length()));
    }

    private Character randomConsonant()
    {
        // Generate random Consonant.
        return CONSONANTS.charAt(new Random().nextInt(CONSONANTS.length()));
    }

    /**
     * Print the numbers board to standard output.
     */
    private void printBoard()
    {
        System.out.println("\n---------------- LETTERS BOARD ----------------\n");
        System.out.println("Letters:\t" + choosenLetters);
        System.out.println("\n-----------------------------------------------\n");
    }
}

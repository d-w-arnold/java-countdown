import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Countdown game played on CLI.
 *
 * @author David W. Arnold
 * @version 12/04/2021
 */
public class Countdown
{
    private final int COUNTDOWN_TIMER = 30; // 30 seconds clock
    private final String LETTERS_NUMBERS_INPUT = "y";
    private final String LETTERS_INPUT = "l";
    private final String NUMBERS_INPUT = "n";
    private final int TOTAL_NUMBERS = 6;
    private final int TOTAL_LETTERS = 9;

    private Scanner scanner;

    public Countdown() throws InterruptedException
    {
        scanner = new Scanner(System.in);
        startGame();
    }

    private void startGame() throws InterruptedException
    {
        System.out.println("\n------------------ COUNTDOWN ------------------\n");
        outOfWhile:
        while (true) {
            System.out.println("\nWould you like to play Letters and Numbers (" + LETTERS_NUMBERS_INPUT +
                    "), just Letters (" + LETTERS_INPUT + "), or just Numbers (" + NUMBERS_INPUT + ")?" +
                    "\n(e.g. Type '" + LETTERS_INPUT + "' and ENTER for just Letters)\n");
            switch (scanner.next()) {
                case LETTERS_NUMBERS_INPUT:
                    lettersAndNumbers();
                    break outOfWhile;
                case LETTERS_INPUT:
                    justLetters();
                    break outOfWhile;
                case NUMBERS_INPUT:
                    justNumbers();
                    break outOfWhile;
                default:
                    System.out.println("\nPlease provide one of the valid inputs: (" +
                            LETTERS_NUMBERS_INPUT + "," +
                            LETTERS_INPUT + "," +
                            NUMBERS_INPUT + ")\n");
            }
        }
    }

    private void countdownClock() throws InterruptedException
    {
        System.out.println("\n" + COUNTDOWN_TIMER + " seconds on the clock, please ENTER when you are ready:\n");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= COUNTDOWN_TIMER; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(". ");
            if (i % 10 == 0) {
                System.out.print("\n");
            }
        }
    }

    private void numbersRound() throws InterruptedException
    {
        // A single numbers rounds.
        System.out.println("\nStarting Numbers round...\n");
        System.out.println("\nHow many large numbers would you like (between 1-4)?\n");
        int ln = scanner.nextInt();
        Numbers numbersRound = new Numbers(ln, TOTAL_NUMBERS - ln);
        countdownClock();
        System.out.println("\nWould you like to see a possible solution to the numbers round target (y/n)?\n");
        if (scanner.next().equals("y")) {
            numbersRound.printSolution();
        }
    }

    private void lettersRound() throws InterruptedException
    {
        // A single letters rounds.
        System.out.println("\nStarting Letters round...\n");
        Letters lettersRound = new Letters(TOTAL_LETTERS);
        countdownClock();
    }

    private void lettersAndNumbers() throws InterruptedException
    {
        outOfWhile:
        while (true) {
            System.out.println("\nWould you like to play Letters (" + LETTERS_INPUT + ") or Numbers (" + NUMBERS_INPUT + ") first?" +
                    "\nPlease type abbreviated response:\n");
            switch (scanner.next()) {
                case LETTERS_INPUT:
                    lettersThenNumbers();
                    break outOfWhile;
                case NUMBERS_INPUT:
                    numbersThenLetters();
                    break outOfWhile;
                default:
                    System.out.println("\nPlease provide one of the valid inputs: (" +
                            LETTERS_INPUT + "," +
                            NUMBERS_INPUT + ")\n");
            }
        }
    }

    private void lettersThenNumbers() throws InterruptedException
    {
        do {
            lettersRound();
            numbersRound();
        } while (promptEnd());
        endGame();
    }

    private void numbersThenLetters() throws InterruptedException
    {
        do {
            numbersRound();
            lettersRound();
        } while (promptEnd());
        endGame();
    }

    private void justNumbers() throws InterruptedException
    {
        // Just numbers rounds.
        do {
            numbersRound();
        } while (promptEnd());
        endGame();
    }

    private void justLetters() throws InterruptedException
    {
        // Just letters rounds.
        do {
            lettersRound();
        } while (promptEnd());
        endGame();
    }

    private boolean promptEnd()
    {
        System.out.println("\nDo you want to continue playing Countdown (y/n)?\n");
        String s = scanner.next();
        return !s.equals("n");
    }

    private void endGame()
    {
        System.out.println("\nThank you for playing Countdown!\n");
        System.out.println("\n-----------------------------------------------\n");
    }
}

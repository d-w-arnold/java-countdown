import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Numbers round in Countdown game.
 * <p>
 * The valid calculations include: (+) (-) (*) (/)
 * <p>
 * Small numbers available: 20 "small numbers" (two each of 1 through 10)
 * Large numbers available: 4 "large numbers" of 25, 50, 75 and 100
 *
 * @author David W. Arnold
 * @version 09/04/2021
 */
public class Numbers
{
    // Smallest small number in range of small numbers.
    private final int SMALL_NUMBER_MIN = 1;
    // Largest small number in range of small numbers.
    private final int SMALL_NUMBER_MAX = 10;
    // Selection of large numbers available in a numbers round.
    private final int[] LARGE_NUMBERS = {25, 50, 75, 100};
    // Selection of large numbers available in a special edition numbers round.
    private final int[] LARGE_NUMBER_SPECIAL = {12, 37, 62, 87}; // TODO: Extend to include special large numbers.

    // The number available to make the target number.
    private List<Integer> numbers;
    // Target number in Numbers round.
    private int target;

    public Numbers(int largeQuantity, int smallQuantity)
    {
        numbers = new ArrayList<>();
        chooseNumbers(largeQuantity, smallQuantity);
        generateTarget();
        printBoard();
    }

    public void getSolution()
    {
        // TODO: Complete getSolution() method
        System.out.println("Prints the solution as a pretty string");
    }

    /**
     * Choose the numbers for the numbers round.
     *
     * @param largeQuantity Number of large numbers to use in Numbers round.
     * @param smallQuantity Number of small numbers to use in Numbers round.
     */
    private void chooseNumbers(int largeQuantity, int smallQuantity)
    {
        // Randomly pick large number based on quantity specified by player.
        List<Integer> largeNumbers = new ArrayList<>();
        for (int ln : LARGE_NUMBERS) {
            largeNumbers.add(ln);
        }
        Collections.shuffle(largeNumbers);
        for (int i = 0; i < largeQuantity; i++) {
            numbers.add(largeNumbers.get(i));
        }
        // Randomly pick small number based on quantity specified by player.
        List<Integer> smallNumbers = new ArrayList<>();
        for (int i = SMALL_NUMBER_MIN; i <= SMALL_NUMBER_MAX; i++) {
            for (int j = 1; j <= 2; j++) {
                smallNumbers.add(i);
            }
        }
        Collections.shuffle(smallNumbers);
        for (int i = 0; i < smallQuantity; i++) {
            numbers.add(smallNumbers.get(i));
        }
    }

    /**
     * Generate a valid random 3-digit target number for the numbers round.
     */
    private void generateTarget()
    {
        // TODO: Complete generateTarget() method
        target = 0;
    }

    private void printBoard()
    {
        System.out.println("\n---------------- NUMBERS BOARD ----------------\n");
        System.out.println("Target:\t\t[ " + target + " ]");
        System.out.println("Numbers:\t[ " + convertToString(new ArrayList<>(numbers)) + " ]");
        System.out.println("\n-----------------------------------------------\n");
    }

    private String convertToString(ArrayList<Integer> numbers)
    {
        StringBuilder builder = new StringBuilder();
        // Append all Integers in StringBuilder to the StringBuilder.
        for (int number : numbers) {
            builder.append(number);
            builder.append(", ");
        }
        // Remove last delimiter with setLength.
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }
}

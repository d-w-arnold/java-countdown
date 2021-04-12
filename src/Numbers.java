import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    // The numbers available to make the target number.
    private List<Integer> choosenNumbers;
    // Target number in Numbers round.
    private int target;
    // The last calculation recorded, used to generate the solution for a given target number.
    private char lastCalc;
    // The order of calculations in the solution to get too the target number.
    private List<Character> solutionCals;
    // The order of shuffled numbers in the solution to get too the target number.
    private List<Integer> solutionNumsOrder;


    public Numbers(int largeQuantity, int smallQuantity)
    {
        chooseNumbers(largeQuantity, smallQuantity);
        generateTarget();
        printBoard();
    }

    /**
     * Print solution to get the target number to standard output.
     */
    public void printSolution()
    {
        String solution = target + " = ";
        StringBuilder tmpValue = new StringBuilder("(" + solutionNumsOrder.get(0) + ")");
        for (int i = 0; i < solutionCals.size(); i++) {
            char tmpCalc = solutionCals.get(i);
            int tmpNum = solutionNumsOrder.get(i + 1);
            tmpValue = new StringBuilder("(" + tmpValue + " " + tmpCalc + " " + tmpNum + ")");
        }
        solution += tmpValue;
        System.out.println("\nSolution:\n" + solution + "");
    }

    /**
     * Choose the numbers for the numbers round.
     *
     * @param largeQuantity Number of large numbers to use in Numbers round.
     * @param smallQuantity Number of small numbers to use in Numbers round.
     */
    private void chooseNumbers(int largeQuantity, int smallQuantity)
    {
        choosenNumbers = new ArrayList<>();
        // Randomly pick large number based on quantity specified by player.
        List<Integer> largeNumbers = new ArrayList<>();
        for (int ln : LARGE_NUMBERS) {
            largeNumbers.add(ln);
        }
        Collections.shuffle(largeNumbers);
        for (int i = 0; i < largeQuantity; i++) {
            choosenNumbers.add(largeNumbers.get(i));
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
            choosenNumbers.add(smallNumbers.get(i));
        }
    }

    /**
     * Generate a valid random 3-digit target number for the numbers round.
     */
    private void generateTarget()
    {
        List<Integer> tmpNumbers = new ArrayList<>(choosenNumbers);
        while (true) {
            Collections.shuffle(tmpNumbers);
            int tmpTargetNumber = getTargetNumber(tmpNumbers);
            if (validTargetNumber(tmpTargetNumber)) {
                target = tmpTargetNumber;
                break;
            }
        }
    }

    /**
     * Generate a target number for a given assortment of shuffled choosen numbers.
     *
     * @param tmpNumbers The shuffled choosen numbers.
     * @return The generated target number.
     */
    private int getTargetNumber(List<Integer> tmpNumbers)
    {
        solutionCals = new ArrayList<>();
        int total = tmpNumbers.get(0);
        for (int i = 1; i < randomNumCalcs(tmpNumbers.size(), tmpNumbers.size() / 2); i++) {
            int tmpNumber = tmpNumbers.get(i);
            total = randomCalc(total, tmpNumber);
            if (total < 0) {
                return -1; // Illegal running total, negative value.
            } else {
                solutionCals.add(lastCalc);
            }
        }
        solutionNumsOrder = new ArrayList<>(tmpNumbers);
        return total;
    }

    /**
     * Generate a random number of calculations a player would need to conduct to a valid solution for a target number.
     *
     * @param n The number of choosen numbers.
     * @param b The bounds for generating a number.
     * @return The random number of calculations to use to get to the target number.
     */
    private int randomNumCalcs(int n, int b)
    {
        return n - new Random().nextInt(b);
    }

    /**
     * Conduct a random valid calculation.
     *
     * @param x First number in calculation.
     * @param y Second number in calculation.
     * @return Answer of calculation.
     */
    private int randomCalc(int x, int y)
    {
        int ans = 0;
        int n;
        if (y == 1) {
            n = 3; // Avoids division by 1.
        } else {
            n = 4;
        }
        switch (new Random().nextInt(n)) {
            case 0: // (+)
                lastCalc = '+';
                ans = x + y;
                break;
            case 1: // (-)
                lastCalc = '-';
                ans = x - y;
                break;
            case 2: // (*)
                lastCalc = '*';
                ans = x * y;
                break;
            case 3: // (/)
                lastCalc = '/';
                if (x % y == 0) {
                    ans = x / y;
                } else {
                    ans = -1; // Illegal division, has a remainder.
                }
                break;
        }
        return ans;
    }

    /**
     * Validate whether a potential target number is 3-digits.
     *
     * @param potentialTargetNumber The potential target number.
     * @return True if a 3-digit number.
     */
    private boolean validTargetNumber(int potentialTargetNumber)
    {
        return 100 <= potentialTargetNumber && potentialTargetNumber < 1000;
    }

    /**
     * Print the numbers board to standard output.
     */
    private void printBoard()
    {
        System.out.println("\n---------------- NUMBERS BOARD ----------------\n");
        System.out.println("Target:\t\t[ " + target + " ]");
        System.out.println("Numbers:\t[ " + convertToString(new ArrayList<>(choosenNumbers)) + " ]");
        System.out.println("\n-----------------------------------------------\n");
    }

    /**
     * Convert a list of ints to a string.
     *
     * @param numbers The list of ints.
     * @return The list of ints as a string.
     */
    private String convertToString(List<Integer> numbers)
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

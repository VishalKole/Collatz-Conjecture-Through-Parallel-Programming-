//******************************************************************************
//
// File:    CollatzConjecture.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

/**
 * Class CollatzConjecture is a sequential program that computes the Collatz Conjuecture
 * for the number provided. It returns the Collatz Conjuecture number to the calling
 * program. It also checks for the input validation and returns true if the input
 * could be used for the computation.
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class CollatzConjecture {

    /**
     * Computes and returns the Collatz Conjuecture number for given long integer
     *
     * @param input_number input long integer
     * @return returns the sum of steps required to get the Collatz Conjuecture
     */
    public static long getCollatzNumber(long input_number) {
        long result = 1;

        //main recursive computation
        while (input_number > 1) {
            if (input_number % 2 == 0) {
                input_number = input_number / 2;
            } else {
                input_number = input_number * 3 + 1;
            }
            ++result;
        }
        return result;
    }

    /**
     * Class validateCollatzConjectureInput is a validator function to check the input for correct
     * format and argument size. It also checks if the input is in range.
     *
     * @param args input string arguments to validate to be used with CollatzConjecture function.
     * @return returns a long integer array with the range provided as string input.
     * @throws IllegalArgumentException throws error if arguments are not equal to two or are not all
     *                                  integers or if lower bound not less than upper bound.
     */
    public static long[] validateCollatzConjectureInput(String args[]) throws IllegalArgumentException {
        // lower and upper bound range
        long range_array[] = new long[2];

        //check if arguments equal to two
        if (args.length != 2)
            throw new IllegalArgumentException("Arguments are not exactly equal to two.");

        //check if arguments are only integers
        if (!args[0].matches("[0-9]+") || !args[1].matches("[0-9]+"))
            throw new IllegalArgumentException("Arguments are not numbers and contains other characters or" +
                    " special characters.");

        //convert to long int
        range_array[0] = Long.parseLong(args[0]);
        range_array[1] = Long.parseLong(args[1]);

        //check if the lower bound less than upper bound
        if ((range_array[1] < range_array[0]) || range_array[0] < 1 || range_array[1] < 1)
            throw new IllegalArgumentException("Range is not in correct order or not greater than 1. ");

        return range_array;
    }
}

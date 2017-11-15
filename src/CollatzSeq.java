//******************************************************************************
//
// File:    CollatzSeq.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;

/**
 * Class CollatzSeq computes the Collatz Conjecture in a sequential manner. It takes the
 * String arguments and checks for validation. If the validation holds true, it then computes
 * the Collatz Conjecture number and displays it.
 * <p>
 * Usage: <TT>java pj2 CollatzSeq <I>LB</I> <I>UB</I> </TT>
 * <BR><TT><I>LB</I></TT> = Lower bound for the initial n value. it must be a long integer ≥ 1
 * <BR><TT><I>UB</I></TT> = Upper Bound for the initial n value. it must be a long integer ≥ LB
 * <p>
 * The program uses CollatzConjecture for its collatz number generator.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class CollatzSeq extends Task {

    /**
     * Main program
     *
     * @param args input arguments - Range on which to compute
     */
    public void main(String[] args) throws Exception {

        //variable to store bounds
        long[] bound_range = {0, 0};

        //validate the input
        try {
            bound_range = CollatzConjecture.validateCollatzConjectureInput(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            terminate(1);
        }

        //Reduction variables to reduce and store the minimum and maximum CN for range of
        //input.
        MaxVbl max = new MaxVbl();
        MinVbl min = new MinVbl();

        //loop to iterate through the range and compute the CN
        for (long i = bound_range[0]; i <= bound_range[1]; ++i) {
            long val = CollatzConjecture.getCollatzNumber(i);

            //Reduce the result to get min and max of it
            max.reduce(val, i);
            min.reduce(val, i);
        }

        //Print the results on console IO.
        System.out.println("C(" + min.minI + ") = " + min.minCN);
        System.out.println("C(" + max.maxI + ") = " + max.maxCN);
    }
}

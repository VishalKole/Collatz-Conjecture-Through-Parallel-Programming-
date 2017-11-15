//******************************************************************************
//
// File:    CollatzSmp.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.LongLoop;
import edu.rit.pj2.Task;

/**
 * Class CollatzSmp computes the Collatz Conjecture in a parallel. It takes the String arguments
 * and checks for validation. If the validation holds true, it then computes the Collatz Conjecture
 * number and displays it. It does this in K parallel threads, where K is provided as input.
 * <p>
 * Usage: <TT>java pj2 cores= <I>K</I> CollatzSmp <I>LB</I> <I>UB</I> </TT>
 * <BR><TT><I>K</I></TT> = The number of CPU cores to use.
 * <BR><TT><I>LB</I></TT> = Lower bound for the initial n value. it must be a long integer ≥ 1.
 * <BR><TT><I>UB</I></TT> = Upper Bound for the initial n value. it must be a long integer ≥ LB.
 * <p>
 * The program uses CollatzConjecture for its collatz number generator.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class CollatzSmp extends Task {

    //Reduction variables to reduce and store the minimum and maximum CN for range of
    //input. shared variables.
    MaxVbl max;
    MinVbl min;

    /**
     * Main program
     *
     * @param args input arguments - Range on which to compute.
     */
    public void main(String[] args) {

        //variable to store bounds
        long[] bound_range = {0, 0};

        //validate the input
        try {
            bound_range = CollatzConjecture.validateCollatzConjectureInput(args);
        } catch (Exception e) {
            System.out.println(e + "Error occurred in Collatz Conjecture input validation.");
            terminate(1);
        }

        //Reduction variables to reduce and store the minimum and maximum CN for range of
        //input.
        max = new MaxVbl();
        min = new MinVbl();

        //Parallel-for loop to iterate through the range and compute the CN. Guided scheduling
        //is used to balance the load.
        try {
            parallelFor(bound_range[0], bound_range[1]).schedule(guided).exec(new LongLoop() {

                //thread copy of the Max and Min variable.
                MaxVbl thrMax;
                MinVbl thrMin;
                long CollatzNumber;

                //assignment for the local thread variable
                public void start() {
                    thrMax = threadLocal(max);
                    thrMin = threadLocal(min);
                }

                public void run(long CollatzInput) {

                    //compute the CN
                    CollatzNumber = CollatzConjecture.getCollatzNumber(CollatzInput);

                    //Parallel reduction through VBL reduction method
                    thrMax.reduce(CollatzNumber, CollatzInput);
                    thrMin.reduce(CollatzNumber, CollatzInput);
                }
            });
        } catch (Exception e) {
            System.out.println(e + "Error occurred in parallelFor.");
            terminate(1);
        }

        //Print the results on console IO.
        System.out.println("C(" + min.minI + ") = " + min.minCN);
        System.out.println("C(" + max.maxI + ") = " + max.maxCN);
    }
}

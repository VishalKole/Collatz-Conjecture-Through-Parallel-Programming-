//******************************************************************************
//
// File:    MinVbl.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Vbl;

/**
 * Class MinVbl provides a reduction variable for a CollatzSmp class shared by
 * multiple threads executing a parallel loop.
 * <p>
 * Class MinVbl uses the reduction pattern as implemented by the library.
 * Local threads are created using the threadLocal method and the reduction
 * is handled by the library after the parallel loop execution is done. It uses
 * reduction function to merge the threads.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class MinVbl implements Vbl {

    long minCN, minI;

    /**
     * Constructor to assign maximum hardware dependent value to the variable.
     */
    MinVbl() {
        minCN = Long.MAX_VALUE;
        minI = Long.MAX_VALUE;
    }

    /**
     * Clone the current thread. Interfaces' required function.
     *
     * @return return a new deep copy clone of current object
     */
    public Object clone() {
        try {
            MinVbl vbl = (MinVbl) super.clone();
            vbl.minCN = this.minCN;
            vbl.minI = this.minI;
            return vbl;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("MinVbl clone error");
        }
    }

    /**
     * reduces and stores the minimum of the Vbl object provided and the current
     * object. Interfaces' required function.
     *
     * @param vbl Reduction interface object.
     */
    public void reduce(Vbl vbl) {
        MinVbl convertedMinVbl = (MinVbl) vbl;

        //keep the one which is smaller.
        if (this.minCN > convertedMinVbl.minCN || (this.minCN == convertedMinVbl.minCN && this.minI > convertedMinVbl.minI)) {
            this.minCN = convertedMinVbl.minCN;
            this.minI = convertedMinVbl.minI;
        }
    }

    /**
     * Custom reduce function with two integer input to assign and reduce the
     * variable for when it is executed in the thread.
     *
     * @param compareCN Collatz Conjecture number.
     * @param number    the number on which CN was computed.
     */
    public void reduce(long compareCN, long number) {

        //keep the one which is smaller.
        if (this.minCN > compareCN || (this.minCN == compareCN && this.minI > number)) {
            this.minCN = compareCN;
            this.minI = number;
        }
    }

    /**
     * sets the thread variable to the one provided by the pj2 library via vbl
     * object.
     *
     * @param vbl Reduction interface object.
     */
    public void set(Vbl vbl) {
        MinVbl convertedMinVbl = (MinVbl) vbl;
        this.minCN = convertedMinVbl.minCN;
        this.minI = convertedMinVbl.minI;
    }
}


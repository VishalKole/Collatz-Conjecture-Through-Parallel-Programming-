//******************************************************************************
//
// File:    MaxVbl.java
//
// This Java source file is part of the parallel programming assignment 1 for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Vbl;

/**
 * Class MaxVbl provides a reduction variable for a CollatzSmp class shared by
 * multiple threads executing a parallel loop.
 * <p>
 * Class MaxVbl uses the reduction pattern as implemented by the library.
 * Local threads are created using the threadLocal method and the reduction
 * is handled by the library after the parallel loop execution is done. It uses
 * reduction function to merge the threads.
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class MaxVbl implements Vbl {

    long maxCN, maxI;

    /**
     * Clone the current thread. Interfaces' required function.
     *
     * @return return a new deep copy clone of current object
     */
    public Object clone() {
        try {
            MaxVbl vbl = (MaxVbl) super.clone();
            vbl.maxCN = this.maxCN;
            vbl.maxI = this.maxI;
            return vbl;

        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("MaxVbl clone error");
        }
    }

    /**
     * reduces and stores the maximum of the Vbl object provided and the current
     * object. Interfaces' required function.
     *
     * @param vbl Reduction interface object.
     */
    public void reduce(Vbl vbl) {
        MaxVbl convertedMaxVbl = (MaxVbl) vbl;

        //keep the one which is higher.
        if (this.maxCN < convertedMaxVbl.maxCN || (this.maxCN == convertedMaxVbl.maxCN && this.maxI < convertedMaxVbl.maxI)) {
            this.maxCN = convertedMaxVbl.maxCN;
            this.maxI = convertedMaxVbl.maxI;
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

        //keep the one which is higher.
        if (this.maxCN < compareCN || (this.maxCN == compareCN && this.maxI < number)) {
            this.maxCN = compareCN;
            this.maxI = number;
        }
    }

    /**
     * sets the thread variable to the one provided by the pj2 library via vbl
     * object.
     *
     * @param vbl Reduction interface object.
     */
    public void set(Vbl vbl) {
        MaxVbl convertedMaxVblVbl = (MaxVbl) vbl;
        this.maxCN = convertedMaxVblVbl.maxCN;
        this.maxI = convertedMaxVblVbl.maxI;
    }
}

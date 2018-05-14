# Collatz-Conjecture-Through-Parallel-Programming

Suppose you execute the following loop, given an initial value for n ≥ 1:
```
while n > 1: 
    If n is even: 
        n ← n/2 
    Else: 
        n ← 3n + 1
```
The loop terminates when n becomes 1

The **Collatz Conjecture**, posed by German mathematician Lothar Collatz in 1937, is that the loop will always terminate, no matter what the initial value of n (≥ 1) is. Although the conjecture has been verified experimentally for all n up to 5×2^60, no one has been able to prove or disprove the conjecture in general.

A "Collatz sequence" is the sequence of values for n, from the initial value to the final value of 1. The "Collatz number," denoted C(n), is the length of the Collatz sequence starting with n. Here are some examples:



n | Collatz sequence | C(n)
--- | --- | ---
1   | 	1  |  	1
10   | 	10, 5, 16, 8, 4, 2, 1   | 	7
100  |  	100, 50, 25, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1   | 	26


### Program Input and Output

This project has a sequential program and a multicore parallel program to calculate C(n) for a series of initial n values. The program's command line arguments are a lower bound lb and an upper bound ub. For each value of n from lb to ub inclusive, the program calculates C(n). All calculations will use type long. If any calculation overflows the range of values for type long, the program's behavior is not specified.

The program prints two lines of output on the console, in the format shown below.
```
C(<min_n>) = <min_C_n>
C(<max_n>) = <max_C_n>
```
`<min_C_n>` is replaced with the smallest C(n) value encountered.
`<min_n>` is replaced with the initial n value corresponding to min_C_n; if there is more than one such n value, `<min_n>` is replaced with the smallest such n value.
`<max_C_n>` is replaced with the largest C(n) value encountered.
`<max_n>` is replaced with the initial n value corresponding to max_C_n; if there is more than one such n value, `<max_n>` is replaced with the largest such n value.

Here are some examples:
```
$ java pj2 CollatzSeq 1000 1000000
C(1024) = 11
C(837799) = 525
$ java pj2 CollatzSeq 2000000 2000000
C(2000000) = 154
C(2000000) = 154
```

### Software Specification

The sequential version of the program runs by typing this command line:  
```
java pj2 CollatzSeq <lb> <ub>
```
* `<lb>` is the lower bound for the initial n value; it must be a long integer ≥ 1 (type long).
* `<ub>` is the upper bound for the initial n value; it must be a long integer ≥ <lb> (type long).

The parallel version of the program must be run by typing this command line:
```
java pj2 cores=<K> CollatzSmp <lb> <ub>
```
* `<K>` is the number of CPU cores to use; by default, this is also the number of threads in the parallel thread team.
* `<lb>` is the lower bound for the initial n value; it must be a long integer ≥ 1 (type long).
* `<ub>` is the upper bound for the initial n value; it must be a long integer ≥ <lb> (type long).

**If the command line does not have the required number of arguments, if any argument is erroneous, or if any other error occurs, the program must prints an error message on the console and terminate.**

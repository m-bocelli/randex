package edu.udel.cisc675.randex;

/* Module RandomizeProblems.  Given the number of problems nprob, and
   a random number generator, constructs an array of length nprob
   which is a permutation of the integers 0..nprob-1.  Uses the
   Fisher-Yates algorithms.  See
   https://en.wikipedia.org/wiki/Fisher–Yates_shuffle. */
public class RandomizeProblems {

    /* The number of problems (in) */
    int nprob;

    /* Permutation of problem IDs (out) */
    int[] probPerm;

    FisherYatesShuffle fys;

    /* Constructs new instance from given fields.  Sets fields only,
       does nothing else. */
    public RandomizeProblems(int nprob, FisherYatesShuffle fys) {
        this.nprob = nprob;
        this.fys = fys;
    }

    /* Constructs the probPerm. */
    public void execute() {
        probPerm = new int[nprob];
        fys.shuffleArray(probPerm); 
    }
}

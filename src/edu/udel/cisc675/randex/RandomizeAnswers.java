package edu.udel.cisc675.randex;

/* Module RandomizeAnswers: for each problem i, constructs a random
   permutation of 0..m-1, where m is the number of answers to problem
   i.  Uses Fisher-Yates algorithm; see See
   https://en.wikipedia.org/wiki/Fisher–Yates_shuffle. */
public class RandomizeAnswers {

    /* For each i in 0..numProblems - 1, the number of answers to
     * problem i (in). */
    private int[] numAnswers;

    /* A permutation for each problem (out) */
    public int[][] answerPerms;

	  private FisherYatesShuffle fys;

    /* Constructs new instance from given fields; sets the fields and
       does nothing else. */
    public RandomizeAnswers(AnswerStorage answerStorage, int nprob, FisherYatesShuffle fys) {
      this.fys = fys;
      this.numAnswers = new int[nprob];
      for (int i=0; i<nprob; i++)
        numAnswers[i] = answerStorage.getNumAnswers(i);
    }

    /* Constructs random permutations for each problem */
    public void execute() {
      int nprob = numAnswers.length;
      answerPerms = new int[nprob][];
      for (int i=0; i<nprob; i++) {
        answerPerms[i] = new int[numAnswers[i]];
        fys.shuffleArray(answerPerms[i]);
      }
    }
}

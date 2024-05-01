package edu.udel.cisc675.randex;

import java.io.PrintStream;

/* Module Output: given all data from previous phases, writes the
   randomized exam to an output stream. */
public class Output {

    /* Output stream to write to */
    private PrintStream out;

    /* The characters of the original file, from module Input */
    private FileStorage fileStorage;
    private ProblemStorage problemStorage;
    private AnswerStorage answerStorage;

     /* Permutation of problem IDs, from module RandomizeProblems */
    private int[] probPerm;

    /* For each problem, permutation of answers, from module
     * RandomizeAnswers */
    private int[][] answerPerms;

    /* Constructs new instance of Output from given fields; only sets
       fields and does nothing else */
    public Output(PrintStream out, FileStorage fileStorage, ProblemStorage problemStorage, AnswerStorage answerStorage,
		  int[] probPerm, int[][] answerPerms) {
        this.out = out;
        this.fileStorage = fileStorage;
        this.answerStorage = answerStorage;
        this.problemStorage = problemStorage;
        this.probPerm = probPerm;
        this.answerPerms = answerPerms;
    }

    /* Prints a range of characters to out, starting at start, ending
     * at stop-1 */
    private void printRange(int start, int stop) {
        for (int i=start; i<stop; i++)
            out.print(fileStorage.getChar(i));
    }

    /* Prints the aid-th answer of problem pid */
    private void printAnswer(int pid, int aid) {
	    printRange(answerStorage.getAnswerStartsI(pid, aid), answerStorage.getAnswerStopsI(pid, aid));
    }

    /* Prints problem pid, with answers permuted */
    private void printProblem(int pid) {
        int nanswer = answerStorage.getNumAnswers(pid);
        if (nanswer == 0) {
            printRange(problemStorage.getProblemStartsI(pid), problemStorage.getProblemStopsI(pid));
            return;
        }
        printRange(problemStorage.getProblemStartsI(pid), answerStorage.getAnswerStartsI(pid, 0));
        for (int i=0; i<nanswer; i++)
            printAnswer(pid, answerPerms[pid][i]);
        printRange(answerStorage.getAnswerStopsI(pid, nanswer-1), problemStorage.getProblemStopsI(pid));
    }
    /* Prints the entire exam with permuted problems and permuted
     * answers */
    public void execute() {
        int nprob = problemStorage.getProblemStarts().length;
        printRange(0, 2);
        if (nprob == 0) {
            printRange(0, fileStorage.getChars().length);
        } else {
            printRange(0, problemStorage.getProblemStartsI(0));
            for (int i=0; i<nprob; i++) {
                printProblem(probPerm[i]);
                if (i<nprob-1)
                    printRange(problemStorage.getProblemStopsI(i), problemStorage.getProblemStartsI(i+1));
            }
            printRange(problemStorage.getProblemStopsI(nprob-1), fileStorage.getChars().length);
        }
    }
}

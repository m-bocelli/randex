package edu.udel.cisc675.randex;
import java.util.ArrayList;

/* Module FileStorage: hides the design decision behind
 * storing the contents of the latex file uploaded provided
 * in the CLI.
 */
public class AnswerStorage {
    public final static char[] beginEnumerate =
	"\\begin{enumerate}".toCharArray();
    
    public final static char[] item = "\\item".toCharArray();
    
    public final static char[] endEnumerate =
	"\\end{enumerate}".toCharArray();

    private static AnswerStorage instance;

    /* For each problem i, start index of each answer to the problem
       (out).  This is an array of length number of problems.
       answerStarts[i] is an array whose length is the number of
       answers to the i-th problem.  answerStarts[i][j] is the index
       in chars of the first character of the j-th answer to the i-th
       problem. */
    private int[][] answerStarts;

    /* Like answerStarts, except it gives the stop index of each
       answer (out).  That is 1 greater than the index of the last
       character of the answer. */
    private int[][] answerStops;

    private AnswerStorage() {}

    public static AnswerStorage getInstance() {
        if (instance == null)
            instance = new AnswerStorage();
        return instance;
    }

    public void setAnswers(ArrayList<Integer> starts, ArrayList<Integer> stops, int i) {
        answerStarts[i] = ToArray.toArray(starts);
		answerStops[i] = ToArray.toArray(stops);
    }

    public void initAnswers(int size) {
        answerStarts = new int[size][];
		answerStops = new int[size][];
    }

    public int getNumAnswers(int i) { return answerStarts[i].length; }

    public int getAnswerStartsI(int i, int j) { return answerStarts[i][j]; }

    public int getAnswerStopsI(int i, int j) { return answerStops[i][j]; }
}
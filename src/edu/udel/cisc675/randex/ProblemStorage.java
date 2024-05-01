package edu.udel.cisc675.randex;
import java.util.ArrayList;

/* Module FileStorage: hides the design decision behind
 * storing the contents of the latex file uploaded provided
 * in the CLI.
 */
public class ProblemStorage {
    /* "\begin{problem}" as character array. */
    public final static char[] beginProblem =
	"\\begin{problem}".toCharArray();

    /* "\end{problem}" as character array. */
    public final static char[] endProblem =
	"\\end{problem}".toCharArray();

    private static ProblemStorage instance;

    private int[] probStarts;
    private int[] probStops;

    private ProblemStorage() {}

    public static ProblemStorage getInstance() {
        if (instance == null)
            instance = new ProblemStorage();
        return instance;
    }

    public void setProblems(ArrayList<Integer> start, ArrayList<Integer> stop, int size) {
        probStarts = ToArray.toArray(start);
		probStops = ToArray.toArray(stop);
    }

    public int[] getProblemStarts() { return probStarts; }

    public int getProblemStartsI(int i) { return probStarts[i]; }
    public int getProblemStopsI(int i) { return probStops[i]; }
}
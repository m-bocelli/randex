package edu.udel.cisc675.randex;
import java.util.ArrayList;

/* Module FindProblems: finds all problems (text beginning with
   "\begin{problem}" and ending with "\end{problem}") in the given
   character array.  Constructs arrays probStarts and probStops.  Each
   has length n, the number of problems.  probStarts[i] is the index
   in chars of the first character of problem i.  probStops[i] is 1
   more than the index in chars of the last character of problem i.

   The client should instantiate this class with the chars produced
   by module Input, then call method execute, then read the fields
   probStarts and probStops. */
public class FindProblems {
    FileStorage fileStorage;
	ProblemStorage problemStorage;

    /* Constructor: sets chars field and does nothing else. */
    public FindProblems(FileStorage fileStorage, ProblemStorage problemStorage) {
		this.fileStorage = fileStorage;
		this.problemStorage = problemStorage;
    }

    /* Determines whether the sequence of characters in chars starting
       at offset off matches the chars of sought. */
    private static boolean match(char[] chars, int off, char[] sought) {
		for (int i=0; i<sought.length; i++) {
			if (off+i >= chars.length || sought[i] != chars[off+i])
			return false;
		}
		return true;
    }

    /* Constructs probStarts and probStops. */
    public void execute() {
		ArrayList<Integer> startList = new ArrayList<>(),
			stopList = new ArrayList<>();
		int n = fileStorage.getChars().length;
		boolean inProblem = false; // is i currently inside a problem?
		for (int i=0; i<n; i++) {
			if (TextMatcher.match(fileStorage.getChars(), i, ProblemStorage.beginProblem)) {
				if (inProblem)
					throw new RuntimeException("Encountered \\begin{problem} when inside a problem");
				startList.add(i);
				inProblem = true;
			} else if (TextMatcher.match(fileStorage.getChars(), i, ProblemStorage.endProblem)) {
				if (!inProblem)
					throw new RuntimeException("Encountered \\end{problem} when outside any problem");
				inProblem = false;
				stopList.add(i + ProblemStorage.endProblem.length);
			}
		}
		if (inProblem)
			throw new RuntimeException ("Missing \\endProblem for last problem");
		int nprob = startList.size();
		assert nprob == stopList.size();

		problemStorage.setProblems(startList, stopList, nprob);
    }
}

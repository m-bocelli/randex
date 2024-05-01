package edu.udel.cisc675.randex;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.random.RandomGenerator;

/* Module Randex: the main module of the Randex app, which marshals
   togethers all the other modules to perform a complete exam
   generation task: reads the original file, randomizes problems and
   answers, and writes the resulting randomized exam. */
public class Randex {

    /* Name of file to read (command line argument 0) */
    private String filename;

    /* Random seed (command line argument 1) */
    private long seed;

    /* Random number generator */
    private RandomGenerator rand;
	private FisherYatesShuffle fys;

    // The other modules that will be instaniated and executed...
    
	private FileStorage fileStorage;
	private ProblemStorage problemStorage;
	private AnswerStorage answerStorage;
    private Input input;
    private FindProblems findProblems;
    private FindAnswers findAnswers;
    private RandomizeProblems randomizeProblems;
    private RandomizeAnswers randomizeAnswers;
    private Output output;

    /* Constructs new instance by setting filename and see fields;
       does nothing else. */
    public Randex(String filename, long seed) {
		this.filename = filename;
		this.seed = seed;
    }

    /* Prints a 1d-array of ints (for debugging) */
    private static void print(PrintStream out, int[] a) {
		out.print("{ ");
		for (int x:a) out.print(x+" ");
		out.print("}");
    }

    /* Prints a 2d-array of ints (for debugging) */
    private static void print(PrintStream out, int[][] a2d) {
		out.print("{ ");
		for (int[] a:a2d) {
			print(out, a);
			out.print(" ");
		}
		out.print("}");	
    }

    /* Executes a complete tasks: instantiates all the modules with
       the approriate arguments, executes them in the appropriate
       order, writing output to standard out for now. */
    private void execute() throws FileNotFoundException, IOException {
		PrintStream out = System.out;

		rand = new Random(seed);
		fys = new FisherYatesShuffle(rand);

		fileStorage = FileStorage.getInstance();
		problemStorage = ProblemStorage.getInstance();
		answerStorage = AnswerStorage.getInstance();

		input = new Input(filename, fileStorage);
		input.execute();

		findProblems = new FindProblems(fileStorage, problemStorage);
		findProblems.execute();

		findAnswers = new FindAnswers(fileStorage, problemStorage, answerStorage);
		findAnswers.execute();

		int nprob = problemStorage.getProblemStarts().length;

		randomizeProblems = new RandomizeProblems(nprob, fys);
		randomizeProblems.execute();

		randomizeAnswers = new RandomizeAnswers(answerStorage, nprob, fys);
		randomizeAnswers.execute();

		Output output = new Output
			(out, fileStorage, problemStorage, answerStorage,
			randomizeProblems.probPerm,
			randomizeAnswers.answerPerms);
		output.execute();
    }

    /* Main method: command line arguments are: filename, seed */
    public final static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length != 2) {
			System.out.println
			("Usage: java edu.udel.cisc675.randex.Test <filename> <seed>");
			System.exit(1);
		}
		String filename = args[0];
		long seed = Long.decode(args[1]);
		//System.out.println("Seed = "+seed);
		Randex randex = new Randex(filename, seed);
		randex.execute();
    }
}

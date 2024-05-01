Michael Bocelli

		     Randex: The Exam Randomizer

Critique of Design v1

    Perhaps the most glaring design flaw of v1 is the lack of information hiding, a criteria of modularization 
    emphasized in papers like "On the Criteria To Be Used in Decomposing Systems into Modules" by D.L. Parnas.
    For example, the design decision of how to store the problems, answers, and file. This decision is exposed 
    in 4 different modules (FindAnswers, FindProblems, Output, and Randex), meaning if one was to update how 
    the data is stored, all of these modules would need to be updated. This decision of data storage should 
    be hidden such that when changed, no other modules need to be altered as a result.

    The system also violates one of Parnas's points in how a lack of subsets and extensions in a system 
    can manifest (mentioned in his paper "Designing Software for Ease of Extension and Contraction"). 
    This would be "Components That Perform More than One Function". A clear example of this 
    is the toArray(...) method within FindAnswers. This is a simpler component which can be used 
    in many others that would not need to use FindAnswers, so it would best be moved to a utility
    component and provided as an interface. Subsequently, the amount of functions that FindAnswers 
    perform would be reduced.

    Additionally, there is potential for code to be reused across compnents with new uses relations. Specifically,
    components FindProblems and FindAnswers both implement a similar match(...) method, whereas RandomizeAnswers 
    and  RandomizeProblems both implement the Fisher-Yates shuffle. By hiding these design decisions in separate 
    components that the aforementioned classes could use via interfaces, the program could have a reduced learning 
    curve for developers new to the code base, as mentioned by Gamma et al. in their paper "Design Patterns: 
    Abstraction and Reuse of Object-Oriented Design".

Desgin v2
    
    FileStorage:
        Desc - Singleton class which abstracts the way in which file content is stored in the system.

        Secrets - Hides the decision of how the file text is stored/represented and in what ways it is 
        read/written by the user.

        Uses - Does not use any other module.

        Interface - getInstance() which returns to the user the single instance of the file data persistent
        throughout the application. getChar(int i) which returns the character from the file at index i.
        getChars() which returns the entire string of characters from the file. setFileContents(char[] fileChars)
        which updates the sting of characters to whatever is passed in as an argument.

    ProblemStorage:
        Desc - Similar to FileStorage, provides an interface in which users can read and write data pertaining
        to strings of characters representing problems.

        Secrets - Hides the design decision behind how problems are represented and stored.

        Uses - java.util.ArrayList and ToArray module.

        Interface - getInstance() same as FileStorage but for the ProblemStorage singleton. 
        setProblems(ArrayList<integer> start, ArrayList<Integer> stop, int size) initializes the arrays of
        start and stop indices for the problems via leveraging the ToArray module. getProblemStarts() returns
        an array of all problem starting indices. getProblemStartsI(int i) and getProblemStopsI(int i) return
        a specific index. beginProblem and endProblem which are constant, static variables that specify what
        the beginning and end of a problem string looks like.

    AnswerStorage:
        Desc - Final singleton module which provides an interface for other modules to access the starting
        and stopping points of answers in the original file string.

        Secrets - Hides the decision of how these indices are stored and how they are read.

        Uses - import java.util.ArrayList and ToArray module.

        Interface - Constant, static variables beginEnumerate, item, and endEnumerate which specify formats
        of answers in the original file string. getInstance() to retrieve the singleton instance.
        setAnswers(ArrayList<Integer> starts, ArrayList<Integer> stops, int i) which updates the 
        arrays of answer starting and stopping points in the original file string. getNumAnswers(int i) which
        returns the number of answers in a given problem. getAnswerStartsI(int i, int j) and
        getAnswerStartsI(int i, int j) which collectively returns the bounds of a single answer.

    ToArray:
        Desc - Handles conversion of an ArrayList to an Array.

        Secrets - Hides the decision regarding how this conversion takes place.

        Uses - java.util.ArrayList

        Interface - toArray(ArrayList<Integer> list) which converts the given ArrayList to an Array of type
        int[].

    TextMatcher:
        Desc - Class which handles the detection of matches between two given strings starting at an offset
        from the first.

        Secrets - The algorithm used to detect matches.

        Uses - Nothing.

        Interface - match(char[] chars, int off, char[] sought) returns true if the substring starting from
        chars[off] matches the string sought.

    FisherYatesShuffle:
        Desc - Responsible for handling the Fisher-Yates shuffle algorithm of an array.

        Secrets - The implementation details of the Fisher-Yates shuffle algorithm as well as the random
        number generation used.

        Uses - java.util.random.RandomGenerator

        Interface - FisherYatesShuffle(RandomGenerator rand) constructor which initializes the random
        number generator. shuffleArray(int[] arr) which takes in an integer array and shuffles it
        according to the algorithm.

    Pre-existing module changes:
        FindProblems USES java.util.ArrayList, FileStorage, ProblemStorage, TextMatcher
        FindAnswers USES java.util.ArrayList, FileStorage, ProblemStorage, AnswerStorage, TextMatcher
        Input USES java.util.Arrays, java.io.FileReader, java.io.FileNotFoundException,
                    java.io.IOException, FileStorage
        RandomizeProblems USES FisherYatesShuffle
        RandomizeAnswers USES FisherYatesShuffle
        Output USES FileStorage, java.io.PrintStream, ProblemStorage, AnswerStorage
        Randex USES all modules except itself, TextMatcher, ToArray

Anticipation of Change
    1. Reading multiple files at once
        - For this feature to be implemented, design 1 would simply need to iterate its
        processing flow over all files sent to the command line. It would require a new
        module to store the intermediate and collective results. Design 2 would need
        to alter the singleton storage modules so that they can be instantiated more
        than once, but since it already as storage we could simply iterate.

    2. Ignore comments
        - To implement this in design 1, we would need to either check for the
        comments in both FindAnswers and FindProblems, or create a whole new
        module. Luckily, in design 2 we have created a module specifically for
        matching, so we would simply need to leverage this module when scanning
        for problems to look for the special comment characters.

    3. Allow short essay problems
        - This would also come down to a matching problem. In design 1, if we have
        failed to identify an "item" tag, we can conditionally move to counting the
        space between this problem's start and its end as the short essay content.
        The solution in design 2 can be similar, as we can use the response of 
        our TextMatcher module as the condition in which we count the body
        of two enclosing problem tags as our essay content.

To build: change in to directory src/edu/udel/cisc675/randex and type
"make".  Type "make test1" to run a test.  See the Makefile for
further details.

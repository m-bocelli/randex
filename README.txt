Michael Bocelli

		     Randex: The Exam Randomizer

Critique of Design v1

    Perhaps the most glaring design flaw of v1 is the lack of information hiding, a criteria of modularization 
    emphasized in papers like "On the Criteria To Be Used in Decomposing Systems into Modules" by D.L. Parnas.
    For example, a design decision here was to store the start and stop indices of each problem and answer in four
    different array data structures. However, this decision is exposed in 4 different modules 
    (FindAnswers, FindProblems, Output, and Randex), meaning if one was to update how the data is stored, all of
    these modules would need to be updated. This decision of data storage should be hidden such that when changed,
    no other modules need to be altered as a result.

    The system also violates one of Parnas's points in how a lack of subsets and extensions in a system can manifest
    (mentioned in his paper "Designing Software for Ease of Extension and Contraction"). This would be "Components That
    Perform More than One Function". A clear example of this is the toArray(...) method within FindAnswers. This is a simpler
    component which can be used in many others that would not need to use FindAnswers, so it would best be moved to a utility
    component and provided as an interface. Subsequently, the amount of functions that FindAnswers perform would be reduced.

    Additionally, there is potential for code to be reused across compnents with new uses relations. Specifically,
    components FindProblems and FindAnswers both implement a similar match(...) method, whereas RandomizeAnswers and 
    RandomizeProblems both implement the Fisher-Yates shuffle. By hiding these design decisions in separate components
    that the aforementioned classes could use via interfaces, the program could have a reduced learning time for developers
    new to the code base, as mentioned by Gamma et al. in their paper "Design Patterns: Abstraction and Reuse of
    Object-Oriented Design".

Desgin v2
    

To build: change in to directory src/edu/udel/cisc675/randex and type
"make".  Type "make test1" to run a test.  See the Makefile for
further details.

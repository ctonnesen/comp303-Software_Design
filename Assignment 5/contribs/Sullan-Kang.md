**Reviewer for problem 1: Enhanced Playable Type Hierarchy**

My suggestions for problem 1 can be found in #2, !2, and !3.
*  Discussed the design trade-offs between using interface and creating an abstract class.
*  Suggested that an abstract class extends the interface, following the lecture material.
*  Reviewed the merge request and consulted assignment instruction to make a note on our design decision. (https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/2#note_30782)
*  Reviewed test codes of problem 1, and discussed how to possibly improve setTitle() in the problem solution.


**Implementer (+Reviewer) for problem 2: Unified Playing Algorithm**

My design decision and implementation for problem 2 can be found in #3 and !4.
*  Built up the `TEMPLATE METHOD` design pattern on the method play() with co-implementer Hamza. (https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/issues/3#note_30445)
*  Implemented playContent() to isolate the varying step inside play().
*  Modified duration() to include silence time of different Playable objects, and considered simplifying it.
*  Refactored different variables and methods from problem 1 and 2 to keep the hierarchy and convention.


**Tester (+Implementer) for problem 3: Undo/Redo of Library Modification Actions**

My attempts to test the problem 3 can be found in !6.
*  Reorganized all the previous test codes into class-by-class mirror tests.
*  Wrote initial test code, which failed at an edge case (https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/6#note_33065).
*  Test was written at a quite late time, so worked with Hamza to find the reason of test failure.
*  Tried to fix the source code without breaking the `COMMAND` pattern and the composite behaviour (https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/6#note_33150) (https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/6#note_33187)
*  Wrote final tests which all passed with the implementation modified through !6
## Main Contributions
### Implementer (problem 2)
* Implemented flyweight design pattern for `Artist` class along with a Client class showing use cases. All commits containing the evolution of the implementation can be found as part of these two merge requests: https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/2 and https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/3.
* Main chapter concepts being showcased in the design are:
	* Flyweight design pattern
	* Modelling absent values with the use of optional types
	* Implementing ideas from the Null object pattern (note that the `Artist` class's implementation of null object pattern was done as a direct consequence of Christian's work on the `Album` class for problem 1, as seen [here](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/1/diffs?commit_id=24b1e59f82c393de2f204d193b1aa34e6f1ac985) and discussed [here](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/2#note_14701))
	* Use of final fields
	* Object uniqueness (no two generated objects are equal, as per the flyweight pattern)
* Had lengthy discussions regarding the design and made improvements to it based on these conversations, as seen in two MR links above as well as in [this issue](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/3).
* As mentioned in [this comment](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/1#note_15935), given the fact that this problem was clearly divisible into two parts, I implemented the `Artist` class while Wen Qi implemented `Genre`. We compared and tried to optimize the two designs while also allowing them each to have some distinguishing features, but as can be seen from timestamps in the commit history, the implementation for `Genre` from start to finish was done after I had completed implementing `Artist` individually.

### Reviewer (problem 3)
* Reviewed an implementation for the `Library` class in [this comment](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/4#note_16528), and added some additional comments on [this MR](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/4).

### Discussant (problem 1)
* Discussed the points made by a reviewer for this problem and discussed the problem's overall implementation in [this comment](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/2#note_14665), which lead to further discussion and changes made to code as seen in this same issue.
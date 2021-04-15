Problem 1 

* [Discussed](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/2#note_18420) the advantages of the current implemetation and considered the use of builder pattern, but the null object pattern is more efficient for our case

Problem 3

* proposed the [initial state diagram](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/4#note_15423) which was pointed out to be incomplete. I agree with the modified diagram since mine was missing the state of **empty(backed up)**
* implemented all methods for problem 3 requirements which were modified according to review by second implementer 

Problem 4

* At first, agreed with enum implementation, but upon reviewing, agreed that the first implementation would be a better design.
* [Reviewed](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/5#note_17416) the code in master and suggested using the comparison code seen in the enum implementation to avoid the use of indexes when it comes to comparing `Album` and `Playlist` content. Since they implement iterable, I think we should take advantage of enhanced loops. 
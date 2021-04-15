**Implementer**

I'm the implementer for problem 3, the merge request is in https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/1. At first I used 2 methods which return anonymous Command class, one represents `addComand` and the other one represents `RemoveCommand`. In addition, I made a `compositeCommand` class to let client be able to undo multiples commands in reverse order. Later I discussed with the reviewer the reason I implemented like this, and generally we are in agreement with the implementation. At last, we decided to add a `compositeCommand` attribute in the library so it has the record to all the commands. This signals the very first state of a library, and clients can decide the states they want by creating other composite instances.

**Reviewer**

About problem 1, I made a review in https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/2#note_30780 and I talked about things we can do to improve the functionality of the abstract class, it is generally agreed and this part is realized in my test branch: !3.

About problem 2, my review is at https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/issues/3#note_31909. I found the implementation to be pretty complete and well-organized, and I come up with things that make it more consistent with implementation of problem 1, as they are related to some extent.


**Tester**

I'm the tester for Problem 1 and my test code is in https://gitlab.cs.mcgill.ca/mnassif/303a5t22/-/merge_requests/3. I not only tested the methods of reset, increment, and obtain the playcount but also those related to the `atitle` attribute which is new in the abstract class. All Junit tests passed, which is within our expectation.

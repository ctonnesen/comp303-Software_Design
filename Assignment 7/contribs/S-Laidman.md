# Problem 2 Implementation
* Suggested the decorator / constructor pattern (along with @ohaber) : https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39061
* Suggested using flyweight design pattern for the concrete MenuItems https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39325
* Implemented an Template Design and abstract object pattern for shared functionality and consistent description formatting: https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_40041, !3 MR and discussion, including UML of design
* Discussion on why having a Template Super method might not be effective code reuse. https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/3#note_41081
* Small changes to functionality using streams. 

#Problem 4 Review
* Encouraged good code reuse for the various factory filters : https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/8#note_41948
* Suggested a compartmentalizing of each configuration panel, instead of having them need to be predefined in a start() method in the same review. 
* Included point for effective reuse of already implemented methods : https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/8#note_42021
* Suggestions for avoiding NullObjectExceptions and magic number antipaterns in the way that the configurations were being presented and manipulated. https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/17#note_43917

#Problem 3 Test
* Used reflective programming for testing the section 3, and made effective reuse of code: !34
* JUnit tests built for coverage of relevant portions of the code.
* Make tests for names case independent in the same !34

#General
* Added a sample menu for ease of testing, containing examples from all of our enumerated types to examine sorting and printing of various items. 
* Documenting code here : https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/DocumentingCode
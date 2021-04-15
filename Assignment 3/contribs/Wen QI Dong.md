## Main Contributions
### Implementer (problem 2)
* Implemented the Genre class using flyweight design as the main approach to ensure uniqueness. Because private constructors, all new creation of the object will have use get() methods which checks for existance of Genres with the same name. Creation of Genre cases in the client code. See merge request: https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/8
* Genre was implemented after the 'Artist' Class which shared similarities in basic design structure such as flyweight pattern and Null object pattern. 
*Discussion about possible improvement of the class

### (problem 4)
* Suggested new implemention for duplication checking to use enum class instead retuning boolean values for the anonymous classes decleared in album and playlist. See branch: https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/tree/Problem4
* Related concepts included:
    *final fields
    *anonymous class
    *flyweight 
* This version of the implemention was ultimately not used due to the issue of some enum type not used for song duplication 

### Reviewer (problem 3)
*Reviewed the updated version. Fixed syntac errors in the album class. Checked results of the requried methods in client code and made adjustments/ improvements. See commit:https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/merge_requests/4/diffs?commit_id=e283f70b800efef5dfdb9b40a0bc46d79de2fcf2

### Discussant (problem 4)
*Confirmed on general design of using interface instead of overriding .equals and usage of anonymous class. Discussed the pros/cons of different implementations in issue #4. See comments:https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/5#note_16374 and https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/5#note_16455



For this assignment I was mainly an implementer of area 4, tester for area 2, and reviewer for area 1, however had some reviews in other implementations of area 4.

**Implementations**
- I decided to add on to @ajanev design as I thought it was a good implementation for this problem. 
- I added the third required configuration that the user should be able to choose in the UI. Also, I added many different selection strategies for on sale/on special items. 
- The first implementation of this was in branch [DanielP4ThirdConfig](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/DanielP4ThirdConfig)
- Since another implementation of area 4 involving a configuration class was added, I adjusted my previous implementation to suit the new one in branch [DanielP4ThirdConfigV2](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/DanielP4ThirdConfigV2). 

**Reviews**
My Reviews can be found here:
- [Review 1](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/1#note_40160) This was a review of [Oliver-P1](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/Oliver-P1), the main concern was that there was a lot of code duplication which was later fixed, and adding UI functionality.
- [Review 2](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/17#note_43856), this was my review of one of the area 4 implementations, the UI suggestions I provided were later implemented.

**Tests**
- My original versions of my tests for area 2 were created in branch [P2-Tests-DG](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/P2-Tests-DG/) however I later moved my tests into ComboMenuItemTest.java and SizedMenuItemTest.java in branch [P2TestsDaniel](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/P2TestsDaniel) in order to have proper test code organization, followed by this [merge request](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/31). 
- There was one failed test in ComboMenuItemTest.java with the test method testGetDietCategory() as the ArrayList<DietCategory> kept giving an empty list when trying to access the diet categories of the ComboMenuItem.
- Took @ajanev feedback in [this](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/31) merge request and made the changes and created a new [merge request](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/33). Was mainly changes to naming conventions and some duplicate test and null test removal.
[Designated Roles](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/2). 

**Note: My commits are from both @ctonne2 and @Appellus (second computer)**

### - Problem 1: Tester ###
  - [Responded](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/3#note_38992) to initial idea of implementation, suggesting the usage of the inheritance to create sizable and non-sizable items, while reviewing the merits of a `COMPOSITE` and `DECORATOR` Design pattern. 
     - Also touched upon the idea of using an Abstract superclass, which was later implemented
     - Also touched upon the idea of using an Abstract superclass, which was later implemented
     - [Further](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/3#note_39080) clarified my inheritance model with JetUML diagrams
       - Also discussed the idea of using the `NULL OBJECT Design Pattern` vs. Optional Types
  - [Proposed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39036) the idea of a `ComboItem` Food Type with a class declaration header
  - [Proposed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39489) the usage of the `FLYWEIGHT Design Pattern` for a static item store as two menu items should not have the same name given the design choices we made.
  - As part of [A2's implementation](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commits/V1Problem2--SpecialDomainObj(Implementer--Christian)), I also needed to create a `MenuItem` class and implement the `FLYWEIGHT` pattern
  - [Gave](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/1#note_41060) idea for reducing code duplication and how to handle the sorting of lists based on Predicates
  - [Branched](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commits/P1-OffShoot--ctonne2) off and made version utilizing a wrapper class to keep track of sortedLists
  - [Created branch](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/5#note_43564) to clean up code and that utilized loops to dynamically include new `FoodType` and `DietCategory` enums. It also reorganized all the Predicates
  - [Created branch](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commits/A1-Testing) for JUnit test classes that achieved 100% coverage of `MenuItem` and `AbstractMenuItemSource`
    - This was later rebased with @xliu162's branch to test for more possible cases
  - [Added](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/42) test cases for the filters using Predicates


### - Problem 2: Implementer ###
  - [Agreed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39088) to the usage of the a `COMPOSITE` and `DECORATOR` Design pattern in regard to implementing `MenuItem` vs. `SizedMenuItem` to stop the problem of two objects of the same representation existing 
  - [Implemented](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/tree/V1Problem2--SpecialDomainObj(Implementer--Christian)) first draft, building off the discussions from earlier and utilizing an interface, `COMPOSITE/DECORATOR Design Pattern`, and `FLYWEIGHT Design Pattern`
  - [Outlined](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_39231) the difference between an inheritance model vs. a `COMPOSITE` and `DECORATOR` version with a focus on the design pros and cons on the latter in being able to access the original, on-sized Object
  - [Reviewed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_40141) @slaidm addition branched from my branch and gave input on the choice of using a `TEMPLATE Design Pattern` to handle the description
  - [Discussed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_40252) how to handle price calculation and if it should just rely upon the internal `MenuItem` in a `SizedMenuItem`
  - [Pushed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commits/V1Problem2--SpecialDomainObj(Implementer--SLaidman)) `ComboMenuItem` implementation to new A2 branch
  - [Responded](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/3#note_40623) to @slaidm's comment about the current implementation of `ComboMenuItem`
  - [Added](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commits/V2_Problem2_(Implementer--SLaidman)) to second version of the A2 implementation, with corrections specifically aimed at `ComboMenuItem`
  - [Explained](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/3#note_40672) how `ComboMenuItem` works and the design decision behind using a counting method of `DietCategory` tracking instead of ArrayList<> methods
  - [Responded](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/3#note_40839) to @ohaber's review and built on @slaidm's points, with a highlight on `TEMPLATE` method in the abstract class and usage of `ArrayList<>.removeAll()`
  - [Discussed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_43353) possible places where more stream functions could be implemented
    - [Discussed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/4#note_43503) tradeoff of using streams
  - Reviewed and merged [MR](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/9#note_43405), also reintroduced assert statements after discovering Java VM -ea argument
  - Reviewed and merged this [MR](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/27) for test cases and big fixes 

### - Problem 3: Reviewer ###
  - [Responded](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/5#note_39228) to question of how to implement dynamic changes with the answer of the `OBSERVER Design Pattern` 
  - [Gave](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/5#note_39492) an idea for what the possibility of creating a new item or combo item would look like from the GUI perspective
  - [Responded](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/5#note_39894) to idea of using selected items from listView to create `MenuItemCombo` objects 
  - [Gave](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/4#note_41008) unofficial review of A3 draft and how it interacts with the current A2 implementation
  - [Commented](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/4#note_41317) on idea of using selected items to create `ComboMenuItems` and an alternative GUI addition method that used a Popup
  - [Tested/Reviwed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/4#note_41319) the implementation and discussed the current shortcomings relating to adding `MenuItem`
  - [Gave](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/6#note_42220) official review and gave recommendations for GUI and pointed out issues for creating `SizedMenuItems`
  - Pushed [branch](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/28) that fixed all of the reccomendations posed in this [review](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/6#note_42220). This was denied in favor of not adding more stuff to test, although it adds functionality required by the problem statement
  - [Added](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/40) try-catch blocks to beautify the console during runtime exceptions

### - Problem 4: No Role ###
  - [Gave input](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/23#note_45174) and discussed design decisions of implementing `EnumMap` for hosting panel configurations, with an additional recommendation for a `FLYWEIGHT` store
  - [Cleaned](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commit/3b0106cdefc9665125570b40f186b45d5d63ed74) up the Comparator code with some functional programming
  - Reviewed and merged [this](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/23) MR
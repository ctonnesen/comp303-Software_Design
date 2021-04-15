### P1 Implementer

*  Implemented the code for problem 1 by using the composite design pattern so that menu could comprise menu items. (Outlined design choices here: #3) !1
* Created MenuItem class to store information representing Menu items that could be stored in the menu !1
* Created a Filters class to store filtering predicates !11, !22
* Created a filter() method in Menu to return a list of AbstractMenuItemSource that matched the unspecified number of predicates passed to the method !22
* Created GUI functionality for filtering the menu that was not used because we agreed it was not needed by the deliverables !11

### P2 Reviewer
* Reviewed and discussed how problem 2 should be implemented and the overall structure of our assignment, focusing on how to represent items that could have a size, how to store combo items, how to deal with empty values in our code, provided a UML to illustrate my ideas, discussed use of flyweight and equality for menu items,  inheritance, pricing of items, and how to filter lists of dietary types. #3, #4, !3

### P3 Tester
* Wrote tests to test the addItem, removeItem and createCombo functions. 4b1422b8
* Made sure the menu’s items were updated, and that the changed was observed by the GUI
* #8, 4b1422b8

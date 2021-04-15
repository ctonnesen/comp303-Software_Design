[Designated Roles](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/2). 

### Area 1: Reviewer ###
[Reviewed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/1#note_40128) problem 1.

[Reviewed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/22#note_45061) revised filter solution.

### Area 2: Tester & Reviewer ###
[Wrote Tests](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/commit/dc3478cbab77edebecd0871b22eb57553bf263e6) for Area 2.

Reviewed other tests for Area 2 [here](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/31) and [here](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/21#note_45050).


### Area 3: Discussant ###
[Discussed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/issues/6#note_43506) to find a solution on how to merge configurations with GUI implementation of Area 3.


### Area 4: Implementer & Reviewer ###

[Initial implementation](https://gitlab.cs.mcgill.ca/massif/303a7t1/-/merge_requests/8#note_42032) of configuration as objects that apply a functional strategy pattern. Initially my observer pattern
was using a callback method to update the configuration with the new list of items, and the configuration was an observer.
Discussed and revised on the merge thread.

[Revised initial implementation](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/17#note_44140) of problem 4 to fit with the initial implementation of problem 3. The observer MenuView
extended listview and updated the observable list within itself with every callback. I changed MenuView to still observe, 
but now aggregate three listviews for each panel, and apply an arraylist of function strategies (to represnet a configuration) to each listview with 
every callback. Discussed and revised on the merge thread.

[Reviewed](https://gitlab.cs.mcgill.ca/mnassif/303a7t1/-/merge_requests/23#note_45035) merge request regarding representing configurations as objects once more.
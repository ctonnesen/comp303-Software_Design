**Problem 1: Implementer - Composite Design Pattern, Optionals, Null Object Pattern**

https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/2#note_21441
Provided the fist implementation for this problem:
- Decided to use the Composite Design Pattern and have Playlist act as the Composite (it aggregates a number of different objects of the component type, Playable).
- I implemented the Null Object Pattern in Playable to allow for the creation of Null Playable Objects that would have by default 0 as duration, “Not available” as description and true as the return for isNull().
- In Song class, I made all attributes Optional except for the file of the Song. Suggested to use Null Object design Pattern for Artist and Genre.
- Provided printArtistName and printDuration methods to facilitate the unwrapping of Optional objects (facilitates the implementation of duration).
- Provided JetUML diagrams https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/2#note_21470

**Problem 2: Reviewer - Decorator Design Pattern**
- Before the code was implemented, I provided a suggestion on how to make our solution open-ended. https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/3#note_21955
- I then reviewed the code and provided the pros of the decorator design pattern as well as some concerns https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/3#note_23929

**Problem 3: Tester - JUnit**
- I created testers for the cloning methods in Album, Song, Playlist, BufferDecorator, Artist. I decided to test using JUnit https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/4#note_23966
- Thanks to the testers, I realized our code had a lot of NullPointerException so I corrected this.
- I also reviewed the code for this problem and noted that the clone() method in Song class could be improved. I provided a new implementation for this method https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/4#note_22514
- I overwrote the equals() methods and hashcode() of all classes where we were going to need this and was not already done.

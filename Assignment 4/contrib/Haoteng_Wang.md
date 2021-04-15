Implementation:

    I am the implementator for problem #1 together with @@mmendi1. Details of my implementations
    can be found in merge requests of !2, !4 and !7. Here is a brief summary:
    1. We decided to directly implement playlist as the composite.
    2. I implemented the static NULL plabable object in the playable class.
    3. I modified the song class to takes the NULL artist instead of Using Ootional<Artist>
    4. Implemented all the relavent equals() methods.
    5. Some getters and setters
    6. Modified the Genre class from last activity.
    
Tester:
    
    I am the tester for the bufferDecorator in problem #2. Details of my testing code can
    be found in merge request of !24. The test covers 100% of the bufferDecorator class.
    
Reviewer;

    I am the reviewer for problem #3. I reviewed the design choices, the code and gave my
    suggestions. My main suggestion is that we shouoldn't use the flyweight design for the
    song class. Also we shouldn't change the file path when we are cloning songs. I also give
    some other suggestions,
    
    I also made a general UML diagram for the whole project.
    [The UML Diagram](https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/6)
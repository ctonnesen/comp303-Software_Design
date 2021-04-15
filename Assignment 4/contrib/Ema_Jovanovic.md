Implementer:

- Implemented initial verison of PlayCountDecorator (problem 2)
	- Changed functionality of Playable methods
	- BufferDecorator implemented similarly
	- Added to branch ema-p2-playcount
	- Reviewed alternative PlayCountDecorator implementation 
	    - Had added clone method
	    - https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/3#note_24267

Reviewer:

- Reviewed suggestions for polymorphic cloning (problem 3)
	- Suggested we implemented a copy method in interface Playable which would be overridden in each class that implemented Playable (such as Playlist, Album, etc)
	- https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/4#note_21991 
- Reviewed design discussions for extra features (problem 2)
	- Discussed alternative structure of using more general abstract class for decorator class wrapper
		- https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/3#note_23363
	- Discussed usefulness of clone methods in decorator classes 
		- https://gitlab.cs.mcgill.ca/mnassif/303a4t11/-/issues/3#note_24267


Tester:

- Created AlbumTester and expanded it to include equals method (problems 1 and 3?)
	- Utilizes Java unit testing 
	- Doesn't use stub such as Playlist since Album is comprised of Song objects rather than more complex Playable objects which are composite and more easily represented as a stub 
	- Added to branch ema-AlbumTesting
	
	
Notes:

- Not sure exactly which problems my tester code would count towards... I was originally supposed to be a tester for problem 1 but a thorough Playlist tester had already been created, so I instead worked on a thorough Album tester. Problems had some overlap.
	

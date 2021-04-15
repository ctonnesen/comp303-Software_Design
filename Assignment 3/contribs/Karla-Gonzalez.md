## Problem 3
- Discovered redundancies within backup() and isBackedUp()given the flag variable used to detect if the Library had been backed up. Suggested a small improvement though the team decided to maintain original implementation.
- Opened discussion to change the formatting of the two methods: removeAlbumAndSongs and removePlaylistAndSongs. Noticed that we had 4 methods: removeAlbum, removePlaylistandSongs, removeAlbum, removeAlbumandSongs. Suggested we reduce this to 3: removeAlbum, removePlaylist, andRemoveSongs. Further explanation found [here](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/4#note_17575)
several other small comments discussed [here](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/4/)
 - Assisted and discussed the [commit](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/commit/cb1351d11fab4383ec8819e3ff21324acd6ac805) for new implementation of the remove() methods

## Problem 4
- Reviewed the first implementation of code and gave comments regarding possible areas of improvement: caught multiple set backs in original solution discussed the possibility to revert enums to booleans within the song DupeChecker [Discussion](https://gitlab.cs.mcgill.ca/mnassif/303a3t2/-/issues/5#note_17070)

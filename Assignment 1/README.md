# Design Activity 1

*Start on 14 September, complete by 25 September end of day Montreal time zone. Covers Chapter 2 of the book. Project repos will be available on 14 September.*

## Problem Statement

Following the principles seen in Chapter 2 of the book, design and write the code necessary to support a music library with the following properties.

1. A library is made up of songs, playlists, and albums.
2. Each song corresponds to a specific file path. This path determines the identity of the song and the audio format used and can never change for a given song. Your solution needs to take into account that a file path and/or audio format (as determined through the file extension) may not exist. 
3. A song can become *invalid* if its underlying file disappears from the operating system. It should be possible to detect and remove invalid songs from a library.
4. A song has associated meta-data organized into key-value pairs. When describing songs, keys are called *tags*. There are three types of tags: expected, optional, and custom. Expected tags represent pieces of data that it would be weird not to have. These include title, time, and artist. Optional tags include common tags from a fixed set. These include BPM (beats per minute), genre, composer, etc. Custom tags is a feature that allows users to have an arbitrary key-value pair associated with a song to help classify their music. The value of tags can change during the lifetime of a song.
5. Songs can be organized into playlists. A playlist is an ordered collection of songs. A playlist must have a name. It can be possible to change this name after the playlist is created. It should be possible for client code to access all the songs in the playlist, as well as easily access pre-determined aggregated information (number of songs, total playing time, etc.)
6. Songs can be organized into albums. By default, songs are not part of any album. However, a song can be associated with an album, in which case it gains a track number. Albums can be partial, so for example it could be possible to have an album with only tracks 3, 5 and 9. Client code should be able to gain access to all the songs in an album, and find out their track number. Albums have a required title and optionally an artist name. It should be possible to change both the title and artist of an album after the album is created. It should be possible to have songs from different artists in an album. Your solution is not responsible for ensuring that the content of an album is correct in the musical sense.

## Rules of Engagement

* The goal of this activity **is** to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* The goal of this activity **is not** just to bang out a satisfactory solution. Please be aware that simply uploading a solution, even if it's a fine solution, will not constitute a satisfactory completion of this activity.
* Try to isolate "variation points" in the solution space, and discuss alternative solutions. Use issues to organize your work. Ask help from TAs if you need guidance.
* As part of the activity, strive to contribute all of: code, design diagrams, textual comments, and code reviews.
* Do not be shy to contribute! During the two weeks, anything can be changed. If you commit some code that has a problem, it's easy to change it. If you make a comment that later proves to make no sense, it's easy to edit it. And so on.
* If you feel up for it, consider using branches to explore alternatives.
* It's completely ok to propose solutions that turn out to be, in the end, not all that great. If fact, it's explicitly encouraged. If it helped articulate why a certain option isn't worth pursuing, you've contributed something important.
* Parts of the requirement are *purposefully* left open-ended. For example, in requirement 4 we state "pre-determined aggregated information (number of songs, total playing time, etc.)". In such case, as long as you have enough operations to demonstrate the point, it's not really important for you to support a *precise* list of operations. Whenever the requirement state "etc.", it's safe to assume that we don't care exactly about the complete list. If you believe that an an ambiguity needs to be clarified, please post an issue on the class's main GitHub repo.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.

## Deliverables

1. The documented code of the solution. Include main method that exercises the main scenarios of the code. Tag the code with a Major.Minor version. Start with `v1.0` and if for any reason you need to update your solution, increment the second digit. We will look at the last available tagged version.
2. In the file `README.md` a bullet list of the main design decisions and the rationale for them, optionally linked to the various commits, issues, files, and other resources related to the discussion.

## Marking Scheme

The solution will be assessed for basic adequacy, but most of the grade will be determined by each student's individual contributions to the activity.

### Solution

The solution is evaluated for the group. As long as it falls within the [A-C] range there's nothing to worry about.

| Grade | Assessment                                                   | Outcome               |
| ----- | ------------------------------------------------------------ | --------------------- |
| A     | **Notably excellent solution**                               | 10% bonus             |
| B     | **Very good solution:** All aspects follow the principles seen in the course and show evidence of well-informed quality work, except for very minor problems than can be overlooked in practice. | No grade modification |
| C     | **Good solution:** Small problems are noticeable, but these could be fixed during implementation with some low-impact design changes. | No grade modification |
| D     | **Marginal solution:** The solution might be workable, but there are a few obvious major problems that would require some rework at the design level to be implementable. | 10% penalty           |
| F     | **Flawed solution:** A solution that cannot reasonably be implemented without thorough rework, and shows little to no knowledge of the course material. Includes complete absence of a solution. | 20%-100% penalty      |

### Contributions

The contributions are evaluated individually according to the following instrument. Midpoints (odd numbers) are used for assessments that fall between categories.

| Grade | Assessment                                                   |
| ----- | ------------------------------------------------------------ |
| 0     | No contribution, or exclusively irrelevant or unhelpful contributions (clutter) |
| 2     | Only shallow/token/symbolic contributions (fixing typos, formatting, generic comments, etc.) |
| 4     | The contributions have some substance but are limited in number, or remain shallow and/or generic (reheated from the textbook), and/or are not relevant to any on-going discussion. |
| 6     | The contributions meet only a few of the criteria for the maximum grade, or meet some of them partially. They provide evidence of adequate knowledge of the material, but with some noticeable omissions. |
| 8     | The contributions meet most, but not all, of the criteria for the maximum grade, but generally demonstrate very good knowledge of the material and regular involvement instrumental to the solution. |
| 10    | The student contributed regularly and substantially; Many of the contributions show thorough preparation and a deep understanding of the issues at hand as learned from practice; If applicable, the contributions are relevant to on-going discussions, build on previous contributions, and contribute to moving towards a common goal; The contribution are of high quality and helpful; The contributions demonstrate mastery of the material through the exercise of the following skills: coding, diagramming, and commenting. |


### Song class:
 The descion for the Song class was to compose it of several objects, both of our design and several already existing. From the beginning of the project, it became clear that the Song class would become the central pillar of which we build everything else, so we had to ensure that it was solid in design.
### ExpectedTags: 
These would be the tags that include title, artist and totalTime object that would be stored upon the creation of a Song object (which would consequently call the ExpectedTags constructor). the TotalTime itself was of its own class. The ExpectedTags class would utilize a HashMap<String,Object>, as it had to store a variety of Objects inside. Typecasts on the getters proved the easiest option for converting them out of the Object class, but if there were more tags, we would have had to limit the types as it would have become unfeasible. A copy constructor was created to allow encapsulation.
### TotalTime:
This class was created so as to keep track of the seconds, minutes, and hours by having each be represented by one of 3 int attributes. Initially, we believed extending the class for the purpose of not having hours be a default attribute for songs, but this was scrapped after we decided the tradeoff was minimal in order to have it function with our album and playlist classes. At the end of it all, though, the hours aren't even apparent to the client code, so it does not look visually out of place. In additon, the TotalTime has a method that adds and subtracts time for the playlist and album classes, allowing us to have one place where all of the chronoclogical data is handled, making it much easier to debug. The downside of this was the need to limit the int parameters with assert statements so that someone could not input an invalid "second" or hour "value". There is also a copy constructor for this class so as to ensure encapsulation. 
### customTags:
This class is essentially a HashMap<String,String> attribute that stores any number of custom tags. It uses a toString for an object to store value. It also has an empty constructor, so as to allow a constructor for the song class to have only the expected tag elements. This choice was made in order for more simplicty and having an String Object hashmap would be a waste due to most tags being able to be represented as a String. In this way, we don't have to worry about typecasting for our getters, as it can all be represented easily as a String.
### optionalTags
This class holds the composer, beats per minute and genre in a HashMap<String,Object>. This class, due to holding several different kinds of objects similar to the ExpectedTags class, necessitated the use of the Object <value> . It returns these elements only if they already exist. There are several constructors for this class such, as an empty one for similar reasons to the customTags, or another for creating copy for encapsulation reasons. For client-friendliness, there is also a way to add values to the predetermined keys, through the use of just passing the String name of the key and the actual value. This is achieved through an if-else ladder that compares and matches the key parameter to the the final String declared at thebeginning. The if-else ladder is not ideal and would necessiate the use of enums if expanded beyodn 3 possible options, but is limited and not too egregious.
### Playlist
The playlist class contains an arrayList of Songs, a totalTime object, and a String for its name. The arrayList object was chosen due it providing a way to store them and could be easily iterated over, as compared to the slightly more difficult HashMaps. The draw back here was the need to make a method that would sort the arraylist. The one currently in effect by default is to sort by title, but there are also methods to sort by totalTime and artist as well. In additon, the Arraylist could return the songs in side of it and the number of songs as well. the draw back of this method is the need to call the sorting method every time it adds an object, however this would be unnceesary for removal due to it already being sorted every time an object is added.
### SongWithTrack
This class is simply an extension of the song class that adds an Integer object to the song to represent the track number in an Album for storage. Tt has a getter for returning it and a two copy constructors, one containing the various tag classes mentioned above the other having simply a song and and integer. However, this object posed signicant difficulties in that it would not allow us to compare inherently to a Song object with a .equals without overriding it. This limitation, funnily enough, actually helped create a bit for fluidity in the code, as it inspired other methods in the album and playlist class for removing by a songTitle that became critical in our checkForNullSongs() method.
### Album
The album class is comprised of a hashMap<Integer,SongWithTrack> to store the songWithTracks, required title, and optional artists. This structure allows the ability to get the SongWithTrack simply by inputing an integer, which helps to make it more similar to it's real life abstraction. Tt also contains a TotalTime object to keep track of how long the album is, which utilizes the addition and subtraction methods previously mentioned. There are two sets of constructors: one that takes an array of Songs, and another take takes no Songs and simply initializes the title and/or the artist. There are getters and setters for both both the title and the artist.
### Audioformat
the audio format of the song is an enumerated type that limits the amount of choices that the user can input to .mp3,.flac etc.
### Library:
The library class is essentially 3 arrayLists to represent our three main classes and store/keep track of each respective type and instance in one list: ArrayList<Song>, ArrayList<Album>, ArrayList<Playlist>. It has a method, checkForNullSongs() to check the database of songs for valid songs (i.e. songs with existing files) and remove those for which an udnerlying File cannot be found. The user can add a new playlist or album by simply calling the addNewPlaylist or addNewAlbum, which places the newly created object at the end of its respective Library attribute ArrayList. The class also allows for the addition of songs to these created objects through the addSongToPlaylist, addSongToAlbum, and addNewSong. With nearly every method call in Library, checkForNullSongs is called to ensure the integrity of the database and removes any invalid songs before presenting to the client. These removals are done by iterating through the Library Arraylists, and then iterating through the collectione ach instance of these ArrayLists have, whether it be album, playlist, or regular Song arraylist. In addition, there are methods for acessing playLists and Albums internal collections of songs, as well as other info they hold, like aTotalTime for both types. At the bottom of the class if the main method, which features a demo of the code by creating three songs with tempFile Objects, removing one of these tempFile objects (rendering the above Song invalid), and displaying how the according Song has been removed from all instances, including an album and playlist.

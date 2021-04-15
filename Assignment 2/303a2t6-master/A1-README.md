## Design Activity 1

# Main Design Decisions
* Diagram: after the first few days, we developed a diagram containing the main classes and attributes that we would 
base our code on, found here: https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/17. We subsequently made many 
changes to our design, but we still used this diagram as a base to begin with.  
* Naming important concepts: We created classes for the most significant concepts needed in this activity--namely 
a Library, Song, Playlist and Album class. We also included classes such as OptionalTag and CustomTag to highlight 
the difference in how these object are used and implemented, even if they may seem similar.
* Component Design Principle: To optimize the collaborations between different components of this project. We agreed 
trying to support as much functionality as possible when first implementing the classes, so that we do not have to add in
a lot of additional functions last-minute when everything comes together. For example, in Album class, a RESTful standard (CRUD)
was adopted for the management of songs within the album. This turns out to be effective in the end and we did not miss major requirements 
because of miscommunication.
* Encapsulation: To ensure our design is as encapsulated as possible, we decided to adopt the **facade pattern** in the
 software design, giving the Client access to the Library class only. That is, we set all the classes except Library
  to be package-default and only set the Library as public. Library redirects all the requests from Client to the hidden
  classes. From there, the Client is able to perform all actions 
  necessary to manage Songs, Albums and Playlists. This also allows the client 
  to create as many new libraries as they please. This way, we hide the complex logic from the user and let Library 
  handle everything from creation to delete. Besides, we can prevent almost all invalid operations that a user can 
  make to break the codebase.
Issue discussion link: https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/24, https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/27 and https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/30
* Album/Playlist's unique identifier: In the library class we included concurrent.atomic.AtomicLong that allows us to 
linearily keep track of integers we assign our new albums and playlists. We have two different attributes, one for
 albums and one for playlists, that we fetch from and increment each time a new object is made. We implemented this
  so we had a unique way to identify albums and playlists. We could not use their names as the client is free to 
  add as many albums with the same name. Issues link https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/28 and 
  https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/14
* Tag implementations: We implemented the three types of tags in three separate ways: the expected tags are 
attributes of the Song, optional tags are represented as enums and custom tags are regular objects. This adds a 
layer of encapsulation as the client is simply just inputs strings, however the library and song class take care 
of how these strings are treated(i.e if they are tagValues or predetermined optionalTags). 
Issue discussion link: https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/9
* Checking for invalid songs: We consider a song to be invalid if its underlying file on the OS does not exist or 
if the file extension is not ".mp3". Our general method isValidSong(), in the song class, verifies exisitence of 
the underlying file. This method is called fom the album and playlist classes before all operations. We hope you 
enjoy.
* Client class: As a group effort, we included a client class which is outside the library package where we test 
our code and show off some examples of how a client could potential use our implementation of a music library. 

* Note: If a song is found to be invalid, it will be removed without warning and without indication to the client 
that its gone. The client wont be notified of removal.
* Assumptions made on client: We assume that the client will always have access to their songs filePath and can 
input them with ease. We also assume the client will have their own personal list where they have a reference to 
all the album and playlists they have created so they will always put in valid IDS. If in the case they forget, 
the can call getAllAlbums()/getAllPlaylist() in the library class and search themselves for the album they are 
interested in. 
* Meetings: we held(almost daily) meetings where we almost all got together to brainbstorm better 
implementations and/or help each other with code. A git workshop was also hosted for teammates with limited experience
with git to ensure a consistent workflow. 
Meetings: https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/7 - https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/14 - https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/24 https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/30 - https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/15 
if no names where specifically added means that comment was a group effort. Helping with code https://gitlab.cs.mcgill.ca/mnassif/303a1t11/-/issues/34

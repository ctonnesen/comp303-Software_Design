# Our Approach for Design Activity 2: PLEASE CHECK v2.2 FOR THE LATEST VERSION

### Base Code Rationale
 - We saw a lot of options of base codes and we decided to stick with the base 
 from Vincent, and keep the UI of Wendy's code. 
 - The idea was that the base
 code from Vincent(Chenzhun) was fully encapsulated, and has clear preconditions marked out. Also the methods are in standard in terms of naming, so it would be easy to extract the interface. Thus, when working, we are only dealing with really 
 one class and we don't need to worry about escaped refrences from other classes.

### Problem 1: Use Interface to support main domain concept:

#### The Two Interfaces are:
1. `ISongList`
2. `Playable`

#### 1) ISongList:
- ISongList defined **the basic functionality**/the crud operations an audiolist suppose to have, such as create, read, update, delete
- In our project, `Album` and `Playlist` both implements `ISongList`, since both of them suppose to act as some sort of audiolist. We had the plan of letting Library 
  extend ISonglist as well. However, there are more discrepencies than we imagined. For example, the unique identifier of a Song in the library is the filepath(String), 
  but the unique identifier for Playlist/Album are ids (integers). For the sake of simplicity, we decided to let Library stand out as an independent component. 
- However, `Album` and `Playlist` also have their own distinct parts, for example, album has `track number` while playlist dont, but they all serve as different implementations
 of the same structure.
#### 2) Playable:
- Playable only define one method which is `play()`.
- In this project, `Song`, `Album` and `Playlist` all implement Playable but each of them have a different implementation/logic on the method play().
- In Song, when you call `play()`, you will see a detailed information about the song's title, name, artist, etc;
- In Album, when you call `play()`, it will frist **sort** the Album songs **by trackNumber**, and use `enhenced for-loop` to play each song in the album.
- In Playlist, when you call `play()`, it will **calculate the total seconds**, and then display songs just as album did.

##### Contributor to this problem: 
- Vincent(Chenzhun) Huang (implementation, discussion)
- Wendy Wu (idea)
- Sullan KANG (discussion)
- Sofie Eile (discussion, first idea with abstract class that evolved to an interface)
- Christian Tonnesen (discussion)
---

### Problem 2: Provide at least two ways to **obtain the songs in any object**
* We solved this by creating a class called SongIterator; the class creates an iterator object that iterates over the given list of songs. We chose to implement the SongIterator with the aid of a queue which is a form of linkedlist. The linkedlist is faster than an Arraylist when it comes to inserting and deleting elements than an Arraylist. The downside of a linkedlist is that it is slower for randomly accessing elements. Therefore; the SongIterator comes in great use when we are iterating thorugh an entire list and adding/removing elements as we go along. For the second implementation, our playlist,album,songs are stored into Hashmap. 
* We chose hashmaps as it allows us to tie an unique identifier to each object. By using the built-in .values(); function gives us access to songs without the need to create an additional object with an access time of O(n). An integrated library method is also very reliable.  Hence the design descision was a choice of convinece and practicality.   
##### Contributor to this problem: 
- Vincent Huang (implementation,discussion)
- Sofie Eile (implemented, discussion)
- Christian Tonnesen (discussion)
---

### Problem 3: Interface Segregation Principle

Throughout our code, we sought to keep ISP in mind when it came to designing and implementing our interface and client code. We had three main interfaces: Filterable, ISongList, and Playable, with each catering to a specific branch of functions. Filterable implemented methods specifically for the sorting of songs in an Album, Playlist, or Library with a variety of methods, such as being shorter or longer than some time value. This interface is fully self-sustaining and does not depend on any unnecessary methods from other interfaces to perform its job. In each class it is implemented, each method is utilized to sort, with none being destined for no purpose. This pattern is also evident in our ISongList interface, which deals with the CRUD (create, read, update, and delete) of Songs in the previously mentioned three classes. Like Filterable, every method in ISongList is utilized in their respective classes. Finally, we have Playable, which implements a play method for Albums and Playlists. None of these interfaces depend on each other to operate, as coupling two would often mean that there are methods that become unused. 

For instance, it ISongList was coupled with Playable, the play method of Playable would not be utilized in the Playlist class, but the interface would nonetheless depend on its implementation to operate. By splitting the interface into two, we are able to have each run in their respective classes and respect the Interface Segregation Principle.
##### Contributor to this problem: 
 - Christian Tonnesen (discussion/overview)
 - Vincent Huang(discussion)

---
### Problem 4: View of the library including only the songs that respect a certain filtering predicate.

* We decided that the safest, in order to maintain encapsulation, was to consider the view of the library as a simple display. Therefore, after filtering the songs according to the predicate, we simply printed the songs using the toString() from the Song Class. Instead of returning a collection each time the user filtered a list of songs, we thought it would be better to let them decide whether they wanted to create a collection with these songs or not. If the user ever wanted to create a playlist containing the filtered songs, he would be able to do it using the existing methods. 

* To filter the songs, we decided that our best option was to make use of a song’s Tags. Thanks to the expected and optional tags, the user would be able filter a list of songs by artist, title, bpm, genre and composer. We decided that it would be better to provide separate methods for the Time Tag since it would be more common for the user to search for songs “longer than x time”, or “shorter than x time” rather than “songs of exact time”. However, the three corresponding methods were provided. We also decided to provide methods allowing for the user to search for a specific String in the Title of a Song (at the beginning, or simply containing).

* The “open-mindedness” of our implementation comes from the fact that we allow users to filter songs according to their Custom Tags. Say a user wanted to filter a list of Songs, using the “mood” custom tag they already created, they would be able to do so by using the FilterByTag(). Now say a user wanted to filter a list of songs by “80s” and “80s” was not currently a Custom tag; the user would have to create the custom Tag, assign it to its corresponding songs and then using this same method FilterByTag() he would be able to filter the list. 

* The three methods for Time comparison and the two methods for String/Title comparison were provided as a shortcut so that the user wouldn’t have to create custom Tags for what we considered to be “popular” filtering predicates.

*  Therefore we created an interface Filtereable(), declaring all filtering methods. We then made Album, Playlist and Library implement this interface and we provided the corresponding methods for each Class.
##### Contributor to this problem: 
- Sofie Eile (implemented)
- Malena Mendilaharzu  (implemented)
- Christian Tonnesen (discussion/consult)

---

 ### Problem5: Sorting
 #### We solve the simple sorting problem by:
* 	Create 3 **comparator object** (ByArtist/ByLength/ByName), each implements Comparator interface and overwrite the compare method.
 #### How we use them:
1. construct the comparator you want to use: `Compatator<Song> byXXXComparator = new ByXXXComparator();`
2. use the built-in collections.sort: `Collections.sort(XXXthingsWantToSortXXX, byXXXComparator);`
 #### we solve the open-ended sorting problem by:
- Create a **MultiComparator** class, which takes a list of comparator in the constructor.
#### How we use it:
 1. assume we have a list of fields `(name,time,bpm,artist....)` by priority, we don't know what's inside
 2. we first use a `enhenced for-each loop` to **build a list of comparators using anyonunous classes** with some Good Logic
 3. Good Logic includes: <br />
 a): if songA and songB both does not have `bpm`, we use the next field (in this case `"artist"`) to compare them <br />
 b): try and catch clauses to prevent exceptions on notExisting fields<br />
 c): `name`, `artist`, etc are sorted by lexical graphically order while time and bpm are sorted as integers
 4.  then we call the built-in sort function: `Collections.sort(XXXthingsWantToSortXXX,new MultiComparator<Song>(comparators));`
 #### In conclusion:
 
 ######Obviously the open-ended sorting solution is very powerful, it can solve simple sorting problems as well. But in real life, since users will call on the simple sorting methods more frequently, so I kept both solutions.
 
 ##### Contributor to this problem: 
 - Wendy Wu (implemented)
 - Jinyue JIANG (discussion)
 - Mohamed Elenany (discussion)
 - Sofie Eile (discussion)
 - Christian Tonnesen (discussion/consult)
 
 ---
 
 ### Problem 6: Playlist Generation Feature

 
 #### In brief: 
 - We saw a lot of options of base codes and we decided to stick with the base 
 of Vincent, and keeping the UI of Wendy's code. The idea was that the base
 code of vincent was fully encapsulated, keeping all methods only accessible 
 through the library class. Thus, when working, we are only dealing with really 
 one class and we don't need to worry about escaped refrences from other classes.
 
 - In my part, I worked on problem number 6 and this is the rationale for my design
 decisions:
 
 ##### for testing:
 - I added button6 `Generate Playlist by Genre` and button7 `Generate Playlist by Artist` to generate the playlists (in playlist menu)
 - I added a button4 to `Generate a playlist with songs similiar to the selected song` (in select song menu)
 
 - for generating by genre, I generated a playlist with songs that are of the
 same genre. I use a enhanced for loop (which is essentially
  just an iterator of a List object) to iterate through all songs of a library and 
 check if the genre of each song is the same as the desired genre. Since genre 
 is an optional tag, it may be empty for some songs so I catch any exception 
 raised from this issue. In addition, I assert that the inputted string for the author name is not null,
 with a precondition to make sure the client understands that condition.
 
 - for generating by artist, I use the same idea as genre and use the same
 iterator idea and making sure I so not have a null pointer exception. In 
 addition, I assert that the inputted string for the author name is not null,
 with a precondition to make sure the client understands that condition.
 
 - for generating a playlist similiar to a song inputted by the user, I also
 have an assert and a precondition to make sure that the song object inputted
 is not a null. Also, I implemented an iterator method to iterate through songs
 of a library. I created a helper method to compare song objects based on certain
 criteria..it checks if both songs have the same composer, genre, artist, and 
 bpm difference smaller than 30 (I read to understand that songs with a bpm difference
 higher than that are considered different in music and/or genre). If two songs have 
 the two or more of those criterion the same, then they are considered similar(if I made
 it to return true if more than one difference happens it would be meaningless, as for example
 if songs have the same artist does not mean that they are similiar in any way..they can be different
 genres even). I know that this seems like a compareTo method, but I decided not to overload
 that method for many reasons:
 1. This method is only used once in the method for playlist generation by similiar songs, thus, there
 is no reason to overload it if it is never called except once. We already overloaded the CompareTo 
 method multiple times, so no reason to abuse the ability to overload the CompareTo method.
 2. I understand the opportunity to use an anonymous class and the method would only stay inside
 the method it is declared in. However, I felt no reason to do so as I really don't need the comparator method 
 because I don't sort the songs, but I filter them. The helper method is a mix of the idea of equals() and compareTo() 
 in which it compares attributes of a song and deems them two songs similiar(which is what equals() does..
 checking equality but in my case similarity) if 2+ of their attributes are the same.
 
 - For all three generations, there was no reason to making them return something,
 thus, I made them void as the client can just easily access them through the 
 library. I made sure that the generated playlists all have names of a fixed
 format like "All_by_*author name*" so that the user can differentiate between
 manually created playlists and automatically generated ones. If the user
 does not like the name, playlist allows him to change it easily. Finally,
 I made print statements to print results of method call and included the name
 of the playlist generated so that the client can know the name of the playlist
 and access it easily.
 
 - Unfortunately, those generation methods were not abstract methods in an
 interface (thus no polymorphism for those methods), but the issue is that
 those methods are only specific to playlist objects, thus no reason for
 them to be abstract methods
 
###### In addition to question 6, I did some debugging and reference hiding for the playlist, library, and client classes.
 
 ##### Contributor to this problem: 
 -  Mohamed Elenany (Discussion/Implementation)
 -  Christian Tonnesen (Idea/Discussion)
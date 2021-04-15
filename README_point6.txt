Mohamed Elenany (melena1):
*In brief: 
We saw a lot of options of base codes and we decided to stick with the base 
of Vincent, and keeping the UI of Wendy's code. The idea was that the base
code of vincent was fully encapsulated, keeping all methods only accessible 
through the library class. Thus, when working, we are only dealing with really 
one class and we don't need to worry about escpaped refrences from other classes.

In my part, I worked on problem number 6 and this is the rationale for my design
decisions:

* for testing, I added buttons to 13 and 12 to generate the playlists, and 
I added a button number 5 in the button where we chose a single song (button
4), to generate a playlist with songs similiar to the song chosen.

*for generating by genre, I generated a playlist with songs that are of the
same genre. I use a enhanced for loop (which is essentially
 just an iterator of a List object) to iterate through all songs of a library and 
check if the genre of each song is the same as the desired genre. Since genre 
is an optional tag, it may be empty for some songs so I catch any exception 
raised from this issue. In addition, I assert that the inputted string for the author name is not null,
with a precondition to make sure the client understands that condition.

*for generating by artist, I use the same idea as genre and use the same
iterator idea and making sure I so not have a null pointer exception. In 
addition, I assert that the inputted string for the author name is not null,
with a precondition to make sure the client understands that condition.

*for generating a playlist similiar to a song inputted by the user, I also
have an assert and a precondition to make sure that the song object inputted
is not a null. Also, I implemented an iterator method to iterate through songs
of a library. I created a helper method to compare song objects based on certian
criteria..it checks if both songs have the same composer, genre, artist, and 
bpm difference smaller than 30 (I read to understand that songs with a bpm difference
higher than that are considered different in music and/or genre). If two songs have 
the two or more of those criterion the same, then they are considered similar(if I made
it to return true if more than one difference happens it would be meaningless, as for example
if songs have the same artist does not mean that they are similiar in any way..they can be different
genres even). I know that this seems like a compareTo method, but I decided not to overload
that method for many reasons:
1.This method is only used once in the method for playlist generation by similiar songs, thus, there
is no reason to overload it if it is never called except once. We already overloaded the CompareTo 
method multiple times, so no reason to abuse the ability to overload the CompareTo method.
2. I understand the opertunity to use an anonymous class and the method would only stay inside
the method it is declared in. However, I felt no reason to do so as I really don't need the comparator method 
because I don't sort the songs, but I filter them. The helper method is a mix of the idea of equals() and compareTo() 
in which it compares attributes of a song and deems them two songs similiar(which is what equals() does..
checking equality but in my case similarity) if 2+ of their attributes are the same.

*For all three generations, there was no reason to making them return something,
thus, I made them void as the client can just easilly access them through the 
library. I made sure that the generated playlists all have names of a fixed
format like "All_by_*author name*" so that the user can differentiate between
manually created playlists and automatically generated ones. If the user
does not like the name, playlist allows him to change it easily. Finally,
I made print statements to print results of method call and included the name
of the playlist generated so that the client can know the name of the playlist
and access it easily.

*Unfortunately, those generation methods were not abstract methods in an
interface (thus no polymorphism for those methods), but the issue is that
those methods are only specific to playlist objects, thus no reason for
them to be abstract methods

--> In addition to question 6, I did some debugging and refrence hiding for the playlist, library,
and client classes.
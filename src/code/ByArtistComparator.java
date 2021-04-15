package code;

import java.util.Comparator;

public class ByArtistComparator implements Comparator<Song> {
    public int compare(Song o1, Song o2) {
        return o1.getArtist().compareTo(o2.getArtist());/*lexical graphical*/
    }
}

package code;

import java.util.Comparator;

public class ByLengthComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        /* Since time is a string like 0:33, we need to trim it before parse to int*/
    	int time1 = Integer.parseInt(song1.getTime().replace(":",""));
    	int time2 = Integer.parseInt(song2.getTime().replace(":",""));
        return time1 - time2;
    }
}

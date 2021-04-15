package code;

/* Declaration by Wendy Wu:
*   I adopted the idea of MultiComparator on codeReview:
*   https://codereview.stackexchange.com/questions/74758/subsorting-with-multiple-comparators/74764#74764
*   But there is an external library function (Apache) which does the same thing as well*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MultiComparator<Song> implements Comparator<Song> {
    private final List<Comparator<Song>> comparators;

    public MultiComparator(List<Comparator<Song>> comparators) {
        this.comparators = comparators;
    }

    public MultiComparator(Comparator<Song>... comparators) {
        this(Arrays.asList(comparators));
    }

    public int compare(Song o1, Song o2) {
        for (Comparator<Song> c : comparators) {
            int result = c.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}

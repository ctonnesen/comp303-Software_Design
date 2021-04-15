package activity3;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Song  {
    private final String aFile;
    private Optional<String> aTitle = Optional.empty();
    private Optional<Integer> aDuration = Optional.empty();
    private Optional<Artist> aArtist = Optional.empty();
    private Optional<String> aGenre = Optional.empty();
    private Map<String, Optional<String>> aTags = new HashMap<>();

    private static final DupeChecker<Song> dupeCheckerByFile = new DupeChecker<Song>() {
        @Override
        public boolean isDupe(Song pO1, Song pO2) {
        	assert pO1 != null && pO2 != null;
            return pO1.aFile.equals(pO2.aFile);
        }

    };
    private static final DupeChecker<Song> dupeCheckerByTitleArtist = new DupeChecker<Song>() {
        @Override
        public boolean isDupe(Song pO1, Song pO2) {
        	assert pO1 != null && pO2 != null;
            return pO1.aTitle.equals(pO2.aTitle)
                    && pO1.aArtist.equals(pO2.aArtist);
        }
    };

    public Song(String pFile, String pTitle, Integer pDuration, Artist pArtist, String pGenre) {
        aFile = pFile;
        aTitle = Optional.ofNullable(pTitle);
        aDuration = Optional.ofNullable(pDuration);
        aArtist = Optional.ofNullable(pArtist);
        aGenre = Optional.ofNullable(pGenre);
    }

	/*
	Keeping the attributes as optional types means we do not have to provide a value for them
	 */


    public Song(String pFile) {
        aFile = pFile;
    }

    // Beginning of Getters and Setters

	/*
	The getters of the Optional Song attributes check to see if the Optional<T> holds any value and if not,
	it throws an exception letting the client know that there is no value to get. Otherwise, they pull the value from
	the Optional object with .get()
	 */

	/*
	The setters of the Optional Song attributes take in the corresponding Object type (Integer, String, Artist) and
	wrap them in the Optional class. This allows us to set out Song attributes equal to them without fear of a raw null.
	 */

    public String getFile() {
        return aFile;
    }

    public String getaTitle() {
        if (aTitle.isEmpty()) {
            throw new NoSuchElementException("There is no assigned value to the Song Title");
        }
        return aTitle.get();
    }

    public void setaTitle(String pTitle) {
        this.aTitle = Optional.ofNullable(pTitle);
    }

    public Integer getaDuration() {
        if (aDuration.isEmpty()) {
            throw new NoSuchElementException("There is no assigned value to the Song Duration");
        }
        return aDuration.get();
    }

    public void setaDuration(Integer pDuration) {
        this.aDuration = Optional.ofNullable(pDuration);
    }

    public Artist getaArtist() {
        if (aArtist.isEmpty()) {
            throw new NoSuchElementException("There is no assigned value to the Song Artist");
        }
        return aArtist.get();
    }

    public void setaArtist(Artist pArtist) {
        this.aArtist = Optional.ofNullable(pArtist);
    }

    public Optional<String> getaGenre() {
        if (aGenre.isEmpty()) {
            throw new NoSuchElementException("There is no assigned value to the Song Genre");
        }
        return aGenre;
    }

    /**
     * @pre pGenre != null
     */

    public void setaGenre(String pGenre) {
        this.aGenre = Optional.ofNullable(pGenre);
    }

    public Map<String, Optional<String>> getTagMap() {
        Map<String, Optional<String>> copyMap = new HashMap<>();
        for (String curTag : aTags.keySet()) {
            copyMap.put(curTag, aTags.get(curTag));
        }
        return copyMap;
    }

    /**
     * @pre pTags != null
     */

    public void setTagMap(Map<String, Optional<String>> pTags) {
        assert pTags != null;
        for (String checker : pTags.keySet()) {
            if (checker == null)
                System.out.println("The Map has invalid keys");
            return;
        }
        this.aTags = pTags;
    }

    /**
     * @pre pTag != null
     */

    public void setaTag(String pTag, String pValue) {
        assert pTag != null;
        aTags.put(pTag, Optional.ofNullable(pValue));
    }

    /**
     * @pre pTag != null
     */

    public String getaTag(String pTag) {
        assert pTag != null;
        if (aTags.get(pTag).isEmpty()) {
            throw new NoSuchElementException("There is no assigned value to the tag: " + pTag);
        }
        return aTags.get(pTag).get();
    }

    /**
     * Get the song duplicate checker by file.
     *
     * @return the song checker.
     */
    public static DupeChecker<Song> getDupeCheckerByFile() {
        return dupeCheckerByFile;
    }

    /**
     * Get the song duplicate checker by Title/Artist.
     *
     * @return the song checker.
     */
    public static DupeChecker<Song> getDupeCheckerByTitleArtist() {
        return dupeCheckerByTitleArtist;
    }
}

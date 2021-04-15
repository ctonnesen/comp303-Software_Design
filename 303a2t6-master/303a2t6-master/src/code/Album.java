package code;

import java.util.*;


class Album implements ISongList, Playable, Filtereable {
    /**
     * Note that there are two attributes related with Artist,
     * aAlbumArtistName is the one and only artistName we have for album
     * aArtistNames is the list of artistNames for the Album, which is a supplement to what is already there
     */
    private String aTitle;
    private String aAlbumArtistName;
    private int maxTrackNumber = 0;

    private final HashMap<Integer, Song> aContentInHashMap = new HashMap<>();
    
    
    

    /*
    @pre - the song must not be null
     */
    public void addSong(Song song) {
        assert song != null;
        /*
        for adding Song to Album without explicit track number, in this case
        we will assign (the maximum track number occupied) + 1 as the new trackNumber
         */
        aContentInHashMap.put(this.maxTrackNumber + 1, song);
        this.maxTrackNumber = this.maxTrackNumber + 1;
    }

    /*
    @pre - The track number must not already be in the content
     */
    public void addSongWithTrackNumber(int trackNumber, Song song) {
        assert !aContentInHashMap.containsKey(trackNumber);
        aContentInHashMap.put(trackNumber, song);
        this.maxTrackNumber = Collections.max(aContentInHashMap.keySet());
    }

    public void deleteSong(Integer trackNumber) {
        assert trackNumber > -1;
        this.aContentInHashMap.remove(trackNumber);
        if (aContentInHashMap.size() != 0) {
            this.maxTrackNumber = Collections.max(new ArrayList<>(aContentInHashMap.keySet())); // update
        } else {
            this.maxTrackNumber = 0;
        }
    }

    /*
     * @pre The track number has to be contained in the content of the album already
     */
    public Song getSong(Integer trackNumber) {
        assert aContentInHashMap.containsKey(trackNumber);
        return aContentInHashMap.get(trackNumber);
    }



    /**
     * This is for adding Song to Album without explicit track number
     */
    //optional artist name
    public Album(String pTitle) {
        assert pTitle != null;
        aTitle = pTitle;
    }

    public Album(String pTitle, String pAlbumArtistName) {
        assert pTitle != null && pAlbumArtistName != null;
        aTitle = pTitle;
        aAlbumArtistName = pAlbumArtistName;
    }

    public String getTitle() {
        return aTitle;
    }

    public String getArtistName() {
        return aAlbumArtistName;
    }

    public void validateList() {
        this.aContentInHashMap.entrySet()
                .removeIf(
                        entry -> !entry.getValue().isValidSong()
                );
        if (aContentInHashMap.size() != 0) {
            this.maxTrackNumber = Collections.max(new ArrayList<>(aContentInHashMap.keySet()));
        } else {
            this.maxTrackNumber = 0;
        }
    }

    public void changeTitle(String newTitle) {
        assert newTitle != null;
        aTitle = newTitle;
    }

    public void changeAlbumArtist(String newArtist) {
        assert newArtist != null;
        aAlbumArtistName= newArtist;
    }

    public ArrayList<Song> getAllSongs() {
        validateList();
        return new ArrayList<>(aContentInHashMap.values());
    }

    public String toString() {
        return "Title : "+ aTitle +" Artist Name : "+ aAlbumArtistName;
    }

    @Override
    public void play() {
        // print the name of playlist
        if (aAlbumArtistName != null) {
            System.out.println("Now playing: " + aTitle + " by " + aAlbumArtistName + "\n");
        } else {
            System.out.println("Now playing: " + aTitle + "\n");
        }
        ArrayList<Integer> trackNumbers = new ArrayList<>(aContentInHashMap.keySet());
        // sort the track numbers
        Collections.sort(trackNumbers);
        for (Integer trackNumber : trackNumbers) {
            System.out.println("Track " + trackNumber + ":");
            aContentInHashMap.get(trackNumber).play();
        }
    }
    
	@Override
	public void filterByTag(String tagName, String filter) {
		Filter.filterByTag(tagName, filter, aContentInHashMap);
	}

	@Override
	public void songsShorterThan(String t) {
		Filter.songsShorterThan(t, aContentInHashMap);

	}

	@Override
	public void songsLongerThan(String t) {
		Filter.songsLongerThan(t, aContentInHashMap);

	}

	@Override
	public void songsofExactlength(String t) {
		Filter.songsofExactlength(t, aContentInHashMap);

	}

	@Override
	public void titleStartwith(String pattern) {
		Filter.titleStartwith(pattern, aContentInHashMap);

	}

	@Override
	public void titleContains(String pattern) {
		Filter.titleContains(pattern, aContentInHashMap);

	}

}

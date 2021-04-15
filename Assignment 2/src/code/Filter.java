package code;

import java.util.ArrayList;
import java.util.HashMap;

public class Filter {

	// contains all our filter methods.
	public static void filterByTag(String tagName, String filter, HashMap<?, Song> ourList) {
		for (Song song : ourList.values()) {
			String tagType = song.getTagType(tagName.toLowerCase());
			if (tagType.equals("notfound")){

				System.out.println("It was not possible to filter using " + tagName
						+ " Try using the provided methods or create a new Custom Tag and try again.");
			}
			if ((tagName.toLowerCase().equals("artist") && song.getArtist().equals(filter))) {
				System.out.println(song.toString());
			} else if ((tagName.toLowerCase().equals("title") && song.getTitle().equals(filter))) {
				System.out.println(song.toString());
			} else {
				try {
					if (tagType.toLowerCase().equals("optional") && song.getCustomTag(tagName).equals(filter)) {
						System.out.println(song.toString());
					} else if (tagType.toLowerCase().equals("custom") && song.getCustomTag(tagName).equals(filter)) {
						System.out.println(song.toString());
					}
				} catch (NoSuchFieldError noSuchFieldError) {
					System.out.println("It was not possible to filter using " + tagName
							+ " Try using the provided methods or create a new Custom Tag and try again.");
				}
			}
		}
	}

	// Some more options for filtering
	// Notice that the client could always create a new custom tag and use the
	// method above
	// So these would be some shortcuts

	/**
	 * @pre t != null
	 * @param t
	 */
	public static void songsShorterThan(String t, HashMap<?, Song> ourList) {
		assert t != null && ourList != null;

		for (Song song : ourList.values()) {
			if (Integer.parseInt(song.getTime()) < Integer.parseInt(t)) {
				System.out.println(song);
			}
		}
	}

	/**
	 * @pre t != null
	 * @param t
	 */
	public static void songsLongerThan(String t, HashMap<?, Song> ourList) {
		assert t != null && ourList != null;

		for (Song song : ourList.values()) {
			if (Integer.parseInt(song.getTime()) > Integer.parseInt(t)) {
				System.out.println(song);
			}
		}
	}

	/**
	 * @pre t != null
	 * @param t
	 */
	public static void songsofExactlength(String t, HashMap<?, Song> ourList) {
		assert t != null && ourList != null;

		for (Song song : ourList.values()) {
			if (Integer.parseInt(song.getTime()) == Integer.parseInt(t)) {
				System.out.println(song);
			}
		}
	}

	/**
	 * @pre s != null
	 * @param s
	 */
	public static void titleStartwith(String pattern, HashMap<?, Song> ourList) {
		assert pattern != null && ourList != null;

		for (Song song : ourList.values()) {
			if (song.getTitle().startsWith(pattern)) {
				System.out.println(song);
			}
		}
	}

	/**
	 * @pre s != null
	 * @param s
	 */
	public static void titleContains(String pattern, HashMap<?, Song> ourList) {
		assert pattern != null && ourList != null;
		for (Song song : ourList.values()) {
			if (song.getTitle().contains(pattern)) {
				System.out.println(song);
			}

		}
	}

	public static HashMap<Integer, Song> convertListToHashMap(ArrayList<Song> toConvert,
			HashMap<Integer, Song> converted) {
		converted.clear();
		for (Song song : toConvert) {

			converted.put(toConvert.indexOf(song), song);
		}

		return converted;
	}

}
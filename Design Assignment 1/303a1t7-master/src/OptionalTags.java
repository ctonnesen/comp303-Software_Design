import java.util.HashMap;


public class OptionalTags {
	private static final String bpm = "bpm";
	private static final String genre = "genre";
	private static final String composer = "composer";
	HashMap <String, Object> optionalMap = new HashMap <String, Object>();

	/**
	 *@param pBPM
	 *@param pGenre
	 *@param pComposer
	 *@pre pGenre !=null && pComposer !=null
	 */ 

	public OptionalTags(int pBPM, Genre pGenre , String pComposer){
		assert 0 <= pBPM && pGenre !=null && pComposer !=null;
		optionalMap.put(bpm, pBPM);
		optionalMap.put(genre, pGenre);
		optionalMap.put(composer, pComposer);
	}
	
	public OptionalTags(){
		optionalMap = new HashMap<String, Object>();
	}
    //constructor to create new optionalTags
	public OptionalTags(OptionalTags pTag){
     assert pTag != null;
     optionalMap.put(bpm,pTag.optionalMap.get(bpm));
     optionalMap.put(genre,pTag.getGenre());
     optionalMap.put(composer, pTag.getComposer());
	}
	public void addOptionalTag(String tagName, Object pObject) {
		if (tagName.toLowerCase().equals(bpm)) {
			setBPM((int) pObject);
			return;
		}
		if (tagName.toLowerCase().equals(genre)){
			setGenre((Genre) pObject);
			return;
		}
		if (tagName.toLowerCase().equals(composer)) {
			setComposer((String) pObject);
			return;
		} else {
			throw new IllegalArgumentException ("The provided Tag Name does not match any predetermined optional tag name");
		}
	}
	
	public int getBPM(){
		assert optionalMap.get(bpm)!=null;
		return (int) optionalMap.get(bpm);
	}

	public Genre getGenre(){
		assert optionalMap.get(genre)!=null;
		return (Genre) optionalMap.get(genre);
	}

	public String getComposer(){
		assert optionalMap.get(composer)!=null;
		return (String) optionalMap.get(composer);
	}
	public void setBPM(int newBPM){
		assert newBPM >=0;
		optionalMap.put(bpm, newBPM);
	}

	public void setGenre(Genre newGenre){
		assert newGenre != null;
		optionalMap.put(genre, newGenre);
	}

	public void setComposer(String newComposer){
		assert newComposer != null;
		optionalMap.put(composer, newComposer);
	}

	public String toString(){
		return "BPM: " + (int) optionalMap.get(bpm) + "\nGenre: " + ((Genre) optionalMap.get(genre)).toString() + " \nComposer: " + optionalMap.get(composer);
	}
}

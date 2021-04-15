import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomTags {
	

	private HashMap<String, String> customTags;
	
	public CustomTags(String pKey, String pValue) {
		customTags = new HashMap<String, String>();
		customTags.put(pKey, pValue);
	}
	
	public CustomTags() {
		customTags = new HashMap<String, String>();
	}
	
	public CustomTags(CustomTags pTag){
     assert pTag != null;
	 this.customTags = new HashMap<String, String>();
	 ArrayList<String> keys = new ArrayList<String>(pTag.customTags.keySet());
     ArrayList<String> values = new ArrayList<String>(pTag.customTags.values());
	//both would be the same size since each key will be maped to a values
	//and each time one is replaced it will still be equal
	for(int i=0; i< keys.size(); i++){
		this.customTags.put(keys.get(i), values.get(i));//adds to new customTags hashMap
	}
	}
	public CustomTags(String pKey, Object pValue ) {
		customTags = new HashMap<String, String>();
		String temp = pValue.toString();
		customTags.put(pKey, String.valueOf(temp));
	}
	public void setCustomTags(String pKey, Object pValue) {
		String temp = pValue.toString();
		customTags.put(pKey, temp); 
	}
	
	public String getCustomTag(String pKey) {
		return customTags.get(pKey);
	}

}

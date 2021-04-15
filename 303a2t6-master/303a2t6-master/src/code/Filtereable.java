package code;
import java.util.ArrayList;
public interface Filtereable {
	public void filterByTag(String tagName, String filter);
	public void songsShorterThan(String t);
	public void songsLongerThan(String t);
	public void songsofExactlength(String t);
	public void titleStartwith(String pattern);
	public void titleContains(String pattern);
}

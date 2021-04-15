package activity6;

import java.util.Iterator;

public interface Observer {
    public void ItemChange(Iterator<Playable> iterator);
}

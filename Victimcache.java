import java.util.ArrayList;

import java.util.List;

public class Victimcache {



	private int cacheSize;

	private List<String> victimLines;

	

	public Victimcache(int size) {

		cacheSize = size;

		victimLines = new ArrayList<>(size);

	}

	

	public int findtag(String tag) {

		for(int i = 0; i < victimLines.size(); i++) {

			if(victimLines.get(i).equals(tag)) return i;

		}

		return -1;

	}

	

	public void addtovCache(String tag) {

		if(victimLines.size() == cacheSize) victimLines.remove(0);

		victimLines.add(tag);

	}

	

	public void removetag(int index) {

		victimLines.remove(index);

	}

}

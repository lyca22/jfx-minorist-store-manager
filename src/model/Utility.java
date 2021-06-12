package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Utility {

	public void saveAll() throws FileNotFoundException, IOException;
	
	public void loadAll() throws NullPointerException, ClassNotFoundException, FileNotFoundException, IOException;
	
	public long randomNumberWithRange(long min, long max);
	
}

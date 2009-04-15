package cgh.util;

import java.io.*;

/**
 * Move this to the utils package!
 * @author choward
 *
 */
public class ObjectPersistanceHelper {
	
	/**
	 * Stores a object to the requested file.
	 * @param toStore
	 * @param out
	 */
	public void store(Object toStore, File out)
		throws IOException
	{
		if (toStore == null || out == null)
			throw new IllegalArgumentException("Illegal Input");
		if (!(toStore instanceof Serializable))
			throw new IllegalArgumentException("Object being stored is NOT serializable");
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(out));
		o.writeObject(toStore);
		o.flush();
		o.close();
	}
	
	/**
	 * Retrieves a previously stored object from the requested file.
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public Object retrieve(File in)
		throws Exception
	{
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(in));
		return i.readObject();
	}
}

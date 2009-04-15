import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import junit.framework.TestCase;


public class ObjectPersistanceHelperTest extends TestCase {

	static class Settings implements Serializable
	{
		private static final long serialVersionUID = 1L;
		public String t1;
		public boolean t2;
		public Settings(String x, boolean y)
		{
			this.t1 = x;
			this.t2 = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((t1 == null) ? 0 : t1.hashCode());
			result = prime * result + (t2 ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Settings other = (Settings) obj;
			if (t1 == null) {
				if (other.t1 != null)
					return false;
			} else if (!t1.equals(other.t1))
				return false;
			if (t2 != other.t2)
				return false;
			return true;
		}
	}
	
	public void testBadObject()
	{
		try {
			ObjectPersistanceHelper i = new ObjectPersistanceHelper();
			i.store(new Object(), new File("x"));
		} catch (IOException e) {
			fail("This test should throw an Illegal Arg exception!!");
		}
		catch (IllegalArgumentException e)
		{
			//Ok
		}
		catch (Exception e)
		{
			fail("This test should throw an exception!!");
		}
	}
	
	public void testHelper()
	{
		try {
			File f = new File("chris.settings");
			f.createNewFile();
			
			// Do some stuff
			Settings s = new Settings("exit", false);
			ObjectPersistanceHelper i = new ObjectPersistanceHelper();
			
			i.store(s, f);
			Object got = i.retrieve(f);

			// Test that file exists and that it can be rebuilt
			assertTrue(f.exists());
			assertTrue(got != null);
			assertEquals(s.getClass(), got.getClass());
			assertEquals(s, got);
			
			f.deleteOnExit();
		} catch (Exception e) {
			System.err.println(e);
			fail(e.getMessage());
		} 
	}
	
}

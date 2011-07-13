package cgh.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;

import cgh.util.DumpObject.MultiLineToStringStyle;

import junit.framework.TestCase;

public class DumpObjectTest
    extends TestCase
{
    private static class Test {
        private ArrayList<Object> t1 = new ArrayList<Object>();
        @SuppressWarnings("unused")
        private String t2 = "help help";
        @SuppressWarnings("unused")
        private String t3 = "test test";
        private HashMap<String, String> t4 = new HashMap<String, String>();
        @SuppressWarnings("unused")
        private String[] t5 = new String[] {"1", "2", "3", "4"};
        
        {
            t1.add("test1");
            ArrayList<Object> x = new ArrayList<Object>();
            x.add(1);
            x.add(true);
            x.add(new String[] {"a", "b", "c"});
            t1.add(x);
            
            t4.put("key", "value");
            t4.put("k1", "v2");
        }
    }

    private static final String EXPECTED = "t1=[test1, [1, true,";
    private static final String EXPECTED2 = "t2=help help"; 
    private static final String EXPECTED3 = "t3=test test"; 
    private static final String EXPECTED4 = "t4={k1=v2, key=value}"; 
    private static final String EXPECTED5 = "t5={1,2,3,4}"; 
 
    public void testToString() {
        
        MultiLineToStringStyle x = new MultiLineToStringStyle();
        String ret = ToStringBuilder.reflectionToString(new Test(), x);
        System.err.println(ret);
        assertTrue(ret.contains(EXPECTED));
        assertTrue(ret.contains(EXPECTED2));
        assertTrue(ret.contains(EXPECTED3));
        assertTrue(ret.contains(EXPECTED4));
        assertTrue(ret.contains(EXPECTED5));
    }
}
